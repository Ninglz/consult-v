package com.dsecet.core.service.impl;

import static com.dsecet.foundation.model.ErrorCode.EXPERTS_REGISTERED;
import static com.dsecet.util.FileHelper.writeByteToFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.api.vo.ExpertsShowVo;
import com.dsecet.core.domain.record.City;
import com.dsecet.core.domain.record.Levels;
import com.dsecet.core.domain.record.SendIntegral;
import com.dsecet.core.domain.record.Times;
import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.domain.use.Img;
import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.core.domain.user.Focus;
import com.dsecet.core.repository.CityRepository;
import com.dsecet.core.repository.ExpertsListRepository;
import com.dsecet.core.repository.ExpertsRecordListRepository;
import com.dsecet.core.repository.FocusRepository;
import com.dsecet.core.repository.admin.IndustryRepository;
import com.dsecet.core.repository.admin.SendIntegralRepository;
import com.dsecet.core.service.CommentService;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.LevelsService;
import com.dsecet.core.service.admin.TimesService;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.CommentVo;
import com.dsecet.foundation.model.query.ExpertsListVo;
import com.dsecet.util.FileHelper;
import com.dsecet.util.TimeUtils;

/**
 * 专家接口实现类
 * @author: heyue
 */
@Service("expertsService")
@Transactional
public class ExpertsServiceImpl implements ExpertsService {
	
	@Autowired
	private ExpertsListRepository expertsListRepository;
	
	@Autowired
	private ExpertsRecordListRepository expertsRecordListRepository;
	
	@Autowired
	private IndustryRepository industryRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private LevelsService levelsService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private TimesService timesService;
	
	@Autowired
	private SendIntegralRepository sendIntegralRepository;
	
	@Autowired
	private FocusRepository focusRepository;
	
	@Override
	public Experts saveInformation(String name,Long sex,String idCard,MultipartFile avatar,Date seniorityStart,Date seniorityEnd, Long industryId,Long cityId, String goodAt, String description,
			String alipayUserName, MultipartFile[] certificateImg,Long id) {
		
		Experts experts = expertsListRepository.findOne(id);
		experts.setState(0L);//设置专家下线状态
		Levels levels = levelsService.findOne(Long.valueOf(1));
		experts.getConsumerDetails().setExpertsLevels(levels);
		List<ExpertsRecord> record = experts.getExpertsRecord();
		if(record != null && 0 != record.size()){
			throw new RespondableException(EXPERTS_REGISTERED);
		}
		ExpertsRecord expertsRecord = new ExpertsRecord();
		
		expertsRecord.setSeniorityStart(seniorityStart);
		expertsRecord.setSeniorityEnd(seniorityEnd);
		if(industryId != null){
			Industry industry = industryRepository.findOne(industryId);
			expertsRecord.setIndustry(industry);
		}else{
			expertsRecord.setIndustry(null);
		}
			City city = cityRepository.findOne(cityId);
			experts.build(city);
			
		expertsRecord.setGoodAt(goodAt);
		expertsRecord.setDescription(description);
//		experts.setRealName(name);
		expertsRecord.setExpertsRealName(name);
//		if(sex != null){
//			experts.setSex(sex);
//		}else{
//			experts.setSex(2L);
//		}
		if(sex != null){
			expertsRecord.setExpertsSex(sex);
		}else{
			expertsRecord.setExpertsSex(experts.getSex());
		}
		experts.setIdCard(idCard);

		if(avatar != null){
			String originalName = avatar.getOriginalFilename();
			String extension = originalName.substring(originalName.lastIndexOf("."));
			try {
				FileHelper.deleteAll(
						SystemProperty.storageFilePrefix+File.separator+
						SystemProperty.informationPrefix+ File.separator + 
						experts.getId() + File.separator);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String imgPath = SystemProperty.informationPrefix+ File.separator + experts.getId() + File.separator + experts.getId() + extension;
			writeByteToFile(SystemProperty.storageFilePrefix+imgPath, avatar);
//			experts.setAvatar(imgPath);
			expertsRecord.setExpertsAvatar(imgPath);
		}else{
			expertsRecord.setExpertsAvatar(experts.getAvatar());
		}
		expertsRecordListRepository.saveAndFlush(expertsRecord);
		List<Img> imgList = new ArrayList<Img>();
		if(certificateImg != null){
			for (int i = 0; i < certificateImg.length; i++) {
				if(certificateImg[i].getSize() == 0){
					continue;
				}
				String originalName = certificateImg[i].getOriginalFilename();
				String extension = originalName.substring(originalName.lastIndexOf("."));
				String certificateImgPath = SystemProperty.informationPrefix+ File.separator + experts.getId() + i + File.separator + TimeUtils.currentMillis() + extension;
				writeByteToFile(SystemProperty.storageFilePrefix+certificateImgPath, certificateImg[i]);
				Img dossier = Img.of(certificateImgPath.replaceAll("\\\\", "/"),expertsRecord);
				imgList.add(dossier);
			}
			expertsRecord.addImg(imgList);
		}
		experts.setHandle(1L);  //设置专家身份申请中状态
		expertsRecord.addExperts(experts);
		expertsRecordListRepository.saveAndFlush(expertsRecord);
		return experts;
	}

	@Override
	public PageableResponse<ExpertsListVo> findByConditions(String name, String industryId, String evaluation, String price,Long id,
			Pageable pageable) {
		name = name == null ? "" : name;
		industryId = industryId == null ? "" : industryId;
		price = price == null ? "" : price;
		evaluation = evaluation == null ? "" : evaluation;
		Page page = expertsListRepository.findByConditions(name, industryId, evaluation, price,id.toString(), pageable);
		return PageableResponse.of(ExpertsListVo.of(page.getContent()), page.getContent().size(), (int) page.getTotalElements());
	}

	@Override
	public PageableResponse<ExpertsListVo> findRecommendExperts(Long id,String evaluation,Pageable pageable) {
		evaluation = "desc";
		Page page = expertsListRepository.findRecommendExperts(id.toString(),evaluation,pageable);
		return PageableResponse.of(ExpertsListVo.of(page.getContent()), page.getContent().size(), (int) page.getTotalElements());
	}

	@Override
	public Experts findOne(Long id) {
		Experts experts = expertsListRepository.findOne(id);
		List<ExpertsRecord> expertsRecord = experts.getExpertsRecord();
		Collections.sort(expertsRecord);
		if(experts.getHandle() == 3L || experts.getHandle() == -2L){
			expertsRecord.get(0);
		}else if(experts.getHandle() == 2L || experts.getHandle() == -1L){
			expertsRecord.get(expertsRecord.size() - 1);
		}
		return experts;
	}

	@Override
	public Page<Experts> queryByCondition(String keyword, String active,Long expertState, Pageable pageable) {
		return expertsListRepository.queryByCondition(null,keyword, active, expertState, pageable);
	}

	@Override
	public Experts save(Experts experts) {
		// TODO Auto-generated method stub
		return expertsListRepository.save(experts);
	}

	@Override
	public Experts updateInformation(String name, Long sex, String idCard, MultipartFile avatar, Date seniorityStart,Date seniorityEnd,
			Long industryId, Long cityId, String goodAt, String description, String alipayUserName, MultipartFile[] certificateImg,
			Long id) {
		Experts experts = expertsListRepository.findOne(id);
		//ExpertsRecord expertsRecord = experts.getExpertsRecord().get(0);
		ExpertsRecord expertsRecord = new ExpertsRecord(); 
		if(seniorityStart != null){
			expertsRecord.setSeniorityStart(seniorityStart);
		}
		if(seniorityEnd != null){
			expertsRecord.setSeniorityEnd(seniorityEnd);
		}
		
		if(industryId != null){
			Industry industry = industryRepository.findOne(industryId);
			expertsRecord.setIndustry(industry);
		}
		if (cityId != null) {
			City city = cityRepository.findOne(cityId);
			experts.build(city);
		} 
		
		if(!StringUtils.isBlank(goodAt)){
			expertsRecord.setGoodAt(goodAt);
		}
		
		if(!StringUtils.isBlank(name)){
//			experts.setRealName(name);
			expertsRecord.setExpertsRealName(name);
		}
		
//		if(sex != null){
//			experts.setSex(sex);
//		}
		if(sex != null){
			expertsRecord.setExpertsSex(sex);
		}
		
		if(!StringUtils.isBlank(idCard)){
			experts.setIdCard(idCard);
		}
		
		if(!StringUtils.isBlank(description)){
			expertsRecord.setDescription(description);
		}
		
		if(avatar != null){
			String originalName = avatar.getOriginalFilename();
	        String extension = originalName.substring(originalName.lastIndexOf("."));
			String imgPath = SystemProperty.informationPrefix+ File.separator + experts.getId() + File.separator + TimeUtils.currentMillis() + extension;
			
			writeByteToFile(SystemProperty.storageFilePrefix+imgPath, avatar);
//			experts.setAvatar(imgPath);
			expertsRecord.setExpertsAvatar(imgPath);
		}
		expertsRecordListRepository.saveAndFlush(expertsRecord);
		List<Img> imgList = new ArrayList<Img>();
		if(certificateImg != null){
			for (int i = 0; i < certificateImg.length; i++) {
				if(certificateImg[i].getSize() == 0){
					continue;
				}
				String originalName = certificateImg[i].getOriginalFilename();
		        String extension = originalName.substring(originalName.lastIndexOf("."));
				String certificateImgPath = SystemProperty.informationPrefix+ File.separator + experts.getId() + TimeUtils.currentMillis()+ experts.getId() + i + extension;
				writeByteToFile(SystemProperty.storageFilePrefix+certificateImgPath, certificateImg[i]);
				Img dossier = Img.of(certificateImgPath.replaceAll("\\\\", "/"),expertsRecord);
				imgList.add(dossier);
			}
			expertsRecord.addImg(imgList);
		}
		experts.setHandle(2L);  //设置专家身份修改资料状态
		expertsRecord.addExperts(experts);
		expertsRecordListRepository.save(expertsRecord);
		return experts;
	}

	@Override
	public void delete(Long recordId) {
		expertsRecordListRepository.delete(recordId);
	}

	@Override
	public Boolean updateBatch(Long time) {
		return expertsListRepository.updateBatch(time);
	}

	@Override
	public List<String> findAndroidOrIosByCondition(AppType appType, String keyword, String active, Long expertState) {
		Page<Experts> page = expertsListRepository.queryByCondition(appType, keyword, active, expertState, null);
		 List<String> list = page.getContent().stream().map(s -> s.getAppToken()).collect(Collectors.toList());
		return list;
	}

	@Override
	public Experts saveAuth(Long id,boolean flag,String reason) {
		Experts experts = expertsListRepository.findOne(id);
		List<ExpertsRecord> expertsRecord = experts.getExpertsRecord();
		List newList = new ArrayList<>();
		if(flag){
			//专家资料审核通过  
			if(experts.getHandle() == 1L){
				for (ExpertsRecord record : expertsRecord) {
					record.setAuditState(true);
				}
			}
			//     专家修改资料审核通过        ||   专家第一次审核失败,再次审核     ||   专家修改资料失败后再次审核,成功
			if(experts.getHandle() == 2L || experts.getHandle() == -1L || experts.getHandle() == -2L){
				List<ExpertsRecord> list = experts.getExpertsRecord();
		    	Collections.sort(list);
		    	ExpertsRecord record = list .get(list.size()-1);
		    	record.setAuditState(true);
		    	list.remove(list.size()-1);
		    	newList.addAll(list);
				experts.removeAll(list);
				
				//将修改的头像/性别/真实姓名  设置到consumer中
				experts.setAvatar(record.getExpertsAvatar());
				experts.setSex(record.getExpertsSex());
				experts.setRealName(record.getExpertsRealName());
//				for (ExpertsRecord record : expertsRecord) {
//					if(record.getAuditState() == true && record.isActive()){
//						record.removeExperts(experts);
//						newList.add(record);
//					}else{
//						record.setAuditState(true);
//					}
//				}
			}
			experts.setHandle(3L);
		}else{
			//       专家审核资料失败           ||   专家审核资料再次失败           || 专家修改资料失败后再次审核,失败
			if(experts.getHandle() == 1L || experts.getHandle() == -1L  || experts.getHandle() == -2L){
				for (ExpertsRecord record : expertsRecord) {
					record.setReason(reason);
				}
				experts.setHandle(-1L);
			}
			//专家申请修改资料失败
			if(experts.getHandle() == 2L){
				for (ExpertsRecord record : expertsRecord) {
					record.setReason(reason);
				}
				experts.setHandle(-2L);
			}
		}
		Experts e = expertsListRepository.save(experts);
		expertsRecordListRepository.delete(newList);
		return e;
	}

	@Override
	public Experts fingByAuditState(Long id) {
		Experts experts = expertsListRepository.findOne(id);
		List<ExpertsRecord> expertsRecord = experts.getExpertsRecord();
		Collections.sort(expertsRecord);
		expertsRecord.get(0);
		return experts;
	}

	@Override
	public Experts saveAlipay(Long id, String alipayUserName) {
		Experts experts = expertsListRepository.findOne(id);
		experts.setAlipayUserName(alipayUserName);
		return expertsListRepository.save(experts);
	}

	@Override
	public Page findRecommendExpertsCondition(Long id, String evaluation,Pageable pageable) {
		evaluation = "desc";
		return expertsListRepository.findRecommendExperts(id.toString(),evaluation,pageable);
	}

	@Override
	public ExpertsShowVo findShow(Long id,Long expertsId) {
		Experts experts = expertsListRepository.findOne(expertsId);
		PageableResponse<CommentVo> commentVo = commentService.findByCondition(expertsId,new PageRequest(0,1));
		
		Double timesProportion;
		Long time = new java.util.Date().getTime();
		Date date = new Date(time);
		if(SystemProperty.consumerTimeForm <= time && time <= SystemProperty.consumerTimeTo){
			timesProportion = SystemProperty.consumerProportion;
		}else{
			Integer weeks = TimeUtils.getWeekOfDate(date);
			Integer hours = TimeUtils.getHourOfDate(date);
	    	Times times = timesService.findProportion(Long.valueOf(weeks), Long.valueOf(hours));
	    	timesProportion = times.getConsumerProportion();
		}
		
		Focus focus = focusRepository.findActivite(id, expertsId);
		Boolean actvie = false;
		if(focus != null){
			actvie = focus.isActive();
		}
		return ExpertsShowVo.of(experts, commentVo.getCollection(),timesProportion,actvie);
	}

	@Override
	public Experts updateIntegral(Long id, Double integral) {
		Experts experts = expertsListRepository.findOne(id);
		SendIntegral sendIntegral = new SendIntegral();
		sendIntegral.setName("手工调整");
		sendIntegral.setConsumer(experts);
		sendIntegral.setIntegral(integral-experts.getConsumerDetails().getIntegral());
		sendIntegralRepository.save(sendIntegral);
		experts.getConsumerDetails().setIntegral(integral);
		return expertsListRepository.save(experts);
	}
}

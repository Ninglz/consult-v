package com.dsecet.core.service.admin.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.nlpcn.commons.lang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.repository.SystemDefaultPropertyRepository;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.util.TimeUtils;

/**
 * @author: lxl
 */
@Service
@Transactional
public class SystemDefaultPropertyServiceImpl implements SystemDefaultPropertyService{

    @Autowired
    private SystemDefaultPropertyRepository systemDefaultPropertyRepository;


    @Override
    public SystemDefaultProperty save(Long id,Long freeTime,Long payTime,Long maxNum,
			 String cell,Double price,Double startPrice,Double proportion,Double consumerProportion,Double consumerPrice,Double consumerStartPrice,
			 Double freeIntegral,String timeForm,String timeTo,String consumerTimeForm,String consumerTimeTo) throws ParseException {
    	
		 SystemDefaultProperty property = systemDefaultPropertyRepository.findOne(id);
		 if(freeTime != null){
			 property.setFreeTime(freeTime);
		 }
		 
		 if(payTime != null){
			 property.setPayTime(payTime);
		 }
		 
		 if(maxNum != null){
			 property.setMaxNum(maxNum);
		 }
		 
		 if(freeIntegral != null){
			 property.setFreeIntegral(freeIntegral);
		 }
		 
		 if(StringUtil.isNotBlank(cell)){
			 property.setCell(cell);
		 }
		 
		 if(price != null){
			 property.setPrice(price);
		 }
		 
		 if(startPrice != null){
			 property.setStartPrice(startPrice);;
		 }
		 
		 if(proportion != null){
			 property.setProportion(proportion);;
		 }
		 
		 if(consumerProportion != null){
			 property.setConsumerProportion(consumerProportion);
		 }
		 
		 if(consumerPrice != null){
			 property.setConsumerPrice(consumerPrice);
		 }
		 
		 if(consumerStartPrice != null){
			 property.setConsumerStartPrice(consumerStartPrice);
		 }
		 
		 SimpleDateFormat sd = new SimpleDateFormat(TimeUtils.DATA_TIME_FROMAT);
		 if(!StringUtils.isBlank(timeForm)){
			 Date timeFormDate = sd.parse(timeForm);
			 property.setTimeForm(timeFormDate.getTime());
		 }
		 
		 if(!StringUtils.isBlank(timeTo)){
			 Date timeToDate = sd.parse(timeTo);
			 property.setTimeTo(timeToDate.getTime());
		 }
		 
		 if(!StringUtils.isBlank(consumerTimeTo)){
			 Date consumerTimeToDate = sd.parse(consumerTimeTo);
			 property.setConsumerTimeTo(consumerTimeToDate.getTime());
		 }
		 
		 if(!StringUtils.isBlank(consumerTimeForm)){
			 Date consumerTimeFromDate = sd.parse(consumerTimeForm);
			 property.setConsumerTimeForm(consumerTimeFromDate.getTime());
		 }
		 

        return systemDefaultPropertyRepository.save(property);
    }

    @Override
    public SystemDefaultProperty delete(Long id) {
        return null;
    }

    @Override
    public SystemDefaultProperty update(SystemDefaultProperty systemDefaultProperty) {
        if (null == systemDefaultProperty.getId() || systemDefaultProperty.getId() == 0L) {
            return null;
        }
        systemDefaultProperty.updateLastModified();
        return systemDefaultPropertyRepository.save(systemDefaultProperty);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemDefaultProperty> findAll() {
        return systemDefaultPropertyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SystemDefaultProperty getUsed() {
        return systemDefaultPropertyRepository.findByActive(true);
    }

    @Override
    public SystemDefaultProperty get(Long id) {
        return systemDefaultPropertyRepository.getOne(id);
    }
}

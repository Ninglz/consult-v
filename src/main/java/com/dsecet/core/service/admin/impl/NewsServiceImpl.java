package com.dsecet.core.service.admin.impl;

import static com.dsecet.core.service.admin.impl.FileConstants.HTML_SUFFIX;
import static com.dsecet.util.FileHelper.writeByteToFile;
import static com.dsecet.util.FileHelper.writerStringToFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.news.News;
import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.repository.api.NewsRepository;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.NewsService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.NewsVo;

/**
 * Created on 2016年3月22日
 * <p>Title:       咨询汇_新闻</p>
 * <p>Description: 新闻接口实现</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService{
	
	@Override
	public News findOne(Long id) {
		return newsRepository.findOne(id);
	}

	@Override
	public News save(News news) {
		return newsRepository.save(news);
	}

	@Override
	public Page<News> findPage(String title, Pageable page) {
		return newsRepository.findByTitleLikeAndActiveOrderByCreatedDesc("%"+(title==null?"":title)+"%",true, page);
	}

	@Override
	public News create(String title, String digest, MultipartFile titleImg, String content,Long industryId) {
		News news = new News();
		String storagePath = SystemProperty.newsUrl + File.separator + news.getSerial() + File.separator + news.getSerial() + HTML_SUFFIX;
		 String originalName = titleImg.getOriginalFilename();
         String extension = originalName.substring(originalName.lastIndexOf("."));
		String titleImgPath = SystemProperty.newsUrl + File.separator + news.getSerial() + File.separator + news.getSerial() + extension;
				
		 try{
			 	writeByteToFile(SystemProperty.storageFilePrefix+titleImgPath, titleImg);
	            writerStringToFile(SystemProperty.storageFilePrefix + storagePath, content);
	            news.setTitle(title);
	            news.setStoragePath(storagePath);
	            news.setDigest(digest);
	            news.setTitleImg(titleImgPath);
	            Industry industry = new Industry();
	            industry.setId(industryId);
	            news.setIndustry(industry);
	            return newsRepository.save(news);
	        }catch(Exception e){
	            return null;
	        }
	}

	@Override
	public PageableResponse<NewsVo> findPage(String title, String timeSort,Long industryId, Pageable page) {
		Page<News> newsPage = newsRepository.findPage(title, timeSort,industryId, page);
		List<NewsVo> newsVoList = newsPage.getContent().stream().map(
				n -> NewsVo.of(n)).collect(Collectors.toList());
		return PageableResponse.of(newsVoList, newsPage.getSize(), (int)newsPage.getTotalElements());
	}


	
    @Autowired
    private SystemDefaultPropertyService systemDefaultPropertyService;
    
    @Autowired
    private NewsRepository newsRepository;


	
	
}

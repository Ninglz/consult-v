//package com.dsecet.website.controller;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.ws.rs.FormParam;
//import javax.ws.rs.QueryParam;
//
//import com.dsecet.core.domain.mongo.TransientDataShare;
//import com.dsecet.core.domain.mongo.TrendDataShare;
//import com.dsecet.core.domain.system.SystemDefaultProperty;
//import com.dsecet.core.domain.version.AppVersion;
//import com.dsecet.core.mongo.repository.TransientDataShareRepository;
//import com.dsecet.core.mongo.repository.TrendDataShareRepository;
//import com.dsecet.core.service.VersionService;
//import com.dsecet.core.service.admin.SystemDefaultPropertyService;
//import com.dsecet.util.TimeUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * @author: lxp
// * Date: 2015/7/13 11:42
// * safeguard-v1
// */
//@Controller
//@RequestMapping(value = "share")
//public class ShareController{
//
//    @Autowired
//    private TransientDataShareRepository transientDataShareRepository;
//
//    @Autowired
//    private TrendDataShareRepository trendDataShareRepository;
//
//    @Autowired
//    private SystemDefaultPropertyService systemDefaultPropertyService;
//
//    @Autowired
//    private VersionService versionService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @SuppressWarnings("deprecation")
//	@RequestMapping(value = "/trend/{id}", method = RequestMethod.GET)
//    public ModelAndView trendData(@PathVariable("id") String id,Pageable pageable){
//        ModelAndView view = new ModelAndView("share/trend");
//        TrendDataShare data = trendDataShareRepository.findOne(id);
//        AppVersion appVersion = versionService.findLasterVersion();
//        String urlPrefix = null;
//        long lo = data.getCreated();
//        Date date = new Date(lo);
//        SystemDefaultProperty systemDefaultProperty = systemDefaultPropertyService.getUsed();
//        urlPrefix = systemDefaultProperty.getWebsiteStaticUrl() + systemDefaultProperty.getAppPrefix();
//        String downloadURL = urlPrefix + appVersion.getRelativePath();
//        view.addObject("apk_download", downloadURL);
//        view.addObject("data", data.getData());
//        view.addObject("dateType", data.getDays());
//        view.addObject("currentDate", TimeUtils.currentDate());
//        view.addObject("year",(date.getYear()+1900) );
//        view.addObject("month", (date.getMonth()+1));
//        return view;
//    }
//
//    @SuppressWarnings("deprecation")
//	@RequestMapping(value = "/transient/{id}", method = RequestMethod.GET)
//    public ModelAndView transientData(@PathVariable("id") String id){
//        ModelAndView view = new ModelAndView("share/transient");
//        TransientDataShare data = transientDataShareRepository.findOne(id);
//        AppVersion appVersion = versionService.findLasterVersion();
//        String urlPrefix = null;
//        long lo = data.getCreated();
//        Date date = new Date(lo);
//        SystemDefaultProperty systemDefaultProperty = systemDefaultPropertyService.getUsed();
//        urlPrefix = systemDefaultProperty.getWebsiteStaticUrl() + systemDefaultProperty.getAppPrefix();
//        String downloadURL = urlPrefix + appVersion.getRelativePath();
//        view.addObject("apk_download", downloadURL);
//        view.addObject("data", data.getData());
//        view.addObject("year",(date.getYear()+1900) );
//        view.addObject("month", (date.getMonth()+1));
//        return view;
//    }
//
//}

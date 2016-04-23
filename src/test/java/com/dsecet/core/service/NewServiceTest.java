//package com.dsecet.core.service;
//
//import com.dsecet.ApplicationConfig;
//import com.dsecet.core.domain.news.News;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//
///**
// * @author: lxp
// * Date: 2015/6/17 10:13
// * safeguard-v1
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ApplicationConfig.class)
//@TransactionConfiguration
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class NewServiceTest{
//
//    @Autowired
//    private NewsService newsService;
//
//    @Test
//    public void test_01(){
//        Pageable pageable = new PageRequest(0, 10);
//        Page<News> pageNews = newsService.findBySearchTextPageable("", pageable);
//        System.out.println("===========================================");
//        System.out.println("news page" + pageNews.getContent());
//        System.out.println("news size" + pageNews.getContent().size());
//    }
//
//
//    @Test
//    public void testBykeyword_query_02(){
//        Pageable pageable = new PageRequest(0, 10);
//        Page<News> pageNews = newsService.findBySearchTextPageable("我有健康呵呵PM", pageable);
//        System.out.println("===========================================");
//        System.out.println("news page" + pageNews.getContent());
//        System.out.println("news size" + pageNews.getContent().size());
//    }
//}

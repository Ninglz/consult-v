package com.dsecet.core.service;

import com.dsecet.ApplicationConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;
import java.util.Map;

/**
 * @author: lxp
 * Date: 2015/6/24 14:55
 * safeguard-v1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TransactionConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsumerServiceTest{

    @Autowired
    private ConsumerService consumerService;

    @Test
    @Rollback(false)
    public void batchSave_01(){
        for(int i=0; i < 5 ;i++){
            String cellNo = "1555555555" + i;
            //consumerService.register(cellNo, "123456");
        }
    }

}

package com.dsecet.core.service.admin.impl;

import com.dsecet.core.domain.admin.Resources;
import com.dsecet.core.repository.admin.ResourcesListRepository;
import com.dsecet.core.repository.admin.ResourcesListRepositoryImpl;
import com.dsecet.core.repository.admin.ResourcesRepository;
import com.dsecet.core.service.admin.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ting.yao
 * on 15/6/9.
 */
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService{

    @Autowired
    private ResourcesListRepository resourcesListRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Override
    @Transactional(readOnly = true)
    public Resources getById(Long id){
        return resourcesListRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Resources> getResources(Pageable pageable) {
        ResourcesListRepositoryImpl.ResourcesQueryBuilder builder = ResourcesListRepositoryImpl.ResourcesQueryBuilder.newBuilder();
        return resourcesListRepository.getByConditions(builder, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resources> getAllResources(){
        return resourcesRepository.findAll();
    }
}

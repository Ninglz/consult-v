package com.dsecet.core.service.admin;

import com.dsecet.core.domain.admin.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by ting.yao
 * on 15/6/9.
 */
public interface ResourcesService {

    Resources getById(Long id);

    List<Resources> getAllResources();

    Page<Resources> getResources(Pageable pageable);
}

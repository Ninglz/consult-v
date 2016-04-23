package com.dsecet.core.service.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.version.Version;

/**
 * 版本Service
 * @author heyue
 * */
public interface VersionService {

	Page<Version> findCondition(String name, Pageable pageable);

	Version create(String name, String description, boolean forceUpdate, MultipartFile new_apk);

}

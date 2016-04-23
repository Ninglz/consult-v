package com.dsecet.core.service.admin.impl;

import static com.dsecet.util.FileHelper.writeByteToFile;

import java.io.File;
import org.apache.commons.io.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.version.Version;
import com.dsecet.core.repository.VersionRepository;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.VersionService;
import com.dsecet.foundation.model.MediaItem;

@Service
@Transactional
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	private VersionRepository versionRepository;

	@Override
	public Page<Version> findCondition(String name, Pageable pageable) {
		return versionRepository.findCondition(name,pageable);
	}

	@Override
	public Version create(String name, String description, boolean forceUpdate, MultipartFile new_apk) {
		Version version = new Version();
		version.setName(name);
		version.setDescription(description);
		version.setForceUpdate(forceUpdate);
		versionRepository.save(version);
		
		String originalName = new_apk.getOriginalFilename();
		String extension = originalName.substring(originalName.lastIndexOf("."));
		String apkPath = SystemProperty.androidUrl + File.separator + version.getId() + File.separator+ version.getId() + extension;
		writeByteToFile(SystemProperty.storageFilePrefix + apkPath, new_apk);
		MediaItem downUrl = MediaItem.of(apkPath.replaceAll("\\\\", "/"), name, originalName);
		version.setDownUrl(downUrl);
		return versionRepository.save(version);
	}
}

package com.dsecet.core.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.record.Levels;
import com.dsecet.core.repository.admin.LevelsRepository;
import com.dsecet.core.service.admin.LevelsService;

@Service
@Transactional
public class LevelsServiceImpl implements LevelsService{

	@Override
	public Levels save(Levels levels) {
		return levelsRepository.save(levels);
	}
	
	@Override
	public void save(List<Levels> leveles) {
		levelsRepository.save(leveles);
		
	}

	@Override
	public Levels findOne(Long id) {
		return levelsRepository.findOne(id);
	}
	
	@Override
	public List<Levels> findAll() {
		return levelsRepository.findAll();
	}
	
	@Autowired
	private LevelsRepository levelsRepository;





}

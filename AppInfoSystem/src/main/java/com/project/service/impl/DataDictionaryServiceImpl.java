package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.project.dao.datadictionary.DataDictionaryMapper;
import com.project.entity.DataDictionary;
import com.project.service.DataDictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DataDictionaryServiceImpl implements DataDictionaryService {
	
	@Resource
	private DataDictionaryMapper mapper;
	
	@Override
	public List<DataDictionary> getDataDictionaryList(String typeCode)
			throws Exception {
		return mapper.getDataDictionaryList(typeCode);
	}

}

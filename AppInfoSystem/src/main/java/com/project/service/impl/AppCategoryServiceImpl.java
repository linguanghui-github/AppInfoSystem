package com.project.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.project.dao.appcategory.AppCategoryMapper;
import com.project.entity.AppCategory;
import com.project.service.AppCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppCategoryServiceImpl implements AppCategoryService {

	@Resource
	private AppCategoryMapper mapper;
	
	@Override
	public List<AppCategory> getAppCategoryListByParentId(Integer parentId)
			throws Exception {
		return mapper.getAppCategoryListByParentId(parentId);
	}

}

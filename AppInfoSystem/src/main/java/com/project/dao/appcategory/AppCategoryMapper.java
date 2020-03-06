package com.project.dao.appcategory;

import java.util.List;

import com.project.entity.AppCategory;
import org.apache.ibatis.annotations.Param;

public interface AppCategoryMapper {
	
	public List<AppCategory> getAppCategoryListByParentId(@Param("parentId") Integer parentId)throws Exception;
}

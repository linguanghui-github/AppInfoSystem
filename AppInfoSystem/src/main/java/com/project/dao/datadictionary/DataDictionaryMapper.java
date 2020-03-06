package com.project.dao.datadictionary;

import java.util.List;

import com.project.entity.DataDictionary;
import org.apache.ibatis.annotations.Param;

public interface DataDictionaryMapper {
	/**
	 * 数据字典查询
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	public List<DataDictionary> getDataDictionaryList(@Param("typeCode") String typeCode)throws Exception;
}

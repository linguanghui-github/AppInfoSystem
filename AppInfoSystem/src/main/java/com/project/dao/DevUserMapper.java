package com.project.dao;

import com.project.entity.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    DevUser login(@Param("devCode") String devCode)throws Exception;
}

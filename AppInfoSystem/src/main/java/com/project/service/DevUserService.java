package com.project.service;

import com.project.entity.DevUser;

public interface DevUserService {
    DevUser login(String name,String password)throws Exception;
}

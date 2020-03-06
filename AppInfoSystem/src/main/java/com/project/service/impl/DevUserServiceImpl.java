package com.project.service.impl;

import com.project.dao.DevUserMapper;
import com.project.entity.DevUser;
import com.project.service.DevUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DevUserServiceImpl implements DevUserService {
    @Resource
    DevUserMapper devUserMapper;
    @Override
    public DevUser login(String name,String password) throws Exception {
       DevUser devUser = devUserMapper.login(name);
       if (devUser!=null){

           if (devUser.getDevPassword().equals(password)){
               return devUser;
           }
       }
        return null;
    }
}

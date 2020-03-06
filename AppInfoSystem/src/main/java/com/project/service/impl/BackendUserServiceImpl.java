package com.project.service.impl;

import javax.annotation.Resource;

import com.project.dao.backenduser.BackendUserMapper;
import com.project.entity.BackendUser;
import com.project.service.BackendUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BackendUserServiceImpl implements BackendUserService {
	@Resource
	private BackendUserMapper mapper;
	
	@Override
	public BackendUser login(String userCode, String userPassword) throws Exception {
		BackendUser user = null;
		user = mapper.getLoginUser(userCode);
		//匹配密码
		if(null != user){
			if(!user.getUserPassword().equals(userPassword)){
				user = null;
			}
		}
		return user;
	}

}

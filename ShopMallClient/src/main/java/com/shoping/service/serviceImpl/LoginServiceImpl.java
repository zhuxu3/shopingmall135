package com.shoping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoping.dao.LoginDao;
import com.shoping.entity.UserCustomer;
import com.shoping.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao logindao;
	@Override
	public boolean longin(UserCustomer user) {
		//设置返回值，初始值为true（通过）
		boolean result = true;
		//验证账号密码是否正确 1，查询到数据返回true  2.没查询到数据将result设置为false
		UserCustomer customer=logindao.checking(user);
		if(customer==null){
			result= false;
		}
		return result;
	}

}

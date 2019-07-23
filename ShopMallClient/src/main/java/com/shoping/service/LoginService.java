package com.shoping.service;

import com.shoping.entity.UserCustomer;

public interface LoginService {
	/**
	 * 登陆验证密码账号是否正确
	 * @param user
	 * @return
	 */
	public boolean longin(UserCustomer user);

}

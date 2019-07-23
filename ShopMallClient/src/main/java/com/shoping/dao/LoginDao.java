package com.shoping.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shoping.entity.UserCustomer;

@Mapper
public interface LoginDao {

	public UserCustomer checking(@Param("user")UserCustomer user);
}

package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void signup(MUser user) {
		user.setDepartment_id(1);
		user.setRole("ROLE_GENERAL");
		userMapper.insertOne(user);
	}

	@Override
	public List<MUser> getUsers(MUser user) {
		return userMapper.findMany(user);
	}

	@Override
	public MUser getUserOne(String userId) {
		return userMapper.findOne(userId);
	}

	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		userMapper.updateOne(userId, password, userName);
		//int i = 1/0;
	}

	@Override
	public void deleteUserOne(String userId) {
		userMapper.deleteOne(userId);
	}

}

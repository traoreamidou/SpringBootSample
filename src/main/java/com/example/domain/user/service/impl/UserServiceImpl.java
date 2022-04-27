package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<MUser> getUsers() {
		return userMapper.findMany();
	}

	@Override
	public MUser getUserOne(String userId) {
		return userMapper.findOne(userId);
	}

	@Override
	public void updateUserOne(String userId, String password, String userName) {
		userMapper.updateOne(userId, password, userName);
	}

	@Override
	public void deleteUserOne(String userId) {
		int count = userMapper.deleteOne(userId);
	}

}

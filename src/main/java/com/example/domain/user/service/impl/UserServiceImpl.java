package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void signup(MUser user) {
		user.setDepartment_id(1);
		user.setRole("ROLE_ADMIN");
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
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
		String encryptPassword = encoder.encode(password);
		userMapper.updateOne(userId, encryptPassword, userName);
		//int i = 1/0;
	}

	@Override
	public void deleteUserOne(String userId) {
		userMapper.deleteOne(userId);
	}

	@Override
	public MUser getLoginUser(String userId) {
		return userMapper.findLoginUser(userId);
	}
}

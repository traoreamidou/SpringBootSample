package com.example.domain.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl2  implements UserService{
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    @Override
    public void signup(MUser user) {
        boolean exists = repo.existsById(user.getUserId());
        if(exists) {
            throw new DataAccessException("User already exists"){};
        }
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        repo.save(user);
    }

    public List<MUser> getUsers(MUser user) {
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();
        return repo.findAll(Example.of(user, matcher));
    }

    public MUser getUserOne(String userId) {
        return repo.findLoginUser(userId);
    }

    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        String encryptPassword = encoder.encode(password);
        repo.updateUser(userId, encryptPassword, userName);
    }

    @Transactional
    @Override
    public void deleteUserOne(String userId) {
        repo.deleteById(userId);
    }

    public MUser getLoginUser(String userId) {
        return repo.findLoginUser(userId);
    }

}

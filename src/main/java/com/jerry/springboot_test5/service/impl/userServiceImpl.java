package com.jerry.springboot_test5.service.impl;

import com.jerry.springboot_test5.dao.userDao;
import com.jerry.springboot_test5.entity.user;
import com.jerry.springboot_test5.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userDao userDao;

    @Override
    public List<user> getUserList() {
        return userDao.findAll();
    }

    @Override
    public user findUserById(long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public void saveUser(user user) {
        userDao.save(user);
    }

    @Override
    public void edit(user user) {
        userDao.save(user);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }


    @Override
    public user findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public Page<user> findAllOf(PageRequest request) {
        return userDao.findAll(request);
    }
}

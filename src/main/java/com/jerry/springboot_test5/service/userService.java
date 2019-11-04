package com.jerry.springboot_test5.service;

import com.jerry.springboot_test5.entity.user;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface userService {
    List<user> getUserList();

    user findUserById(long id);

    void saveUser(user user);

    void edit(user user);

    void delete(long id);

    user findUserByName(String username);

    Page<user> findAllOf(PageRequest request);


}

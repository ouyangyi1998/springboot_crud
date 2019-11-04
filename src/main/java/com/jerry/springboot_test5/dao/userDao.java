package com.jerry.springboot_test5.dao;

import com.jerry.springboot_test5.entity.user;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface userDao extends JpaRepository<user, Long> {
    @Query(nativeQuery = true, value = "select * from user where username=?1")
    user findUserByName(String username);

}

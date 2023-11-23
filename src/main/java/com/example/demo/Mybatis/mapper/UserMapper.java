package com.example.demo.Mybatis.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.Mybatis.DAO.User;



@Mapper
public interface UserMapper {

    // Find User
    User findUserById(String id);
    User findUserByName(String name);
    User findUserByIdAndPassword(@Param("id") String id, @Param("pw") String pw);
    int countUserById(String id);
    int countUserByName(String name);

    // Find All Users
    List<User> findAll();

    // Create User
    boolean createUser(User user);

    // Update User
    int updateUser(LinkedHashMap<String, Object> map);

    int updatePassword(LinkedHashMap<String, Object> map);

    // Delete User
    int deleteUser(String id);
    
}

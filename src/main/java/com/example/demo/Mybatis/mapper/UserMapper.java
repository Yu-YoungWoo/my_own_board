package com.example.demo.Mybatis.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.Mybatis.DAO.user;



@Mapper
public interface UserMapper {

    // Find User
    user findUserById(String id);
    user findUserByName(String name);
    user findUserByIdAndPassword(@Param("id") String id, @Param("pw") String pw);
    int countUserById(String id);
    int countUserByName(String name);

    // Find All Users
    List<user> findAll();

    // Create User
    boolean createUser(user user);

    // Update User
    int updateUser(LinkedHashMap<String, Object> map);

    int updatePassword(LinkedHashMap<String, Object> map);


    // Delete User
    String deleteUser(String u_id);
    
}

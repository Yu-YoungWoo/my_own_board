package com.example.demo.Service;

import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.demo.DTO.Request.basicInfoModifyForm;
import com.example.demo.DTO.Request.joinForm;
import com.example.demo.DTO.Request.passwordModifyForm;
import com.example.demo.Mybatis.DAO.user;
import com.example.demo.Mybatis.VO.UserRole;
import com.example.demo.Mybatis.mapper.UserMapper;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * 유저의 모든 정보를 가져오는 Mapper
     * @return List<user>
     */
    public List<user> findAll() {
        return userMapper.findAll();
    }

    public user findUserById(String id) {
        return userMapper.findUserById(id);
    }

    public user findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    /**
     * 유저 중복 체크를 위한 count
     * @param pri_no - User 테이블의 Id
     * @return - true or false
     */
    public String countUserById(String pri_no) {

        int findRows = userMapper.countUserById(pri_no);

        if(findRows == 0) {
            return "true";
        }
        return "false";
    }

    public String countUserByName(String name) {
        
        int findRows = userMapper.countUserByName(name);

        if(findRows == 0) {
            return "true";
        }

        return "false";
    }


    public LinkedHashMap<String, Object> createUser(joinForm form) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        int countIdRows = userMapper.countUserById(form.getId());
        int countNameRows = userMapper.countUserByName(form.getName());

        if(countIdRows >= 1) {
            System.out.println("중복된 아이디를 가지고 있는 유저가 존재함.");
            /* 중복 아이디 */
            map.put("duplicateUserByIdStatus", true);
            map.put("duplicateUserByIdFailMsg", "중복된 아이디입니다! 다른 아이디를 사용해주세요.");
        }

        if(countNameRows >= 1) {
            System.out.println("중복된 닉네임을 가지고 있는 유저가 존재함.");
            /* 중복 닉네임 */
            map.put("duplicateUserByNameStatus", true);
            map.put("duplicateUserByNameFailMsg", "중복된 닉네임입니다! 다른 닉네임을 사용해주세요.");
        }

        if(map.containsKey("duplicateUserByIdStatus") || map.containsKey("duplicateUserByNameStatus")) {
            return map;
        } else {
            ModelMapper modelMapper = new ModelMapper();
            user newUser = new user();
            
            modelMapper.map(form, newUser);
            
            // 비밀번호 encoding
            newUser.setPw(encoder.encode(form.getPw()));

            newUser.setCreateDate();
            newUser.setRole(UserRole.USER);

            // Mybatis Mapping
            userMapper.createUser(newUser);
            map.put("loginSuccessStatus", true);
            return map;
        }
    }

    public LinkedHashMap<String, Object> updateBasicInfo(basicInfoModifyForm form, String curId) {
        LinkedHashMap<String, Object> updateMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        user findUser = userMapper.findUserById(curId);
        user updateUser = new user();

        modelMapper.map(form, updateUser);
        updateUser.setPw(findUser.getPw());

        updateMap.put("user", updateUser);
        updateMap.put("curId", curId);

        int count = userMapper.countUserById(form.getId());
        int updateRows = userMapper.updateUser(updateMap);

        if(count >= 1) {
            updateMap.clear();
            
            updateMap.put("updateUserFailStatus", true);
            updateMap.put("updateUserFailMsg", "중복된 아이디는 허용하지 않습니다!");
            updateMap.put("user", findUser);

            return updateMap;
        } else if(updateRows == 0) {
            updateMap.clear();
            
            updateMap.put("updateUserFailStatus", true);
            updateMap.put("updateUserFailMsg", "업데이트 실패! 관리자에게 문의하세요!");
            updateMap.put("user", findUser);

            return updateMap;
        }
        return updateMap;
    }

    public LinkedHashMap<String, Object> updatePassword(passwordModifyForm form, String curId) {
        LinkedHashMap<String, Object> updateMap = new LinkedHashMap<>();
        
        updateMap.put("pw", encoder.encode(form.getNewPassword()));
        updateMap.put("curId", curId);

        int updateRows = userMapper.updatePassword(updateMap);

        if(updateRows == 0) {
            updateMap.clear();
            
            updateMap.put("updatePasswordFailStatus", true);
            updateMap.put("updatePasswordFailMsg", "업데이트 실패!");
        }

        return updateMap;
        
    }

    public String deleteUser(String id) {
        int deleteRows = userMapper.deleteUser(id);

        if(deleteRows == 1) {
            return "redirect:/login";
        } 
        
        return "redirect:/user-detail";
    }


    public LinkedHashMap<String, Object> createFailMsg(BindingResult bindingResult, String id, String type) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    
        /* 회원가입 - JOIN */
        if(type.toUpperCase().equals("JOIN")) {
            map.put("formValidFailStatus", true);
            map.put("formValidFailMsg", "회원가입 실패! 조건에 맞게 작성 해주세요.");
        }
        /* 유저 정보 수정 - BASIC-INFO-MODIFY */ 
        else if(type.toUpperCase().equals("BASIC-INFO-MODIFY")) {

            user findUser = userMapper.findUserById(id);
            map.put("user", findUser);
            map.put("updateUserFailStatus", true);
            map.put("updateUserFailMsg", "계정 업데이트 실패! 조건에 맞게 작성 해주세요.");
        }
        /* 비밀번호 수정 실패 - USER-PASSWORD-MODIFY */
        else if(type.toUpperCase().equals("USER-PASSWORD-MODIFY")) {
            map.put("passWordUpdateFailStatus", true);
        }
        /* 알수없는 에러 */ 
        else {
            map.put("unknownFailStatus", true);
            map.put("unknownFailMsg", "업데이트 실패! 관리자에게 문의하세요!");
        }

        return map;
    }

    public user returnUserByAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.getName() != null && auth.isAuthenticated()) {
            String id = auth.getName();
            user findUser = userMapper.findUserById(id);

            return findUser;
        }
        return null;
    }    
}

package com.pku.service.impl;

import com.pku.exception.PasswordIncorrectException;
import com.pku.exception.UserAlreadyExistException;
import com.pku.exception.UserNotFoundWException;
import com.pku.mapper.UserMapper;
import com.pku.pojo.dto.UserDTO;
import com.pku.pojo.entity.User;
import com.pku.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserDTO userDTO) {
        if(userMapper.getByUsername(userDTO) != null){
            throw new UserAlreadyExistException("User already exists!!!");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setRegisterTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public User login(UserDTO userDTO) {
        User user = userMapper.getByUsername(userDTO);
        if(user == null){
            throw new UserNotFoundWException("User Not Found!!!");
        }
        else if(!user.getPassword().equals(userDTO.getPassword())){
            throw new PasswordIncorrectException("Password Incorrect!!!");
        }
        return userMapper.getByUsername(userDTO);
    }
}

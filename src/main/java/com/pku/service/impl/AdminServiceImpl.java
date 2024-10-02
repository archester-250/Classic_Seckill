package com.pku.service.impl;

import com.pku.exception.PasswordIncorrectException;
import com.pku.exception.UserNotFoundWException;
import com.pku.mapper.AdminMapper;
import com.pku.pojo.dto.AdminDTO;
import com.pku.pojo.dto.UserDTO;
import com.pku.pojo.entity.Admin;
import com.pku.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(AdminDTO adminDTO) {
        Admin admin = adminMapper.getByName(adminDTO);
        if(admin == null){
            throw new UserNotFoundWException("Admin Not Found!!!");
        }
        else if(!admin.getPassword().equals(adminDTO.getPassword())){
            throw new PasswordIncorrectException("Password Incorrect!!!");
        }
        return adminMapper.getByName(adminDTO);
    }
}

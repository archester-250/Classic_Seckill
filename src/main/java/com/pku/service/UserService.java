package com.pku.service;

import com.pku.pojo.dto.UserDTO;
import com.pku.pojo.entity.User;

public interface UserService {
    void register(UserDTO userDTO);
    User login(UserDTO userDTO);
}

package com.pku.service;

import com.pku.pojo.dto.AdminDTO;
import com.pku.pojo.entity.Admin;

public interface AdminService {
    Admin login(AdminDTO adminDTO);
}

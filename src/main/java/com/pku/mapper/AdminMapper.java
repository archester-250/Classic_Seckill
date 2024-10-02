package com.pku.mapper;

import com.pku.pojo.dto.AdminDTO;
import com.pku.pojo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admins where name = #{name}")
    Admin getByName(AdminDTO adminDTO);
}

package com.pku.mapper;

import com.pku.pojo.dto.UserDTO;
import com.pku.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    void insert(User user);

    @Select("select * from users where username = #{username}")
    User getByUsername(UserDTO userDTO);
}

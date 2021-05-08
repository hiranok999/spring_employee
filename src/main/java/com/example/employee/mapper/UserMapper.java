package com.example.employee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.employee.model.UserModel;

/**
 * t_userのSQLを書くクラス
 */
@Mapper
public interface UserMapper {

    // 全件取得
    @Select("SELECT id, name, password, enabled, authority FROM public.t_user where name = #{name}")
    public UserModel selectByUser(@Param("name") String name);
}

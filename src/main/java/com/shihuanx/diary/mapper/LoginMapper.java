package com.shihuanx.diary.mapper;

import com.shihuanx.diary.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from user where phone_number = #{phoneNumber};")
    User getUserByPhoneNumber(User user);

    @Insert("insert into user (phone_number, password) values (#{phoneNumber},#{password});")
    void addUser(User user);

    @Select("select * from user where phone_number=#{phoneNumber} and password=#{password};")
    User getUserByPhoneNumberAndPassword(User user);
}

package com.ks.ks.mapper;

import com.ks.ks.bean.history;
import com.ks.ks.bean.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface adminMapper {
    @Select("SELECT * FROM `user` ")
    List<user> userList();

    @Select("SELECT COUNT(1) FROM `admin`  WHERE adminName = #{name} AND adminPwd = #{pwd}")
    int adminLogin(@Param("name") String name, @Param("pwd") String pwd);

    @Delete("DELETE  FROM user WHERE userId=#{userId}")
    int deleteUser(@Param("userId")int userId);

    @Insert("INSERT INTO user(userName,userPwd)  VALUES (#{name},#{pwd})")
    int addUser(@Param("name") String name, @Param("pwd") String pwd);


    //获取未开奖信息
    @Select("SELECT * FROM history WHERE betType=0")
    List<history> getHistoryList();

}

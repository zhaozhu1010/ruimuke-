package com.ks.ks.mapper;

import com.ks.ks.bean.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


//用户的接口
@Mapper
public interface userMapper {
    @Select("SELECT COUNT(1) FROM `user`  WHERE userName = #{name} AND userPwd = #{pwd}")
    int userLogin(String name, String pwd);


    @Select("SELECT  * FROM `user`  WHERE userName = #{name} ")
    user findUserByName(String name);


    @Insert("INSERT INTO history(userId,betNum,betType) VALUES (#{userId},#{betNum},#{betType})")
    int addBetHistory(@Param("userId") int userId, @Param("betNum") int betNum, @Param("betType") int betType);
}

package com.ks.ks.service;


import com.ks.ks.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ks.ks.bean.user;

@Service
public class userService implements userMapper{
    @Autowired
    userMapper userMapper;


    public int userLogin(String name,String pwd){

        return userMapper.userLogin(name,pwd);
    }

    public user findUserByName(String  name){
        return userMapper.findUserByName(name);
    }

    @Override
    public int addBetHistory(int userId, int betNum, int betType) {
        return userMapper.addBetHistory(userId,betNum,betType);
    }


}

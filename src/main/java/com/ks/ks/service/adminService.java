package com.ks.ks.service;

import com.ks.ks.bean.history;
import com.ks.ks.bean.user;
import com.ks.ks.mapper.adminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminService implements adminMapper {

    @Autowired
    adminMapper adminMapper;

    @Override
    public List<user> userList() {
        return adminMapper.userList();
    }

    @Override
    public int adminLogin(String name, String pwd) {
        return adminMapper.adminLogin(name,pwd);
    }

    @Override
    public int deleteUser(int userId) {
        return adminMapper.deleteUser(userId);
    }

    @Override
    public int addUser(String name, String pwd) {
        return adminMapper.addUser(name,pwd);
    }

    @Override
    public List<history> getHistoryList() {
        return adminMapper.getHistoryList();
    }


}

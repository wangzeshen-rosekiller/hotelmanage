package com.example.main.serviceImp;

import com.example.main.mapper.UserMapper;
import com.example.main.model.UserLogin;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLogin Login(String useremail) {
        // TODO Auto-generated method stub
        return userMapper.Login(useremail);
    }

    @Override
    public UserLogin Login2(String useremail, String userpassword) {
        // TODO Auto-generated method stub
        return userMapper.Login2(useremail,userpassword);
    }

    @Override
    public void reg(String qqemail, String password) {
        // TODO Auto-generated method stub
        userMapper.reg(qqemail,password);
    }
}

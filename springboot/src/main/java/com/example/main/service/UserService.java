package com.example.main.service;

import com.example.main.model.UserLogin;
import org.springframework.stereotype.Service;


public interface UserService {
    UserLogin Login(String useremail);

    UserLogin Login2(String useremail,String userpassword);

    void reg(String qqemail, String password);
}

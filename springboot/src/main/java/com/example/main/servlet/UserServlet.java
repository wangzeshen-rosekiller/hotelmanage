package com.example.main.servlet;

import com.example.main.model.UserLogin;
import com.example.main.service.SendEmailService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController

public class UserServlet {

    @Autowired
    private UserService userservice;

    @Autowired
    private SendEmailService sendemailservice;

    //检查登陆密码是否正确
    @RequestMapping(value = "checkpassword.do",produces = "application/json;charset=UTF-8")
    /*public String emailCheck(String loginemail,String loginpassword)  {*/
    public String emailCheck(@RequestBody Map map)  {
        String loginemail= (String) map.get("loginemail");
        String loginpassword=(String)map.get("loginpassword");
        System.out.println(loginpassword+"xxx"+loginemail);
        UserLogin user=userservice.Login2(loginemail+"@qq.com",loginpassword);
        if(user!=null){
            System.out.println("密码正确"+loginemail+loginpassword);
            return "success";
        }else{
            System.out.println("密码错误"+loginemail+loginpassword);
            return "fail";
        }
    }

    //注册验证
    @RequestMapping(value="sendemail.do",produces = "application/json;charset=UTF-8")
    public String sendemail(@RequestBody Map map) {
        String qqemail= (String) map.get("qqemail");
        //System.out.println(qqemail);
        Random ra =new Random();
        int num=ra.nextInt(1000000)%(1000000-100000+1)+100000;

        //验证用户是否存在
        UserLogin user=userservice.Login(qqemail+"@qq.com");
        if(user!=null){
            return "fail";
        }else{
            try {
                sendemailservice.setemail(qqemail+"@qq.com", num);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(num);
            return String.valueOf(num);
        }
    }

    //注册完成，录入数据库
    @RequestMapping(value="successreg.do",produces = "application/json;charset=UTF-8")
    public void successreg(@RequestBody Map map)
    {
        String qqemail=(String)map.get("qqemail");
        String password=(String)map.get("password");
        //System.out.println(qqemail);
        userservice.reg(qqemail+"@qq.com",password);
    }

}

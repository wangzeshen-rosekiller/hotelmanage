package com.example.main.servlet;

import com.example.main.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class AdminServlet {

    private AdminService adminservice;

    //登陆跳转管理员首页
    @RequestMapping(value="gotoadminmanage.do")
    public String gotohotelmanage(String admin)
    {
        /*if(admin==null)
        {
            session.setAttribute("admin", session.getAttribute("admin"));
        }
        else
        {
            session.setAttribute("admin", admin);
        }*/
        return "adminmanage";
    }

    //删除用户信息
    @RequestMapping(value="deluser.do")
    public String deluser(String useremail)
    {
        adminservice.deluser(useremail);
        System.out.println("删除成功");
        return "success";
    }

    //获取全部用户
    @RequestMapping(value="getalluser.do",produces = "text/html;charset=UTF-8")
    public String getallorder(String admin)
    {
        List<Map> allorder=adminservice.getalluser();
        /*JSONArray json=JSONArray.fromObject(allorder);*/
       /* return json.toString();*/
        //json数据未处理！！！！！！！！！！！！！！！！！！！！！！！！！！
        return "xxx";
    }
}

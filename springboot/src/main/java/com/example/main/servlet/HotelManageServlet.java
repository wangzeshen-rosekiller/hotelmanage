package com.example.main.servlet;

import com.example.main.service.HotelOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class HotelManageServlet {
    @Resource
    private HotelOrderService hotelorderservice;
    //跳转首页
    @RequestMapping(value="gotohotelmanage.do")
    public String gotohotelmanage(String hotelid)
    {
       /* if(hotelid==null)
        {
            session.setAttribute("hotelid", session.getAttribute("hotelid"));
        }
        else
        {
            session.setAttribute("hotelid", hotelid);
        }*/
        return "hotelmanage";
    }

    //跳转房型详情页
    @RequestMapping(value="gotohotelroomtypemanage.do")
    public String gotohotelroomtypemanage()
    {
        /*session.setAttribute("hotelid", session.getAttribute("hotelid"));*/
        return "hotelroomtypemanage";
    }

    //跳转评论管理页
    @RequestMapping(value="gotohotelcommentmanage.do")
    public String gotohotelcommentmanage()
    {
        /*session.setAttribute("hotelid", session.getAttribute("hotelid"));*/
        return "hotelcommentmanage";
    }

    //跳转订单管理页
    @RequestMapping(value="gotohotelordermanage.do")
    public String gotohotelordermanage()
    {
       /* session.setAttribute("hotelid", session.getAttribute("hotelid"));*/
        return "hotelordermanage";
    }

    //获取全部订单

    @RequestMapping(value="getallorder.do",produces = "text/html;charset=UTF-8")
    public String getallorder(String hotelid)
    {
        List<Map> allorder=hotelorderservice.getallorder(hotelid);
        /*JSONArray json=JSONArray.fromObject(allorder);
        return json.toString();*/
        //json数据未处理！！！！！！！！！！！！！！！！！！！！！！！！！！1
        return "xxx";
    }

    //获取用户全部已评论订单
    @RequestMapping(value="managegetallcomment.do",produces = "text/html;charset=UTF-8")
    public String managegetallcomment(String hotelid)
    {
        List<Map> allcomment=hotelorderservice.managegetallcomment(hotelid);
        /*JSONArray json=JSONArray.fromObject(allcomment);*/
        //System.out.println(json.toString());
        /*return json.toString();*/
        //json数据未处理！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        return "xxx";
    }

    //回复评论提交
    @RequestMapping(value="postreplycomment.do")
    public String postreplycomment(String orderid,String content)
    {
        hotelorderservice.updatereply(orderid,content,"已回复");
        return "hotelcommentmanage";
    }
}

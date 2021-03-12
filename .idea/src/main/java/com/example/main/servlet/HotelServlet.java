package com.example.main.servlet;

import com.example.main.model.Comment;
import com.example.main.model.HotelOrder;
import com.example.main.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController
public class HotelServlet {

    @Autowired
    private HotelOrderService hotelorderservice;

    //获取所有评论信息
    @RequestMapping(value="getallcomment.do",produces = "application/json;charset=UTF-8")
    public List<Map> getallcomment(@RequestBody Map map)
    {
        String hotelid=String.valueOf(map.get("hotelid"));
        //System.out.println(hotelid);
        List<Map> allcomment=hotelorderservice.getallcomment(hotelid);
        //System.out.println(allcomment);
        return allcomment;
    }

    //获取全部订单
    @RequestMapping(value="getallbuy.do",produces = "application/json;charset=UTF-8")
    public List<Map> getallbuy(@RequestBody Map map)
    {
        String qqemail=(String) map.get("qqemail");
        //System.out.println(qqemail+"@qq.com");
        List<Map> allbuy=hotelorderservice.getallbuy(qqemail+"@qq.com");
        //System.out.println(allbuy);
        return allbuy;
    }

    //产生订单编号，并录入数据库
    @RequestMapping(value="payfinish.do")
    public void payfinish(@RequestBody Map map)
    {
        String hotelid=(String)map.get("hotelid");
        String user=(String)map.get("user")+"@qq.com";
        String startvalue=(String)map.get("startvalue");
        String endvalue=(String)map.get("endvalue");
        int num= (int) map.get("num");
        String name=(String)map.get("name");
        int totalprice= (int) map.get("totalprice");
        String id=(String)map.get("id");
        String chinesename=(String)map.get("chinesename");
        String roomtype=(String)map.get("roomtype");
        String orderid=hotelid+new Random().nextInt(1000);
        //System.out.println(chinesename);
        hotelorderservice.insert(hotelid,user,orderid,startvalue,
                endvalue,num,name,id,totalprice,chinesename,roomtype,"预定成功");
    }



    //删除订单
    @RequestMapping(value="delorder.do",produces = "application/json;charset=UTF-8")
    public void delorderid(@RequestBody Map map)
    {
        String orderid=(String)map.get("orderid");
        hotelorderservice.delorder(orderid);
    }

    //取消或完成订单
    @ResponseBody
    @RequestMapping(value="updateorder.do",produces = "application/json;charset=UTF-8")
    public void updateorderid(@RequestBody Map map)
    {
        int statue= (int) map.get("statue");
        String orderid=(String)map.get("orderid");
        System.out.println(statue);
        hotelorderservice.updateorder(statue,orderid);
    }

    //获取评论信息
    @RequestMapping(value="comment.do")
    public Comment comment(@RequestBody Map map)
    {
        String orderid=(String)map.get("orderid");
        Comment comment=hotelorderservice.comment(orderid);
        //System.out.println(comment.hotelid);
        return comment;
    }

    //上传评论
    @RequestMapping(value="postcomment.do")
    public void postcomment(@RequestBody Map map)
    {
        String orderid=(String)map.get("orderid");
        String hotelid=(String)map.get("hotelid");
        String pointV1=String.valueOf(map.get("pointV1"));
        String pointV2=String.valueOf(map.get("pointV2"));
        String pointV3=String.valueOf(map.get("pointV3"));
        String content=(String)map.get("content");
        //System.out.println(orderid+" "+hotelid+" "+pointV1+" "+content);
        hotelorderservice.insertcomment(orderid,hotelid,pointV1,pointV2,pointV3,content);
        hotelorderservice.updateorder(3,orderid);
    }



    @RequestMapping(value="getallpoint.do",produces = "text/html;charset=UTF-8")
    public String getallpoint(String hotelid,HttpServletResponse response,HttpServletRequest request,HttpSession session,Model model)
    {
//			List<Map> allcomment=hotelorderservice.getallcomment(hotelid);
//			JSONArray json=JSONArray.fromObject(allcomment);
//			System.out.println(json.toString());
        Map map=hotelorderservice.getallpoint(hotelid);
       /* JSONObject json=JSONObject.fromObject(map);
        return json.toString();*/
        //json数据未处理！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
       return "xxx";
    }


}

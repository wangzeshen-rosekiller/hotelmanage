package com.example.main.serviceImp;

import com.example.main.mapper.HotelMapper;
import com.example.main.model.Comment;
import com.example.main.model.HotelOrder;
import com.example.main.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelOrderServiceImp implements HotelOrderService {
    @Autowired
    private HotelMapper hotelmapper;
    @Override
    public int insert(String hotelid, String user, String orderid, String startvalue, String endvalue, int num, String name, String id, int totalprice, String chinesename, String roomtype, String statue) {
        return hotelmapper.insert(hotelid,user,orderid,
                startvalue,endvalue,num,name,id,totalprice,chinesename,roomtype,statue);
    }

    @Override
    public List<Map> getallbuy(String qqemail) {
        return hotelmapper.findbuyall(qqemail);
    }

    @Override
    public int delorder(String orderid) {
        int num=hotelmapper.delorder(orderid);
        //System.out.println(num+orderid);
        return num;
    }

    @Override
    public int updateorder(int statue, String orderid) {
        int num=0;
        //取消订单
        if(statue==1){num=hotelmapper.updateorder("已取消",orderid);}
        else if(statue==3)
        {
            num=hotelmapper.updateorder("已评价",orderid);
        }
        //完成订单
        else{num=hotelmapper.updateorder("已完成",orderid);}
        return num;
    }

    @Override
    public Comment comment(String orderid) {
        return hotelmapper.comment(orderid);
    }

    @Override
    public int insertcomment(String orderid, String hotelid, String pointV1, String pointV2, String pointV3, String content) {
        return hotelmapper.insertcomment(orderid,hotelid,pointV1,pointV2,pointV3,content);
    }

    @Override
    public List<Map> getallcomment(String hotelid) {
        return hotelmapper.findcommentall(hotelid);
    }

    @Override
    public Map getallpoint(String hotelid) {
        List<Map> lm=hotelmapper.findcommentall(hotelid);
        double totalpointV1=0;
        double totalpointV2=0;
        double totalpointV3=0;
        for(int t=0;t<lm.size();t++)
        {
            totalpointV1+=Double.valueOf((String) lm.get(t).get("pointV1"));
            totalpointV2+=Double.valueOf((String) lm.get(t).get("pointV2"));
            totalpointV3+=Double.valueOf((String) lm.get(t).get("pointV3"));
            //System.out.println(totalpointV1+" "+totalpointV2+" "+totalpointV1);
        }
        totalpointV1/=lm.size();
        totalpointV2/=lm.size();
        totalpointV3/=lm.size();
        double totalpoint=(totalpointV1+totalpointV2+totalpointV3)/3;
        String $totalpointV1=String.format("%.2f", totalpointV1);
        String $totalpointV2=String.format("%.2f", totalpointV2);
        String $totalpointV3=String.format("%.2f", totalpointV3);
        String $totalpoint=String.format("%.2f", totalpoint);
        //System.out.println(totalpoint);
        Map map=new HashMap();
        map.put("totalpointV1", $totalpointV1);
        map.put("totalpointV2", $totalpointV2);
        map.put("totalpointV3", $totalpointV3);
        map.put("totalpoint", $totalpoint);
        map.put("totalcomment",lm.size());
        return map;
    }

    @Override
    public List<Map> getallorder(String hotelid) {
        return hotelmapper.findorderall(hotelid);
    }

    @Override
    public List<Map> managegetallcomment(String hotelid) {
        return hotelmapper.managecommentall(hotelid);
    }

    @Override
    public String findcontent(String orderid) {
        return hotelmapper.findcontent(orderid);
    }

    @Override
    public int updatereply(String orderid, String reply, String statue) {
        return hotelmapper.updatereply(orderid,reply,statue);
    }
}

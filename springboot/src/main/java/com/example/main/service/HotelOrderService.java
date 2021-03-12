package com.example.main.service;

import com.example.main.model.Comment;
import com.example.main.model.HotelOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HotelOrderService {

    int insert(String hotelid, String user, String orderid,
               String startvalue, String endvalue, int num,
               String name, String id, int totalprice,
               String chinesename, String roomtype, String statue);

    List<Map> getallbuy(String qqemail);

    int delorder(String orderid);

    int updateorder(int statue, String orderid);

    Comment comment(String orderid);

    int insertcomment(String orderid,String hotelid, String pointV1, String pointV2,
                      String pointV3, String content);

    List<Map> getallcomment(String hotelid);

    Map getallpoint(String hotelid);

    List<Map> getallorder(String hotelid);

    List<Map> managegetallcomment(String hotelid);

    String findcontent(String orderid);

    int updatereply(String orderid, String reply, String statue);
}

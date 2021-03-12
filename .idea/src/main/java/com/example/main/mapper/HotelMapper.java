package com.example.main.mapper;

import com.example.main.model.Comment;
import com.example.main.model.HotelOrder;
import com.example.main.model.UserLogin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface HotelMapper {
    @Select("select hotelcomment.pointV1,hotelcomment.pointV2,hotelcomment.pointV3,hotelorder.startvalue,hotelorder.useremail,hotelcomment.content,hotelcomment.reply" +
            "  from hotelcomment,hotelorder " +
            "  where hotelcomment.hotelid=#{hotelid} and hotelcomment.orderid=hotelorder.orderid")
    @Results(id = "getallcomment",value = {
            @Result(property = "orderid",column = "orderid"),
            @Result(property = "hotelid",column = "hotelid"),
            @Result(property = "pointV1",column = "pointV1"),
            @Result(property = "pointV2",column = "pointV2"),
            @Result(property = "pointV3",column = "pointV3"),
            @Result(property = "content",column = "content"),
            @Result(property = "statue",column = "statue"),
            @Result(property = "reply",column = "reply")
    })
    @ResultType(Map.class)
    public List<Map> findcommentall(@Param("hotelid")String hotelid);

    @Insert("insert into hotelorder(hotelid,useremail,orderid,startvalue,endvalue,num,name,id,totalprice,chinesename,roomtype,statue) values(#{hotelid},#{user},#{orderid},#{startvalue},#{endvalue},#{num},#{name},#{id},#{totalprice},#{chinesename},#{roomtype},#{statue})")
    public int insert(@Param("hotelid")String hotelid,
                      @Param("user")String user,
                      @Param("orderid")String orderid,
                      @Param("startvalue")String startvalue,
                      @Param("endvalue")String endvalue,
                      @Param("num")int num,
                      @Param("name")String name,
                      @Param("id")String id,
                      @Param("totalprice")int totalprice,
                      @Param("chinesename")String chinesename,
                      @Param("roomtype")String roomtype,
                      @Param("statue")String statue);

    @Select("select * from hotelorder where useremail=#{useremail}")
    @Results(id = "getallbuy",value = {
            @Result(property = "orderid",column = "orderid"),
            @Result(property = "hotelid",column = "hotelid"),
            @Result(property = "useremail",column = "useremail"),
            @Result(property = "startvalue",column = "startvalue"),
            @Result(property = "endvalue",column = "endvalue"),
            @Result(property = "num",column = "num"),
            @Result(property = "name",column = "name"),
            @Result(property = "totalprice",column = "totalprice"),
            @Result(property = "chinesename",column = "chinesename"),
            @Result(property = "roomtype",column = "roomtype"),
            @Result(property = "statue",column = "statue")
    })
    @ResultType(Map.class)
    public List<Map> findbuyall(@Param("useremail")String qqemail);

    @Delete("delete from hotelorder where orderid=#{orderid}")
    public int delorder(@Param("orderid")String orderid);

    @Update("update hotelorder set statue=#{statue} where orderid=#{orderid}")
    public int updateorder(@Param("statue")String statue,@Param("orderid")String orderid);


    @Select("select * from hotelorder where orderid=#{orderid}")
    @Results(id = "comments",value = {
            @Result(property = "chinesename",column = "chinesename"),
            @Result(property = "orderid",column = "orderid"),
            @Result(property = "hotelid",column = "hotelid"),
            @Result(property = "startvalue",column = "startvalue"),
            @Result(property = "endvalue",column = "endvalue"),
            @Result(property = "name",column = "name"),
            @Result(property = "roomtype",column = "roomtype"),
            @Result(property = "totalprice",column = "totalprice"),
            @Result(property = "num",column = "num")
    })
    @ResultType(Comment.class)
    public Comment comment(@Param("orderid")String orderid);

    @Insert("insert into hotelcomment(orderid,hotelid,pointV1,pointV2,pointV3,content) values(#{orderid},#{hotelid},#{pointV1},#{pointV2},#{pointV3},#{content})")
    public int insertcomment(
            @Param("orderid")String orderid,
            @Param("hotelid")String hotelid,
            @Param("pointV1")String pointV1,
            @Param("pointV2")String pointV2,
            @Param("pointV3")String pointV3,
            @Param("content")String content);



    @Select("select * from hotelorder where hotelid=#{hotelid} ")
    @ResultType(HashMap.class)
    public List<Map> findorderall(@Param("hotelid")String hotelid);

    @Select("select hotelcomment.orderid,hotelcomment.pointV1,hotelcomment.pointV2,hotelcomment.pointV3,hotelorder.startvalue,hotelorder.useremail,hotelcomment.content,hotelorder.chinesename,hotelcomment.statue" +
            "  from hotelcomment,hotelorder" +
            "  where hotelcomment.hotelid=#{hotelid} and hotelcomment.orderid=hotelorder.orderid")
    @ResultType(HashMap.class)
    public List<Map> managecommentall(@Param("hotelid")String hotelid);

    @Select("select content from hotelcomment where orderid=#{orderid}")
    @ResultType(String.class)
    public String findcontent(@Param("orderid")String orderid);

    @Update("update hotelcomment set statue=#{statue},reply=#{reply} where orderid=#{orderid}")
    public int updatereply(@Param("orderid")String orderid,
                           @Param("reply")String reply,
                           @Param("statue")String statue);
}

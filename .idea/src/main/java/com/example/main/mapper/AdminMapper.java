package com.example.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminMapper {

    @Select("delete from userlogin where useremail=#{useremail}")
    void deluser(@Param("useremail")String useremail);

    @Select("select * from userlogin")
    List<Map> getalluser();
}

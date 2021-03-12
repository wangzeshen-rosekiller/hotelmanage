package com.example.main.mapper;

import com.example.main.model.UserLogin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    @Select("select * from userlogin where useremail=#{useremail}")
    @ResultType(UserLogin.class)
    UserLogin Login(String useremail);

    @Select(" select * from userlogin where useremail=#{useremail} and userpassword=#{userpassword}")
    @Results(id = "logins",value = {
            @Result(property = "useremail",column = "useremail"),
            @Result(property = "userpassword",column = "userpassword"),
    })
    @ResultType(UserLogin.class)
    UserLogin Login2(@Param("useremail") String useremail, @Param("userpassword") String userpassword);

    @Insert("insert into userlogin(useremail,userpassword) values(#{useremail},#{userpassword})")
    void reg(@Param("useremail") String qqemail,@Param("userpassword") String password);
}

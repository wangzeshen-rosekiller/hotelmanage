package com.example.main.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserLogin {
    @Id
    public String useremail;
    public String userpassword;

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}

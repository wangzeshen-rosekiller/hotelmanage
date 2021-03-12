package com.example.main.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HotelComment {
    @Id
    public String orderid;
    public String pointV1;
    public String pointV2;
    public String pointV3;
    public String content;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPointV1() {
        return pointV1;
    }

    public void setPointV1(String pointV1) {
        this.pointV1 = pointV1;
    }

    public String getPointV2() {
        return pointV2;
    }

    public void setPointV2(String pointV2) {
        this.pointV2 = pointV2;
    }

    public String getPointV3() {
        return pointV3;
    }

    public void setPointV3(String pointV3) {
        this.pointV3 = pointV3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

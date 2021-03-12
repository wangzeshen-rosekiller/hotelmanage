package com.example.main.serviceImp;

import com.example.main.mapper.AdminMapper;
import com.example.main.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class AdminServiceImp implements AdminService {

    private AdminMapper adminmapper;
    @Override
    public void deluser(String useremail) {
        adminmapper.deluser(useremail);
    }

    @Override
    public List<Map> getalluser() {
        return adminmapper.getalluser();
    }
}

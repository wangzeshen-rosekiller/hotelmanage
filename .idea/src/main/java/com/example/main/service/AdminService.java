package com.example.main.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AdminService {
    void deluser(String useremail);

    List<Map> getalluser();
}

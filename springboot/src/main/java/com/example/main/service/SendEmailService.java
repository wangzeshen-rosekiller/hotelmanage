package com.example.main.service;

import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

@Service
public interface SendEmailService {
    public void setemail(String clientemail,int num) throws Exception;
    public MimeMessage createMimeMessage(Session session, String sendMail,String clientemail,int num) throws Exception;
}

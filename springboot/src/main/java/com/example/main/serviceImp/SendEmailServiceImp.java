package com.example.main.serviceImp;

import com.example.main.service.SendEmailService;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

@Service
public class SendEmailServiceImp implements SendEmailService {
    // 发件人的 邮箱 和 密码
    public static String myEmailAccount = "";
    public static String myEmailPassword = "";
    public static String myEmailSMTPHost = "smtp.qq.com";// qq邮箱的 SMTP 服务器地址为: smtp.qq.com


    @Override
    public void setemail(String clientemail, int num) throws Exception {
        // TODO Auto-generated method stub
        //创建参数配置, 用于连接邮件服务器的参数配置
        System.out.println("开始发送"+clientemail);
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        //开启 SSL 连接,基于 JavaMail 的 Java 邮件发送：简单邮件发送

        final String smtpPort = "465"; //QQ邮箱端口
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        //  根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        //session.setDebug(true);

        // 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, clientemail,num);

        // 也可以保持到本地查看
        // message.writeTo(file_out_put_stream);

        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        //  使用 邮箱账号 和 密码 连接邮件服务器
        //   这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
        transport.connect(myEmailAccount, myEmailPassword);

        //  发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        //  关闭连接
        transport.close();
    }

    @Override
    public MimeMessage createMimeMessage(Session session, String sendMail,
                                         String clientemail, int num) throws Exception {
        //  创建邮件对象
        MimeMessage message = new MimeMessage(session);

        //  From: 发件人
        message.setFrom(new InternetAddress(sendMail, "admin", "UTF-8"));

        //  To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(RecipientType.TO, new InternetAddress(clientemail, "UTF-8"));

        //  Subject: 邮件主题
        message.setSubject("获取验证码", "UTF-8");

        message.setText("[都是梦想家]验证码" + num + "用于邮箱为" + clientemail + "的用户的注册验证，如非本人操作，请忽略本邮件。泄露有风险，防盗能力提升百倍");

        // 保存设置
        message.saveChanges();

        return message;
    }
}

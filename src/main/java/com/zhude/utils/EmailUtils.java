package com.zhude.utils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class EmailUtils {
    private static final Properties PROPERTIES = new Properties();//存储配置文件的map

    static{
        InputStream in = DbUtils.class.getResourceAsStream("/email.properties");
        try {
            PROPERTIES.load(in);//通过流，将配置文件内容加载到Properties集合
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendMail(String to, String code) {
        try {
            //创建参数配置，用于连接邮件服务器的参数配置
            Properties properties = new Properties();
            properties.setProperty("mail.transport.protocol", PROPERTIES.getProperty("mail.transport.protocol"));
            properties.setProperty("mail.smtp.host", PROPERTIES.getProperty("mail.smtp.host"));
            properties.setProperty("mail.smtp.auth", PROPERTIES.getProperty("mail.smtp.auth"));

            //根据配置创建会话对象，用于和邮件服务器交互
            Session session = Session.getInstance(properties);
            session.setDebug(false);

            //创建一封邮件
            //1.创建一封邮件
            MimeMessage mimeMessage = new MimeMessage(session);
            //2.From发件人
            mimeMessage.setFrom(new InternetAddress(PROPERTIES.getProperty("from"), PROPERTIES.getProperty("from"), "UTF-8"));
            //to 收件人 （可以增加多个收件人，抄送，密送）
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{new InternetAddress(to, to, "UTF-8")});
            //subject 邮件主题
            mimeMessage.setSubject("注册账号验证码");

            String content = "亲爱的:"+to+"，您好\n" +
                    "感谢您注册BMS账户，这是您的验证码，请在5分钟内激活您的账户，否则验证码会失效\n" +
                    "验证码："+code;
            //Content:邮件正文（可以使用HTML标签
            mimeMessage.setContent(content, "text/html;charset=UTF-8");

            //6.设置发送时间
            mimeMessage.setSentDate(new Date());
            //7.保存设置
            mimeMessage.saveChanges();


            //根据session获取邮件传输对象
            Transport transport = session.getTransport();

            //使用邮箱的账号和密码连接邮件服务器，这里认证的邮箱必须与message中的发件人邮箱保持一致，否则报错
            transport.connect(String.valueOf(PROPERTIES.get("from")), PROPERTIES.getProperty("password"));

            //发送邮件，发送到所有的收件地址，mimeMessage.getAllRecipients()获取到的是在创建邮件对象时添加的所有收件人邮箱
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

            //关闭连接
            transport.close();
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

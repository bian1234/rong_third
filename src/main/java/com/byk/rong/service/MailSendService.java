package com.byk.rong.service;

import com.byk.rong.entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @Author: ykbian
 * @Date: 2018/10/12 11:49
 * @Todo:  发送邮件服务
 */

@Service
public class MailSendService {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JavaMailSender javaMailSender;


    public   Boolean sendEmail(Email mail)  {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mail.getSender());
            helper.setTo(mail.getReceiver());
            helper.setSubject(mail.getSubject());
            // 暂时还没用到邮件服务的使用场景，因此先不做邮件内容的格式化
//            String sb = MailService.toHtml(mail);
//            helper.setText(sb, true);
            helper.setText(mail.getMailbody(), true);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            logger.info("收件地址错误，邮件发送失败");
            e.printStackTrace();
            return false;
        }
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/12 13:15
     *@Description: 邮件内容格式化
     *@param:
    */
//    public static  String toHtml(Mail mail){
//        String suject = mail.getSubject();
//        String name = mail.getMailBody();
//        String msg = mail.getUserId();
//        String from = mail.getSendTime();
//        String phone = mail.getPhonenumber();
//        StringBuffer sb = new StringBuffer();
//        sb.append("<h3 style='color: #d9001f'>您有新的留言：</h3>");
//        sb.append("<p><strong>访客：</strong>"+name+"</p>");
//        sb.append("<p><strong>留言主题：</strong>"+suject+"</p>");
//        sb.append("<p><strong>他的电话：</strong>"+phone+"</p>");
//        sb.append("<p><strong>他的邮箱：</strong>"+from+"</p>");
//        sb.append("<p><strong>正文：</strong>"+msg+"</p>");
//        return sb.toString();
//    }


}

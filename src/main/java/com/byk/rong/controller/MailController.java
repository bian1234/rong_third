package com.byk.rong.controller;

import com.byk.rong.config.Constant;
import com.byk.rong.entity.Email;
import com.byk.rong.service.MailDataService;
import com.byk.rong.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/10/12 13:22
 * @Todo:  发送邮件
 */
@Controller
@RequestMapping("/mail")
public class MailController extends BaseController{

    @Value("${spring.mail.username}")
    private  String send;
    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private MailDataService mailDataService;
    /**
     *@Author:      ykbian
     *@date_time:   2018/10/12 13:27
     *@Description: 发送邮件=====以下这段代码可能会出现bug,假设邮件发送成功但是数据存储失败，前端返回失败信息；
     *
     *@param:
    */
    @ResponseBody
    @PostMapping("send")
    public Map send(Email mail){
        // 默认邮件发送成功
        boolean b = true;
        mail.setSender(send);
        // 先发送邮件 根据不同结果存入数据库
        if (mailSendService.sendEmail(mail)){
            mail.setIssuccess(Constant.SEND_EMAIL_SUCCESS);
        }else {
            mail.setIssuccess(Constant.SEND_EMAIL_FAILED);
            b = false;
        }
        mail.setSendtime(new Date());
        if (mailDataService.insertSelective(mail) <1 ){
            b = false;
        }
        if (b){
            return insertSuccseeResponse();
        }else {
            return insertFailedResponse();
        }
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/10/12 15:51
     *@Description:  删除邮件记录===假删除
     *@param:
    */
    @ResponseBody
    @PostMapping("delete")
    public Map delete(String id){
        if(mailDataService.deleteById(id) > 0){
            return deleteSuccessResponse();
        }
        return deleteFailedResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/10/12 16:07
     *@Description:  根据id查询邮件信息
     *@param:
    */
    @ResponseBody
    @GetMapping("selectById")
    public Map selectById(String id){
        Email email = mailDataService.selectById(id);
        if( email == null){
            return queryEmptyResponse();
        }
        return querySuccessResponse(email);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/12 16:53
     *@Description: 查询邮件列表
     *@param:
    */
    @ResponseBody
    @GetMapping("list")
    public Map list(@RequestParam Map<String, Object> map){
        List<Email> emailS = mailDataService.list(map);
        if(emailS == null){
            return queryEmptyResponse();
        }
        return querySuccessResponse(emailS);
    }
}

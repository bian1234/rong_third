package com.byk.rong.controller;

import com.byk.rong.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/10/15 9:51
 * @Todo:  短信接口
 */
@Controller
@RequestMapping("/sms")
public class SMSController extends BaseController{

    @Autowired
    SMSService smsService;

    /**
     *@Author:      ykbian
     *@date_time:   2018/10/15 9:56
     *@Description: 发送短信验证码
     *@param:
    */
    @PostMapping("/sendValidaCode")
    @ResponseBody
    public Map sendValidaCode(String phone,String code,String time){
        if (smsService.sendSMS(phone, code, time)){
            return querySuccessResponse(null);
        }
        return emptyParamResponse();
    }
}

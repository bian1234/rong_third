package com.byk.rong.service;



import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

/**
 * @Author: ykbian
 * @Date: 2018/10/15 9:07
 * @Todo:  短信服务
 */

@Service
public class SMSService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *   以下四个属性分别是短信应用的SDK AppID、 AppKey、模板id和短信签名
     */

    @Value("${com.byk.SMS.appid}")
    private int appid;
    @Value("${com.byk.SMS.appkey}")
    private String appkey;
    @Value("${com.byk.SMS.templateId}")
    private int   templateId;
    @Value("${com.byk.SMS.smsSign}")
    private String smsSign;




    public boolean sendSMS(String phoneNumber,String code,String validTime) {
        try {
            String[] params = {code,validTime};//参数，验证码为123456，100秒内填写
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            logger.info(result.toString());
            return true;
        } catch (HTTPException e) {
            // HTTP响应码错误
            logger.info("HTTP响应码错误");
            e.printStackTrace();
            return false;
        }catch (IOException e) {
            logger.info("网络IO错误");
            // 网络IO错误
            e.printStackTrace();
            return false;
        }catch (Exception e) {
            logger.info("其他错误");
            // 网络IO错误
            e.printStackTrace();
            return false;
        }
    }

}

package com.byk.rong.service;

import com.byk.rong.entity.Email;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/10/12 13:55
 * @Todo:  邮件服务（数据库增删改查）
 */

public interface MailDataService {

    int insertSelective(Email e);

    int updateSelective(Email e);

    Email selectById(String id);

    List<Email> selectByParams(Email e);

    int deleteById(String id);

    /**
     * 带分页查询菜单列表
     */
    List<Email> list(Map<String, Object> map);

    int count(Map<String, Object> map);

}
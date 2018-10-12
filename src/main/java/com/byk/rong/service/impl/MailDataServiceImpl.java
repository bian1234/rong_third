package com.byk.rong.service.impl;

import com.byk.rong.entity.Email;
import com.byk.rong.mapper.read.EmailReadMapper;
import com.byk.rong.mapper.write.EmailWriteMapper;
import com.byk.rong.service.MailDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/10/12 13:57
 * @Todo:
 */
@Service
public class MailDataServiceImpl  implements MailDataService {

    @Autowired
    private EmailReadMapper emailReadMapper;
    @Autowired
    private EmailWriteMapper emailWriteMapper;


    @Override
    public int insertSelective(Email e) {
        return emailWriteMapper.insertSelective(e);
    }

    @Override
    public int updateSelective(Email e) {
        return 0;
    }

    @Override
    public Email selectById(String id) {
        return emailReadMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Email> selectByParams(Email e) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return emailWriteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Email> list(Map<String, Object> params) {
        return emailReadMapper.list(params);
    }

    @Override
    public int count(Map<String, Object> map) {
        return emailReadMapper.count(map);
    }
}

package com.byk.rong.mapper.read;

import com.byk.rong.entity.Email;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmailReadMapper {

    Email selectByPrimaryKey(String id);

    List<Email> list(Map<String, Object> map);
    int count(Map<String, Object> map);
}
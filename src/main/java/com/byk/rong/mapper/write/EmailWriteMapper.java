package com.byk.rong.mapper.write;

import com.byk.rong.entity.Email;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Email record);

    int insertSelective(Email record);

    Email selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Email record);

    int updateByPrimaryKey(Email record);
}
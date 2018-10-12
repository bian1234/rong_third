package com.byk.rong.entity;

/**
 * @Author: ykbian
 * @Date: 2018/10/12 17:11
 * @Todo:   所有实体类的基类
 */

public class BaseEntity {

    int offset;
    int limit;

    public int getOffset() {

        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

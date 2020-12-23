package com.chens.coblog.domain.enums;

/**
 * 链接类型枚举类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
public enum LinkTypeEnum {
    /**
     * 友情链接
     */
    FRIEND_LINK(1,"友情链接"),
    /**
     * 个人链接
     */
    PERSONAL_LINK(2,"个人链接");


    private int value;
    private String desc;

    LinkTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}

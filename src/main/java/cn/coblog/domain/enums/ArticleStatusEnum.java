package cn.coblog.domain.enums;

/**
 * 文章状态枚举类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
*/

public enum ArticleStatusEnum{

    /**
     * 已发布
     */
    PUBLISHED(1, "已发布"),

    /**
     * 草稿
     */
    DRAFT(0, "草稿"),

    /**
     * 回收站
     */
    RECYCLE(2, "回收站");

    private int value;
    private String desc;

    ArticleStatusEnum(Integer value, String desc) {
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

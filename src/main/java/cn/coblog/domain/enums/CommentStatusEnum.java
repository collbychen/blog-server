package cn.coblog.domain.enums;

/**
 * 评论状态枚举类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
public enum CommentStatusEnum {


    /**
     * 待审核
     */
    CHECKING(0, "待审核"),

    /**
     * 已发布
     */
    PUBLISHED(1, "已发布"),

    /**
     * 已删除
     */
    RECYCLE(2, "已删除");

    private int value;
    private String desc;

    CommentStatusEnum(Integer value, String desc) {
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

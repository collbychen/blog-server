package cn.coblog.domain.enums;


/**
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
public enum CommentTargetTypeEnum {

    /**
     * 文章
     */
    ARTICLE(1, "文章"),

    /**
     * 评论
     */
    COMMENT(2, "评论");


    private int value;
    private String desc;

    CommentTargetTypeEnum(Integer value, String desc) {
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

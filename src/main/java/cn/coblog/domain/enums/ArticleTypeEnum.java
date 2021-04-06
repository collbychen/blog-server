package cn.coblog.domain.enums;

/**
 * 文章类型枚举类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
public enum ArticleTypeEnum {

    /**
     * 普通文章
     */
    ORDINARY(0, "普通文章"),

    /**
     * 自定义
     */
    CUSTOM(1, "自定义");

    private Integer value;
    private String desc;

    ArticleTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}

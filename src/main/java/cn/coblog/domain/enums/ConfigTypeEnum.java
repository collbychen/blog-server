package cn.coblog.domain.enums;

/**
 * 配置类型枚举类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
public enum ConfigTypeEnum {
    /**
     * 全局变量
     */
    GLOBAL_OPTION(1,"全局变量"),

    /**
     * 系统配置
     */
    SYSTEM_CONFIG(2,"系统配置");

    private int value;
    private String desc;

    ConfigTypeEnum(Integer value, String desc) {
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

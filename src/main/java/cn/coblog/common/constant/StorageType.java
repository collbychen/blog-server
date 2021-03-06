package cn.coblog.common.constant;

/**
 * 数据存储类型
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
public enum StorageType {

    /**
     * 七牛云
     */
    QINIU(1),
    /**
     * 阿里云
     */
    ALIYUN(2),
    /**
     * 腾讯云
     */
    QCLOUD(3),
    /**
     * 本地
     */
    LOCAL(4);

    private int value;

    StorageType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

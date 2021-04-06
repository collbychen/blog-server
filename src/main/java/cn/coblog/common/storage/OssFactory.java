package cn.coblog.common.storage;


import cn.coblog.common.constant.ConfigConst;
import cn.coblog.common.constant.StorageType;
import cn.coblog.common.exception.BlogException;
import cn.coblog.common.utils.SpringUtils;
import cn.coblog.service.ConfigService;
import org.apache.commons.lang3.StringUtils;

/**
 * 文件上传Factory
 */
public final class OssFactory {

    private static ConfigService configService;

    static {
       configService = SpringUtils.getBean(ConfigService.class);
    }

    public static BaseStorage build(){
        //获取存储配置信息
        StorageConfig config = configService.getConfigObject(ConfigConst.FILE_STORAGE, StorageConfig.class);
        if(config.getType() == StorageType.QINIU.getValue()){
            return new QiniuCloudStorage(config);
        }else if(config.getType() == StorageType.LOCAL.getValue()){
            if(StringUtils.isBlank(config.getLocalDirectory())){
                //未指定存储目录，默认存储于项目静态文件夹内
                String localDirectory=System.getProperty("user.dir")+"/src/main/resources/static";
                config.setLocalDirectory(localDirectory);
                config.setLocalDomain(configService.getConfigByName(ConfigConst.DOMAIN));
            }
            return new LocalStorage(config);
        }
        throw new BlogException("未配置存储类型");
    }
}

package com.chens.coblog.common.storage;


import com.chens.coblog.common.constant.ConfigConst;
import com.chens.coblog.common.constant.StorageType;
import com.chens.coblog.common.exception.BlogException;
import com.chens.coblog.common.utils.SpringUtils;
import com.chens.coblog.service.ConfigService;
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

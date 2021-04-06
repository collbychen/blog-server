package cn.coblog.common.storage;

import cn.coblog.common.constant.CodeEnum;
import cn.coblog.common.exception.BlogException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云存储
 * @author chens
 */
public class QiniuCloudStorage extends BaseStorage {
    private UploadManager uploadManager;
    private String token;

    public QiniuCloudStorage(StorageConfig config){
        super(config);
        //初始化
        init();
    }

    private void init(){
        uploadManager = new UploadManager(new Configuration(Region.huadong()));
        Auth auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        token = auth.uploadToken(config.getQiniuBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString() + res.getInfo());
            }
        } catch (Exception e) {
            throw new BlogException(CodeEnum.UPLOAD_ERROR.code(),"上传文件失败，请核对七牛配置信息", e);
        }
        String domain=config.getQiniuDomain();
        if(!domain.endsWith("/")){domain+="/";}
        return domain + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new BlogException(CodeEnum.UPLOAD_ERROR.code(),"上传文件失败", e);
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        // 改变一下路径
        // return upload(data, getPath(config.getQiniuPrefix(), suffix));
        return upload(data, config.getQiniuPrefix() + suffix);
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQiniuPrefix(), suffix));
    }
}

package cn.coblog.api.imp;


import cn.coblog.api.admin.AppsApi;
import cn.coblog.api.front.AppApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.common.constant.CodeEnum;
import cn.coblog.common.constant.Const;
import cn.coblog.common.exception.BlogException;
import cn.coblog.common.storage.OssFactory;
import cn.coblog.common.utils.HttpRequestUtils;
import cn.coblog.config.GithubConfig;
import cn.coblog.domain.Article;
import cn.coblog.service.ConfigService;
import cn.coblog.service.LogService;
import com.alibaba.fastjson.JSONObject;
import net.coobird.thumbnailator.Thumbnails;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 前台应用
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RestController
public class AppImp implements AppApi, AppsApi {

    @Autowired
    private ConfigService configService;
    @Autowired
    private LogService logService;
    @Autowired
    public RestHighLevelClient client;

    @Override
    public BaseResponse global() {
        return BaseResponse.success("查询成功", configService.getAllGlobal());
    }

    @Override
    public BaseResponse sendCode() {
        return BaseResponse.success("获取成功",GithubConfig.CODE_URL);
    }

    @Override
    public BaseResponse getInfo(String token) {
        try {
            String userInfo = GithubConfig.getUerInfoUrl(token);
            //封装数据
            JSONObject obj= JSONObject.parseObject(userInfo);
            Map<String,Object> info = new HashMap<>(3);
            info.put("name", obj.get("name")+"");
            info.put("id", obj.get("id")+"");
            info.put("avatar", obj.get("avatar_url")+"");
            return BaseResponse.success("获取成功", info);
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponse.success("没有信息");
        }
    }

    @Override
    public String callBack(HttpServletRequest request) {
        String code = request.getParameter("code");
        //申请令牌
        String result = HttpRequestUtils.sendGet( GithubConfig.getTokenUrl(code) );
        //从result中截取令牌
        String accessToken = HttpRequestUtils.getMap(result).get("access_token");
        return "<script>" +
                "window.opener.postMessage('"+ accessToken +"', '*' );" +
                "window.close();" +
                "</script>";
    }

    @Override
    public BaseResponse search(String wd, Integer page) {
        if ( wd.equals("") || page <= 0 ){ return BaseResponse.success("参数不正确"); }
        //走ES
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.from((page-1)*Const.PAGE_SIZE);
//        searchSourceBuilder.size(page*Const.PAGE_SIZE);
//        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(wd, FieldName.CONTENT, FieldName.TITLE, FieldName.DESCRIPTION));
//        List<Article> list = ElasticSearchUtil.search(client, Const.BLOG, searchSourceBuilder , Article.class);
        List<Article> list = null;
        return BaseResponse.success(list);
    }

    @Override
    public BaseResponse logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return BaseResponse.success(CodeEnum.NOT_LOGIN);
    }

    @Override
    public BaseResponse uploadImage(MultipartFile image) {
        String type = "image";
        String contentType = image.getContentType();
        if (contentType != null){
            if (!contentType.contains(type)) {
                throw new BlogException(CodeEnum.VALIDATION_ERROR.code(), "文件格式错误");
            }
        }
        //上传文件
        // String suffix = Objects.requireNonNull(image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf("."));
        String suffix = Objects.requireNonNull(image.getOriginalFilename());
        String url;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //判断图片是否较大，超过阈值进行压缩
            if (image.getSize() > Const.COMPRESSION_SIZE) {
                //经测试，png格式图片无法正确压缩，若格式为png，则转换成jpg
                final boolean suffixIsPng = ".png".equals(suffix);
                if (suffixIsPng) {
                    Thumbnails.of(image.getInputStream()).scale(1f).outputQuality(0.5f).outputFormat("jpg").toOutputStream(bytes);;
                    suffix = ".jpg";
                }

                Thumbnails.of(image.getInputStream()).scale(1f).outputQuality(0.5f).toOutputStream(bytes);
                url = OssFactory.build().uploadSuffix(bytes.toByteArray(), suffix);
            }
            else {
                url = OssFactory.build().uploadSuffix(image.getBytes(), suffix);
            }
            return BaseResponse.success("图片上传成功", url);
        } catch (IOException e) {
            logService.error("图片上传失败", e.getMessage());
            return BaseResponse.fail("图片上传失败");
        }
    }

}

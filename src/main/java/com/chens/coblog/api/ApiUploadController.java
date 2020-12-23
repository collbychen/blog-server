package com.chens.coblog.api;


import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.common.constant.CodeEnum;
import com.chens.coblog.common.constant.Const;
import com.chens.coblog.common.exception.BlogException;
import com.chens.coblog.common.storage.OssFactory;
import com.chens.coblog.service.LogService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * 文件上传
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RestController
@RequestMapping("api")
public class ApiUploadController {

    @Autowired
    private LogService logService;

    /**
     * 退出登录
     */
    @GetMapping("logout")
    public BaseResponse logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return BaseResponse.success(CodeEnum.NOT_LOGIN);
    }

    /**
     * 上传图片
     */
    @PostMapping("uploadImage")
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

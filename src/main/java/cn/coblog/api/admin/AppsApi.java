package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * admin应用api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RequestMapping("api")
public interface AppsApi {

    /**
     * 退出登录
     */
    @GetMapping("logout")
    BaseResponse logout();

    /**
     * 上传图片
     */
    @PostMapping("uploadImage")
    BaseResponse uploadImage(MultipartFile image);

}

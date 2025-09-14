package top.zds.controller.Hutool;

import cn.hutool.crypto.SecureUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zds.common.ApiResponse;

import java.util.Map;

@RestController
@RequestMapping("/api/hutool/secure")
public class HutoolSecureController {

    @PostMapping("/md5")
    public ApiResponse<String> md5Encrypt(@RequestBody Map<String, String> param) {
        String content = param.get("content");
        if (content == null) {
            return ApiResponse.error("参数content不能为空");
        }
        String md5 = SecureUtil.md5(content);
        return ApiResponse.success("MD5加密成功", md5);
    }
}
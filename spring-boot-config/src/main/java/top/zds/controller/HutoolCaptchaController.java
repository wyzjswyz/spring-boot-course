package top.zds.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zds.common.ApiResponse;

@RestController
@RequestMapping("/api/hutool/captcha")
public class HutoolCaptchaController {

    @GetMapping("/generate")
    public ApiResponse<String> generateCaptcha() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(120, 40, 4, 10);
        String captchaBase64 = captcha.getImageBase64();
        return ApiResponse.success("验证码生成成功", captchaBase64);
    }
}
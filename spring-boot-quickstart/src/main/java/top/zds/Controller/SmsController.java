package top.zds.Controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zds.service.SmsService;

/**
 * @Author: zds
 * @Date: 2025/9/5
 */
@RestController
public class SmsController {

    @Resource
    private SmsService smsService;

    @GetMapping("/sms")
    public void sendSms(String phone) {
        smsService.sendSms(phone);
    }
}

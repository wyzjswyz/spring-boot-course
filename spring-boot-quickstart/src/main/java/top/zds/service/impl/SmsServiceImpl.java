package top.zds.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.zds.config.CloopenConfig;
import top.zds.service.SmsService;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: zds
 * @Date: 2025/9/6
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    @Resource
    private CloopenConfig cloopenConfig;
    @Override
    public void sendSms(String phone) {
        // 1.生成验证码
        int code = ThreadLocalRandom.current().nextInt(1000, 9999);
        log.info("发送短信验证码: {}", code);

        // 2. 获取到配置信息
        String serverIp = cloopenConfig.getServerIp();
        String serverPort = cloopenConfig.getPort();
        String accountSid = cloopenConfig.getAccountSid();
        String accountToken = cloopenConfig.getAccountToken();
        String appId = cloopenConfig.getAppId();
        String templateId = cloopenConfig.getTemplateId();

        // 3. 创建sdk对象
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSid, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        // 发送短信
        String[] datas = {String.valueOf(code), "1"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, datas, "1234", UUID.randomUUID().toString());
        if ("000000".equals(result.get("statusCode"))) {
            // 正常返回输出data包体信息(map)
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                log.info("{} = {}", key, object);
            }
        } else {
            // 异常返回输出错误码和错误信息
            log.error("错误码={} 错误信息= {}", result.get("statusCode"), result.get("statusMsg"));
        }
    }
}

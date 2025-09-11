package top.zds.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zds
 * @Date: 2025/9/5
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "zds.sms.ccp")

public class CloopenConfig {
    private String serverIp;
    private String port;
    private String accountSid;
    private String accountToken;
    private String appId;
    private String templateId;
}

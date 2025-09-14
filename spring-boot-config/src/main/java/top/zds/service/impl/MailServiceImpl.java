package top.zds.service.impl;

import enums.ResultStatus;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zds.Mail;
import top.zds.service.MailService;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {
    //读取配置的发件⼈
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender javaMailSender;

    /**
     *
     * @param mail
     *简单邮件信息
     * @return
     */
    @Override
    public ResultStatus sendSimpleMail(Mail mail) {
        //发送简单邮件，根据发送情况返回不同信息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        try {
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        }
        catch (Exception e) {
            return ResultStatus.FAIL;
        }
    }

    /**
     *
     * @param mail
     *网页邮件信息
     * @return
     */
    @Override
    public ResultStatus sendHTMLMail(Mail mail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, StandardCharsets.UTF_8.name());
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            //第⼆个参数=true表示HTML
            helper.setText(mail.getContent(), true);
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (Exception e) {
            return ResultStatus.FAIL;
        }
    }

    /**
     *
     * @param mail
     *带附件邮件信息
     * @param files
     * @return
     */
    @Override
    public ResultStatus sendAttachmentsMail(Mail mail, MultipartFile[] files) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            // 支持富文本正文
            helper.setText(mail.getContent(), true);
            if (files != null) {
                for (MultipartFile f : files) {
                    if (f != null && !f.isEmpty()) {
                        helper.addAttachment(Objects.requireNonNull(f.getOriginalFilename()), new ByteArrayResource(f.getBytes()));
                    }
                }
            }
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (Exception e) {
            return ResultStatus.FAIL;
        }
    }
}


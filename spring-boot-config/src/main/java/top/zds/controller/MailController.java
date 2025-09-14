package top.zds.controller;

import enums.ResultStatus;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zds.Mail;
import top.zds.common.ApiResponse;
import top.zds.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;
    /**
     **发送简单邮件
     * @param mail
     *邮件信息
     * @return 发送结果
     */
    @PostMapping("/simple")
    public ResponseEntity<ApiResponse<ResultStatus>> sendSimpleMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs = mailService.sendSimpleMail(mail);
        if (rs == ResultStatus.SUCCESS) {
            return ResponseEntity.ok(ApiResponse.success("发送成功", rs));
        }
        //业务失败建议返回400（参数/业务错误），⽽不是200
        return ResponseEntity.badRequest().body(ApiResponse.error(" 发送失败 "));
    }
    /**
     **发送HTML邮件
     * @param mail
     *邮件信息
     * @return 邮件发送结果
     */
    @PostMapping("/html")
    public ResponseEntity<ApiResponse<ResultStatus>> sendHtmlMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs = mailService.sendHTMLMail(mail);
        return rs == ResultStatus.SUCCESS ? ResponseEntity.ok(ApiResponse.success(" 发送成功 ", rs)) : ResponseEntity.badRequest().body(ApiResponse.error(" 发送失败 "));
    }

    /**
     **发送带附件的邮件
     * @param mail 邮件信息
     * @param files 邮件附件
     * @return 邮件发送结果
     */
//    标注@Valid:若Mail类字段带有如@NotBlank、@Email 等校验，
//    校验不通过会抛出MethodArgumentNotValidException ，返回 400（需配置全局异常处理更友好）
    @PostMapping(value = "/attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ResultStatus>>
    sendAttachmentsMail(@Valid @RequestPart("mail") Mail mail,
                        @RequestPart(value = "files",required = false) MultipartFile[] files){
        try {
            ResultStatus rs = mailService.sendAttachmentsMail(mail, files);
            return rs == ResultStatus.SUCCESS ?
                    ResponseEntity.ok(ApiResponse.success("发送成功", rs)) :
                    ResponseEntity.badRequest().body(ApiResponse.error(" 发送失败 "));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ApiResponse.error(" 邮件发送异常 "));
        }
    }
}

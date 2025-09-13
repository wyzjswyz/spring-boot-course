package top.zds.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zds.common.ApiResponse;

import java.util.Date;

@RestController
@RequestMapping("/api/hutool/date")
public class HutoolDateController {

    @GetMapping("/format")
    public ApiResponse<String> formatDate(@RequestParam String pattern) {
        String formattedDate = DateUtil.format(new Date(), pattern);
        return ApiResponse.success("日期格式化成功", formattedDate);
    }
}
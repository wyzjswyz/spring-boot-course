package top.zds.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zds.common.ApiResponse;

@RestController
@RequestMapping("/api/hutool/http")
public class HutoolHttpController {

    @GetMapping("/get")
    public ApiResponse<String> httpGet(@RequestParam String url) {
        if (url == null) {
            return ApiResponse.error("参数url不能为空");
        }
        String response = HttpUtil.get(url);
        return ApiResponse.success("HTTP GET请求成功", response);
    }
}
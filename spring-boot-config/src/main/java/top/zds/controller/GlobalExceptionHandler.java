package top.zds.controller;

import enums.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiResponse<String>> handleMultipartException(MultipartException e) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("文件上传失败: " + e.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("请求缺少必要部分: " + e.getMessage()));
    }
}


package top.zds.common;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ApiResponse<T> success(String msg, T data) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.code = 200;
        resp.msg = msg;
        resp.data = data;
        return resp;
    }

    public static <T> ApiResponse<T> error(String msg) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.code = 400;
        resp.msg = msg;
        resp.data = null;
        return resp;
    }
}
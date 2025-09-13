package top.zds.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}

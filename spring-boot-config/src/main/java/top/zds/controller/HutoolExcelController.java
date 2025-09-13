package top.zds.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zds.common.ApiResponse;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hutool/excel")
public class HutoolExcelController {

    @PostMapping("/create")
    public ApiResponse<String> createExcel(@RequestBody List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) {
            return ApiResponse.error("Excel数据不能为空");
        }
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.write(data, true); // true表示包含表头
        String filePath = "temp/excel_" + System.currentTimeMillis() + ".xlsx";
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        writer.flush(file);
        writer.close();
        return ApiResponse.success("Excel生成成功", filePath);
    }
}
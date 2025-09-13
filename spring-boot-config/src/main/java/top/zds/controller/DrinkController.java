package top.zds.controller;

import enums.ApiResponse;
import enums.DrinkType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/drink")
public class DrinkController {
    /**
     * 选择饮料
     *
     * @param type 饮料类型
     * @return ApiResponse
     */
    @GetMapping("/{type}")
    public ApiResponse<Map<String, Object>> chooseDrink(@PathVariable String type) {
        try {
            //将传⼊的type统⼀转为⼤写字符
            DrinkType drink = enums.DrinkType.valueOf(type.toUpperCase());
            //构造返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("name", drink.getLabel());
            result.put("price", drink.getPrice());
            //返回结果
            return ApiResponse.success("选择成功", result);
        } catch (IllegalArgumentException e) {
            //输⼊的参数⽆效
            return ApiResponse.error("饮料类型⽆效");
        }
    }
    /**
     * 获取饮料菜单
     * @return ApiResponse
     */
    @GetMapping("/menu")
    public ApiResponse<List<Map<String, Object>>> getMenu() {
        List<Map<String, Object>> menu = new ArrayList<>();
        for (DrinkType drink : DrinkType.values()) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", drink.getLabel());
            item.put("price", drink.getPrice());
            menu.add(item);
        }
        return ApiResponse.success(" 菜单获取成功 ", menu);
    }

    /**
     * 下单
     * *@param type 饮料类型
     * *@param num数量
     * * @return ApiResponse
     */
    @GetMapping("/order")
    public ApiResponse<Map<String, Object>> orderDrink(@RequestParam String type, @RequestParam int num) {
        try {
            DrinkType drink = DrinkType.valueOf(type.toUpperCase());
            double total = drink.getPrice() * num;
            Map<String, Object> result = new HashMap<>();
            result.put("name", drink.getLabel());
            result.put("num", num);
            result.put("unitPrice", drink.getPrice());
            result.put("totalPrice", total);
            return ApiResponse.success(" 下单成功 ", result);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(" 饮料类型⽆效 ");
        }
    }
}

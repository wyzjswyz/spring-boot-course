package top.zds.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String getHello(){
        return "Hello Word!";
    }

    @GetMapping("/list")
    public List<String> getList(){
        return List.of("aaa","bbb","ccc");
    }

    // 生成一个字符串
    @GetMapping("/string")
    public List<String> Strings(){
        return List.of("hello","word");
    }

}
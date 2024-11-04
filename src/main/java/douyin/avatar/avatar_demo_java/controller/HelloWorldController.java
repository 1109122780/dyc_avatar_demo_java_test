package douyin.avatar.avatar_demo_java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // 定义一个处理 HTTP GET 请求的方法
    @GetMapping("/ping")
    public String helloWorld() {
        return "Hello, Spring Boot!";
    }
}

package com.jingwei.dispatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("/hello-world")
    @ResponseBody
    public String sayHelloWorld() {
        return "Hello Java";
    }

}
package com.jingwei.dispatch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/version")
    @ResponseBody
    public String version() {
        return "0.0.1";
    }

}
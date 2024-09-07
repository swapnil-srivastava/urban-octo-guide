package com.greenplate.greenplate.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @Value("${app.version}")
    private String appVersion; 

    @GetMapping("/hello-spring")
    public String HelloWorldSprings() {
        return "Hello World Spring";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/version")
    public Map getAppVersion() {
        @SuppressWarnings("rawtypes")
        Map map = new HashMap();
        map.put("app-version", appVersion);
        return map;
    }
}

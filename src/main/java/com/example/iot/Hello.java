package com.example.iot;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/hello")
    private Map<String, Object> hello(){
        return Map.of("code", 0, "content", "hello");
    }
}

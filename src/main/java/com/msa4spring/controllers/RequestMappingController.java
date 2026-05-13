package com.msa4spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController : REST API 컨트롤러 (중개)
// @RequestMapping : 클래스 레벨의 맵핑을 이어줌 (맵핑 기능)
//                ("/") -> 그룹핑하고 싶은 맵핑 적어줌
@RestController
@RequestMapping("/api")
public class RequestMappingController {
    @GetMapping("/test")
    public String test() {
        return "GET 테스트";
    }

    @PostMapping("/test")
    public String testPost() {
        return "POST 테스트";
    }
}

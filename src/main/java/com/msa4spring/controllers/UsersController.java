package com.msa4spring.controllers;

import com.msa4spring.requests.PostsFilterRequest;
import com.msa4spring.requests.UsersPaginationRequest;
import org.springframework.web.bind.annotation.*;
// import tools.jackson.databind.node.StringNode;

@RestController
@RequestMapping("/api")
public class UsersController {
    @GetMapping("/users")
    public String index(
            // (value = "pa") : 파라미터 이름 바꿔주고 싶을때
            // (required = false) : 유저에게 값을 옵션으로 받을 때 (기본값: null)
            // (required = false, defaultValue = "1") 기본값 "1"로 셋팅
            @RequestParam(required = false, defaultValue = "1") String page
            , @RequestParam String limit
    ) {
        // 쿼리 파라미터 획득 방법: @RequestParam 어노테이션을 통해 획득
        return "users GET: " + page + ", " + limit;
    }

    // 세그먼트 파라미터 -> {} 안에 작성 -> "/users/{pk 컬럼명}"
    @GetMapping("/users/{id}")
    public String show(
            // required속성이 있지만 필수로 있어야 하는 값이므로 사용하지 않음.
            @PathVariable String id
    ) {
        return "GET users show: " + id;
    }

    @PostMapping("/users")
    public String store() {
        return "users POST";
    }

    // ------------------------
    // DTO를 활용하여 파라미터 획득
    // ------------------------
    @GetMapping("/users/dto-param")
    public String dtoParam(
            UsersPaginationRequest usersPaginationRequest
    ) {
        return String.format(
                "GET dtoParam: %d, %d"
                , usersPaginationRequest.page()
                , usersPaginationRequest.limit()
        );
    }

    // -------------------------
    // 세그먼트 파라미터 || Form Data를 DTO로 획득
    // (form형식 or 세그먼트 파라미터 형식) DTO로 받아오려면
    // @ModelAttribute 사용
    // -------------------------
    @GetMapping("/posts/{id}/filter/{categoryId}")
    public String postFilter(
            @ModelAttribute PostsFilterRequest postsFilterRequest
    ) {
        return String.format(
                "postFilter: %d, %d"
                , postsFilterRequest.id()
                , postsFilterRequest.categoryId()
        );
    }

    // ----------------------
    // JSON 데이터를 DTO로 획득
    // @RequestBody 사용
    // ----------------------
    @GetMapping("/posts/json")
    public String postsJson(
            @RequestBody PostsFilterRequest postsFilterRequest
    ) {
        return String.format(
                "postJson: %d, %d"
                , postsFilterRequest.id()
                , postsFilterRequest.categoryId()
        );
    }
}

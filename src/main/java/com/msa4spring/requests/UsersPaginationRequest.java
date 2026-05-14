package com.msa4spring.requests;

public record UsersPaginationRequest(
        Integer page
        , Integer limit
) {
//    public UsersPaginationRequest(String page, String limit) {
//        this.page = (page == null || page.isBlank()) ? "1" : page; // 조건()단위로 묶어준 것. 안 써도 작동은 함.
//        this.limit = (limit == null || limit.isBlank()) ? "1" : limit;
//    }
    public UsersPaginationRequest(Integer page, Integer limit) {
        this.page = (page == null) ? 1 : page; // 조건()단위로 묶어준 것. 안 써도 작동은 함.
        this.limit = (limit == null) ? 10 : limit;
    }
}

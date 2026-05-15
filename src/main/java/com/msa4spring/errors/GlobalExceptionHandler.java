package com.msa4spring.errors;

import com.msa4spring.reponses.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.json.JsonMapper;

import java.sql.SQLException;
import java.util.List;

// ExceptionHandler 클래스 생성(클래스명은 아무거나 상관없다.)
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final JsonMapper.Builder builder;

    public GlobalExceptionHandler(JsonMapper.Builder builder) {
        this.builder = builder;
    }
    // 예외 처리를 실행할 메소드 정의(메소드명 자유)

    // 유효성 검사 실패 예외 처리 (@Valid, @Validated)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<List<String>>> validationHandle(MethodArgumentNotValidException e) {
        List<String> errorMsgList = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
               // .collect(Collectors.joining(", "));
                .toList();

        ResponseDTO<List<String>> responseDTO = ResponseDTO.<List<String>>builder()
                .code("E01")
                // .msg("유효성 검사 실패: " + errorMsg)
                .msg("유효성 검사 실패")
                .data(errorMsgList)
                .build();

        return ResponseEntity.status(400).body(responseDTO);
    }

    //DB 관련 예외 핸들러
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseDTO<String>> sqlExceptionHandle(SQLException e) {
        log.error(e.getMessage());

        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
                .code("E80")
                .msg("DB 에러 발생")
                .data("현재 서비스 이용 불가합니다.\n잠시 후 다시 시도해 주십시오.")
                .build();

        return ResponseEntity.status(500).body(responseDTO);
    }

    // Exception.class : 모든 예외를 다 받음
    // Exception: 에러의 최상위 객체
    // 객체명.class -> 객체의 인스턴스 자체를 가져옴
    // 나머지 예외들 처리
    // 위치 상관 X 관습적으로 마지막에 작성.
    @ExceptionHandler(Exception.class) // 나머지 모두. (=비유 else)
    public ResponseEntity<ResponseDTO<String>> othersHandle(Exception e) {
        log.error("예기치 못한 에러 발생", e.getMessage());

        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
                .code("E99") // 99 예기치 못한 에러, 80 DB에러, 6-70 사용자 에러
                .msg("서버 에러 발생")
                .data("현재 서비스 이용 불가합니다.\n잠시 후 다시 시도해 주십시오.")
                .build();

        return ResponseEntity.status(500).body(responseDTO);
    }
}

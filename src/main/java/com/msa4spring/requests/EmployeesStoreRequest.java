package com.msa4spring.requests;

import jakarta.validation.constraints.NotBlank;

public record EmployeesStoreRequest(
        @NotBlank(message = "이름 필수")
        String name

        ,@NotBlank(message = "이름 필수")
        String birth

        ,@NotBlank(message = "이름 필수")
        String gender
) {
}

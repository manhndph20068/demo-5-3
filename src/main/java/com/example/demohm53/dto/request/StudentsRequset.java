package com.example.demohm53.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentsRequset {

    private Integer id;

    @NotBlank(message = "FullName is required")
    private String fullName;

    @Min(value = 1, message = "age must be greater than 1")
    @NotNull(message = "age is required")
    private Integer age;

    @NotNull(message = "Gender is NotNull")
    private Integer gender;

    private Integer status;
}

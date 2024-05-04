package com.example.demohm53.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
    @NotNull(message = "FullName is NotNull")
    private String fullName;

    @Min(value = 1, message = "age must be greater than 1")
    @NotBlank(message = "age is required")
    @NotNull(message = "age is NotNull")
    private Integer age;

    @NotBlank(message = "Gender is required")
    @NotNull(message = "Gender is NotNull")
    private Integer gender;

    private Integer status;
}

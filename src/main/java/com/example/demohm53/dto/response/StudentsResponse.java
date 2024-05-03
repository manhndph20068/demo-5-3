package com.example.demohm53.dto.response;

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
public class StudentsResponse {
    private String fullName;
    private Integer age;
    private Integer gender;
    private Integer status;
}

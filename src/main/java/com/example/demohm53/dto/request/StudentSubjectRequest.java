package com.example.demohm53.dto.request;

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
public class StudentSubjectRequest {
    private Integer id;
    private Integer studentId;
    private Integer subjectId;
    private String fullNameOfStudent;
    private Integer age;
    private Integer gender;
    private String subjectName;
    private String description;
}

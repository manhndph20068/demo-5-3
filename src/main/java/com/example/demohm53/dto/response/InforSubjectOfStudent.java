package com.example.demohm53.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InforSubjectOfStudent {
    private Integer studentSubjectId;
    private Integer subjectId;
    private String subjectName;
    private String subjectDescription;
    private Integer statusSubject;
}

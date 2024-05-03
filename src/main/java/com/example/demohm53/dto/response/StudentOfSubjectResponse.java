package com.example.demohm53.dto.response;

import com.example.demohm53.entity.Students;
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
public class StudentOfSubjectResponse {
    private Integer subjectId;
    private String subjectName;
    private Integer totalStudent;
    private List<Students> studentsList;
}

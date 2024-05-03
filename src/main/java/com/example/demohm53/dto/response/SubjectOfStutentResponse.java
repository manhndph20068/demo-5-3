package com.example.demohm53.dto.response;

import com.example.demohm53.entity.Subjects;
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
public class SubjectOfStutentResponse {
    private Integer studentId;
    private List<Subjects> subjectsList;
}

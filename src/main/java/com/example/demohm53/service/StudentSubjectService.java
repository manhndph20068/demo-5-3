package com.example.demohm53.service;

import com.example.demohm53.dto.request.StudentSubjectRequest;
import com.example.demohm53.dto.response.StudentOfSubjectResponse;
import com.example.demohm53.dto.response.StudentSubjectResponse;
import com.example.demohm53.dto.response.StudentsResponse;
import com.example.demohm53.dto.response.SubjectOfStutentResponse;
import com.example.demohm53.entity.StudentSubject;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentSubjectService {
    List<StudentSubjectResponse> getListStudentSubject();

    Page<StudentSubject> getListStudentSubjectPaginate(int page, int size);

    StudentSubject addStudentToSubject(StudentSubjectRequest studentSubjectRequest) throws Exception;

    SubjectOfStutentResponse findSubjectOfStutentResponse(StudentSubjectRequest studentSubjectRequest) throws Exception;

    StudentOfSubjectResponse findStudentOfSubjectResponse(StudentSubjectRequest studentSubjectRequest) throws Exception;
}

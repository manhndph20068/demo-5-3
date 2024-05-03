package com.example.demohm53.service;

import com.example.demohm53.dto.request.StudentsRequset;
import com.example.demohm53.dto.request.SubjectRequest;
import com.example.demohm53.dto.response.StudentsResponse;
import com.example.demohm53.dto.response.SubjectResponse;
import com.example.demohm53.entity.Students;
import com.example.demohm53.entity.Subjects;

import java.util.List;

public interface SubjectsService {
    List<SubjectResponse> getListSubjects();

    Subjects addNew(SubjectRequest subjectRequest);

    Subjects updateSubject(SubjectRequest subjectRequest) throws Exception;

    Subjects deleteSubject(SubjectRequest subjectRequest) throws Exception;
}

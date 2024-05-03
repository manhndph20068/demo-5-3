package com.example.demohm53.service;

import com.example.demohm53.dto.request.StudentsRequset;
import com.example.demohm53.dto.response.StudentsResponse;
import com.example.demohm53.entity.Students;
import com.example.demohm53.exceptions.DataNotFoundException;

import java.util.List;

public interface StudentsService {
    List<StudentsResponse> getListStudents();

    Students addNew(StudentsRequset studentsRequset);

    Students updateStudent(StudentsRequset studentsRequset) throws Exception;

    Students deleteStudent(StudentsRequset studentsRequset) throws Exception;
}

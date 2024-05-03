package com.example.demohm53.service.impl;

import com.example.demohm53.dto.request.StudentsRequset;
import com.example.demohm53.dto.response.StudentsResponse;
import com.example.demohm53.entity.Students;
import com.example.demohm53.exceptions.DataNotFoundException;
import com.example.demohm53.repository.StudentsRepository;
import com.example.demohm53.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public List<StudentsResponse> getListStudents() {
        List<StudentsResponse> studentsResponseList = studentsRepository.findAll().stream()
                .map(this::convertToRes)
                .collect(Collectors.toList());
        return studentsResponseList;
    }

    @Override
    public Students addNew(StudentsRequset studentsRequset) {
        Students student = Students.builder()
                .fullName(studentsRequset.getFullName())
                .age(studentsRequset.getAge())
                .gender(studentsRequset.getGender())
                .status(0)
                .build();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Students updateStudent(StudentsRequset studentsRequset) throws Exception {
        Students students = studentsRepository.findById(studentsRequset.getId())
                .orElseThrow(() -> new DataNotFoundException("Not found student id: "+ studentsRequset.getId()));

        students.setFullName(studentsRequset.getFullName());
        students.setGender(studentsRequset.getGender());
        students.setAge(studentsRequset.getAge());
        return studentsRepository.save(students);
    }

    @Override
    public Students deleteStudent(StudentsRequset studentsRequset) throws Exception {
        Students students = studentsRepository.findById(studentsRequset.getId())
                .orElseThrow(() -> new DataNotFoundException("Not found student id: "+ studentsRequset.getId()));
        students.setStatus(1);
        return studentsRepository.save(students);
    }

    private StudentsResponse convertToRes(Students students){
            return StudentsResponse.builder()
                    .fullName(students.getFullName())
                    .age(students.getAge())
                    .gender(students.getGender())
                    .status(students.getStatus())
                    .build();
    }
}

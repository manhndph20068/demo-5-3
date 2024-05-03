package com.example.demohm53.service.impl;

import com.example.demohm53.dto.request.StudentSubjectRequest;
import com.example.demohm53.dto.response.StudentOfSubjectResponse;
import com.example.demohm53.dto.response.StudentSubjectResponse;
import com.example.demohm53.dto.response.SubjectOfStutentResponse;
import com.example.demohm53.entity.StudentSubject;
import com.example.demohm53.entity.Students;
import com.example.demohm53.entity.Subjects;
import com.example.demohm53.exceptions.DataNotFoundException;
import com.example.demohm53.repository.StudentSubjectRepository;
import com.example.demohm53.repository.StudentsRepository;
import com.example.demohm53.repository.SubjectsRepository;
import com.example.demohm53.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Override
    public List<StudentSubjectResponse> getListStudentSubject() {
        List<StudentSubjectResponse> studentSubjectResponseList = studentSubjectRepository.findAll().stream()
                .map(this::convertToStudentSubjectResponse)
                .collect(Collectors.toList());
        return studentSubjectResponseList;
    }

    @Override
    public Page<StudentSubject> getListStudentSubjectPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentSubjectRepository.findAll(pageable);
    }


    @Override
    public StudentSubject addStudentToSubject(StudentSubjectRequest studentSubjectRequest) throws DataNotFoundException {

        Students students = studentsRepository.findById(studentSubjectRequest.getStudentId())
                .orElseThrow(() -> new DataNotFoundException("Not found student id: " + studentSubjectRequest.getStudentId()));

        Subjects subjects = subjectsRepository.findById(studentSubjectRequest.getSubjectId())
                .orElseThrow(() -> new DataNotFoundException("Not found subject id: " + studentSubjectRequest.getSubjectId()));

        StudentSubject studentSubject = StudentSubject.builder()
                .students(students)
                .subjects(subjects)
                .status(0)
                .build();
        return studentSubjectRepository.save(studentSubject);
    }

    @Override
    public SubjectOfStutentResponse findSubjectOfStutentResponse(StudentSubjectRequest studentSubjectRequest) throws Exception {
        Students students = studentsRepository.findById(studentSubjectRequest.getStudentId())
                .orElseThrow(() -> new DataNotFoundException("Not found student id: " + studentSubjectRequest.getStudentId()));

        List<Object[]> listResult = studentSubjectRepository.findSubjectsByStudentId(students.getId());
        List<Subjects> subjectsList = listResult.stream()
                .map(result -> Subjects.builder()
                        .id((Integer) result[0])
                        .name((String) result[1])
                        .description((String) result[2])
                        .build())
                .collect(Collectors.toList());

        SubjectOfStutentResponse subjectOfStutentResponse = SubjectOfStutentResponse.builder()
                .studentId(studentSubjectRequest.getStudentId())
                .subjectsList(subjectsList)
                .build();
        return subjectOfStutentResponse;
    }

    @Override
    public StudentOfSubjectResponse findStudentOfSubjectResponse(StudentSubjectRequest studentSubjectRequest) throws Exception {
        Subjects subjects = subjectsRepository.findById(studentSubjectRequest.getSubjectId())
                .orElseThrow(() -> new DataNotFoundException("Not found subject id: " + studentSubjectRequest.getSubjectId()));

        List<Object[]> listResult = studentSubjectRepository.findSubjectsInforAndTotalCountStudentId(subjects.getId());

        List<Object[]> listStudentsBySubjectId = studentsRepository.findStudentsBySubjectId(studentSubjectRequest.getSubjectId());
        List<Students> studentsList = listStudentsBySubjectId.stream()
                .map(result -> Students.builder()
                        .id((Integer) result[0])
                        .fullName((String) result[1])
                        .build())
                .collect(Collectors.toList());

        Long totalStudent = (Long) listResult.get(0)[2];
        Integer totalStudentInteger = totalStudent != null ? totalStudent.intValue() : null;
        StudentOfSubjectResponse studentOfSubjectResponse = StudentOfSubjectResponse.builder()
                .subjectId(subjects.getId())
                .subjectName(subjects.getName())
                .totalStudent(totalStudentInteger)
                .studentsList(studentsList)
                .build();
        return studentOfSubjectResponse;
    }

    private StudentSubjectResponse convertToStudentSubjectResponse(StudentSubject studentSubject) {
        return StudentSubjectResponse.builder()
                .fullNameOfStudent(studentSubject.getStudents().getFullName())
                .age(studentSubject.getStudents().getAge())
                .gender(studentSubject.getStudents().getGender())
                .subjectName(studentSubject.getSubjects().getName())
                .description(studentSubject.getSubjects().getDescription())
                .status(studentSubject.getStatus())
                .build();
    }
}

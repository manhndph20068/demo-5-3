package com.example.demohm53.service.impl;

import com.example.demohm53.dto.request.StudentSubjectRequest;
import com.example.demohm53.dto.response.InforStudentSubject;
import com.example.demohm53.dto.response.InforSubjectOfStudent;
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

        if (studentSubjectRepository.countByStudentIdAndSubjectId(students.getId(), subjects.getId()) > 0) {
            throw new RuntimeException("Student has been added to this subject");
        }

        if (students.getStatus() == 1 || subjects.getStatus() == 1) {
            throw new RuntimeException("Student or Subject has been deleted");
        }

        StudentSubject studentSubject = StudentSubject.builder()
                .students(students)
                .subjects(subjects)
                .status(0)
                .build();
        return studentSubjectRepository.save(studentSubject);
    }

    @Override
    public StudentSubject completeSubject(StudentSubjectRequest studentSubjectRequest) throws Exception {
        StudentSubject studentSubject = studentSubjectRepository.findById(studentSubjectRequest.getId())
                .orElseThrow(() -> new DataNotFoundException("Not found studentSubject id: " + studentSubjectRequest.getStudentId()));

        studentSubject.setStatus(1);
        return studentSubjectRepository.save(studentSubject);
    }

    @Override
    public SubjectOfStutentResponse findSubjectOfStutentResponse(StudentSubjectRequest studentSubjectRequest) throws Exception {
        Students students = studentsRepository.findById(studentSubjectRequest.getStudentId())
                .orElseThrow(() -> new DataNotFoundException("Not found student id: " + studentSubjectRequest.getStudentId()));

        List<Object[]> listResult = studentSubjectRepository.findSubjectsByStudentId(students.getId());

        List<InforSubjectOfStudent> subjectsList = listResult.stream()
                .map(result -> InforSubjectOfStudent.builder()
                        .subjectId((Integer) result[0])
                        .subjectName((String) result[1])
                        .subjectDescription((String) result[2])
                        .statusSubject((Integer) result[3])
                        .build())
                .collect(Collectors.toList());

        SubjectOfStutentResponse subjectOfStutentResponse = SubjectOfStutentResponse.builder()
                .studentId(studentSubjectRequest.getStudentId())
                .subjectsList(subjectsList)
                .totalSubject(studentSubjectRepository.getTotalSubjectsByStudentId(students.getId()))
                .build();
        return subjectOfStutentResponse;
    }

    @Override
    public StudentOfSubjectResponse findStudentOfSubjectResponse(StudentSubjectRequest studentSubjectRequest) throws Exception {
        Subjects subjects = subjectsRepository.findById(studentSubjectRequest.getSubjectId())
                .orElseThrow(() -> new DataNotFoundException("Not found subject id: " + studentSubjectRequest.getSubjectId()));

        List<Object[]> listResult = studentSubjectRepository.findSubjectsInforAndTotalCountStudentId(subjects.getId());

        List<Object[]> listStudentsBySubjectId = studentsRepository.findStudentsBySubjectId(studentSubjectRequest.getSubjectId());
        List<InforStudentSubject> studentsList = listStudentsBySubjectId.stream()
                .map(result -> InforStudentSubject.builder()
                        .id((Integer) result[0])
                        .fullName((String) result[1])
                        .statusSubject((Integer) result[2])
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
                .id(studentSubject.getId())
                .fullNameOfStudent(studentSubject.getStudents().getFullName())
                .age(studentSubject.getStudents().getAge())
                .gender(studentSubject.getStudents().getGender())
                .subjectName(studentSubject.getSubjects().getName())
                .description(studentSubject.getSubjects().getDescription())
                .status(studentSubject.getStatus())
                .build();
    }
}

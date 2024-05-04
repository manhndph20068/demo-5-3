package com.example.demohm53.controller;

import com.example.demohm53.ServiceResult;
import com.example.demohm53.config.AppConstant;
import com.example.demohm53.dto.request.PaginationRequest;
import com.example.demohm53.dto.request.StudentSubjectRequest;
import com.example.demohm53.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/studentSubject")
public class StudentSubjectController {

    @Autowired
    private StudentSubjectService studentSubjectService;

    @PostMapping("/getAllStudentSubject")
    public ResponseEntity<?> getAllStudentSubject(@RequestBody(required = false) PaginationRequest paginationRequest) {
        if (paginationRequest != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ServiceResult<>(
                                    AppConstant.SUCCESS,
                                    "success",
                                    studentSubjectService.getListStudentSubjectPaginate(paginationRequest.getPage(), paginationRequest.getSize())
                            )
                    );
        }else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ServiceResult<>(
                                    AppConstant.SUCCESS,
                                    "success",
                                    studentSubjectService.getListStudentSubject()
                            )
                    );
        }
    }

    @PostMapping("/findSubjectOfStudentById")
    public ResponseEntity<?> findSubjectOfStudentById(@RequestBody StudentSubjectRequest studentSubjectRequest) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ServiceResult<>(
                                    AppConstant.SUCCESS,
                                    "find success",
                                    studentSubjectService.findSubjectOfStutentResponse(studentSubjectRequest)
                            )
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ServiceResult<>(
                                    AppConstant.ERROR,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @PostMapping("/addStudentToSubject")
    public ResponseEntity<?> addStudentToSubject(@RequestBody StudentSubjectRequest studentSubjectRequest) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ServiceResult<>(
                                    AppConstant.SUCCESS,
                                    "add success",
                                    studentSubjectService.addStudentToSubject(studentSubjectRequest)
                            )
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ServiceResult<>(
                                    AppConstant.ERROR,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @PostMapping("/findStudentOfSubjectById")
    public ResponseEntity<?> findStudentOfSubjectById(@RequestBody StudentSubjectRequest studentSubjectRequest) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ServiceResult<>(
                                    AppConstant.SUCCESS,
                                    "find success",
                                    studentSubjectService.findStudentOfSubjectResponse(studentSubjectRequest)
                            )
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ServiceResult<>(
                                    AppConstant.ERROR,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @PostMapping("/doComplateSubject")
    public ResponseEntity<?> doComplateSubject(@RequestBody StudentSubjectRequest studentSubjectRequest) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ServiceResult<>(
                                    AppConstant.SUCCESS,
                                    "doComplateSubject success",
                                    studentSubjectService.completeSubject(studentSubjectRequest)
                            )
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ServiceResult<>(
                                    AppConstant.ERROR,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }
}

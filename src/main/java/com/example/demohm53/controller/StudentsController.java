package com.example.demohm53.controller;

import com.example.demohm53.ServiceResult;
import com.example.demohm53.config.AppConstant;
import com.example.demohm53.dto.request.StudentsRequset;
import com.example.demohm53.entity.Students;
import com.example.demohm53.service.StudentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/student")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping("/getAllStudents")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ServiceResult<>(
                                AppConstant.SUCCESS,
                                "success",
                                studentsService.getListStudents()
                        )
                );
    }

    @PostMapping("/addNewStudent")
    public ResponseEntity<?> addNewStudent(@Valid @RequestBody StudentsRequset studentsRequset,
                                           BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            String errorMessage = String.join(", ", errorMessages);
            return ResponseEntity.badRequest().body(new ServiceResult(
                            AppConstant.BAD_REQUEST,
                            errorMessage,
                            studentsService.getListStudents()
                    )
            );
        } else {
            Students students = studentsService.addNew(studentsRequset);
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            "success",
                            students
                    )
            );
        }
    }

    @PostMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody StudentsRequset studentsRequset) {
        try {
            Students students = studentsService.updateStudent(studentsRequset);
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            "update success",
                            students
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            e.getMessage(),
                           null
                    )
            );
        }
    }

    @PostMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent(@RequestBody StudentsRequset studentsRequset) {
        try {
            Students students = studentsService.deleteStudent(studentsRequset);
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            "delete success",
                            students
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            e.getMessage(),
                            null
                    )
            );
        }
    }
}

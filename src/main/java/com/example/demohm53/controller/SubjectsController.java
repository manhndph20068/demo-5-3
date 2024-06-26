package com.example.demohm53.controller;

import com.example.demohm53.ServiceResult;
import com.example.demohm53.config.AppConstant;
import com.example.demohm53.dto.request.SubjectRequest;
import com.example.demohm53.service.SubjectsService;
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
@RequestMapping("api/v1/subject")
public class SubjectsController {

    @Autowired
    private SubjectsService subjectsService;

    @GetMapping("/getAllSubject")
    public ResponseEntity<?> getAllSubject() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ServiceResult<>(
                                AppConstant.SUCCESS,
                                "success",
                                subjectsService.getListSubjects()
                        )
                );
    }

    @PostMapping("/addNewSubject")
    public ResponseEntity<?> addNewSubject(@Valid @RequestBody SubjectRequest subjectRequest,
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
                            null
                    )
            );
        } else {
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            "success",
                            subjectsService.addNew(subjectRequest)
                    )
            );
        }

    }

    @PostMapping("/updateSubject")
    public ResponseEntity<?> updateSubject(@RequestBody SubjectRequest subjectRequest) {
        try {
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            "update success",
                            subjectsService.updateSubject(subjectRequest)
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

    @PostMapping("/deleteSubject")
    public ResponseEntity<?> deleteSubject(@RequestBody SubjectRequest subjectRequest) {
        try {
            return ResponseEntity.ok().body(new ServiceResult(
                            AppConstant.SUCCESS,
                            "delete success",
                            subjectsService.deleteSubject(subjectRequest)
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

package com.example.demohm53.service.impl;

import com.example.demohm53.dto.request.SubjectRequest;
import com.example.demohm53.dto.response.SubjectResponse;
import com.example.demohm53.entity.Subjects;
import com.example.demohm53.exceptions.DataNotFoundException;
import com.example.demohm53.repository.SubjectsRepository;
import com.example.demohm53.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectsService {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Override
    public List<SubjectResponse> getListSubjects() {
        List<SubjectResponse> subjectResponseList = subjectsRepository.findAll().stream()
                .map(this::convertToSubjectResponse)
                .collect(Collectors.toList());
        return subjectResponseList;
    }

    @Override
    public Subjects addNew(SubjectRequest subjectRequest) {
        Subjects subjects = Subjects.builder()
                .name(subjectRequest.getName())
                .description(subjectRequest.getDescription())
                .status(0)
                .build();
        return subjectsRepository.save(subjects);
    }

    @Override
    public Subjects updateSubject(SubjectRequest subjectRequest) throws Exception {
        Subjects subjects = subjectsRepository.findById(subjectRequest.getId())
                .orElseThrow(() -> new DataNotFoundException("Not found subject id: "+ subjectRequest.getId()));

        subjects.setName(subjectRequest.getName());
        subjects.setDescription(subjectRequest.getDescription());
        return subjectsRepository.save(subjects);
    }

    @Override
    public Subjects deleteSubject(SubjectRequest subjectRequest) throws Exception {
        Subjects subjects = subjectsRepository.findById(subjectRequest.getId())
                .orElseThrow(() -> new DataNotFoundException("Not found subject id: "+ subjectRequest.getId()));

        subjects.setStatus(1);
        return subjectsRepository.save(subjects);
    }

    private SubjectResponse convertToSubjectResponse(Subjects subjects){
        return SubjectResponse.builder()
                .name(subjects.getName())
                .description(subjects.getDescription())
                .status(subjects.getStatus())
                .build();
    }

}

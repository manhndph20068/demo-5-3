package com.example.demohm53.repository;

import com.example.demohm53.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {

}

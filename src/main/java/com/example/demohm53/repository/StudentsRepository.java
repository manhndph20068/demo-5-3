package com.example.demohm53.repository;

import com.example.demohm53.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {

    @Query(value = "SELECT s.id, s.full_name, ss.status " +
            "FROM students s " +
            "INNER JOIN student_subject ss ON s.id = ss.student_id " +
            "INNER JOIN subjects sub ON ss.subject_id = sub.id " +
            "WHERE sub.id = :subjectId ", nativeQuery = true)
    List<Object[]> findStudentsBySubjectId(@Param("subjectId") Integer subjectId);
}

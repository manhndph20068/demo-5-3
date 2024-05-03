package com.example.demohm53.repository;

import com.example.demohm53.entity.StudentSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {

    Page<StudentSubject> findAll(Pageable pageable);

    @Query(value = "select s.id, s.name, s.description " +
            "from subjects s " +
            "inner join student_subject ss on s.id = ss.subject_id " +
            "inner join students stu on ss.student_id = stu.id " +
            "where stu.id = :studentId", nativeQuery = true)
    List<Object[]> findSubjectsByStudentId(@Param("studentId") Integer studentId);

    @Query(value = "select s.id as subjectId, s.name as subjectName, COUNT(ss.student_id) as totalStudent " +
            "from subjects s " +
            "inner join student_subject ss on s.id = ss.subject_id " +
            "where s.id = :subjectId " +
            "group by s.id, s.name "
            , nativeQuery = true)
    List<Object[]> findSubjectsInforAndTotalCountStudentId(@Param("subjectId") Integer subjectId);
}

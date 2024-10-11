package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StudentInfo;

public interface StudentRepository extends JpaRepository<StudentInfo, Integer> {

    @Query("SELECT s FROM student_info s WHERE s.teacher_id = :facilitatorId " +
           "AND (:nameLike IS NULL OR s.student_name LIKE %:nameLike%) " +
           "AND (:loginIdLike IS NULL OR s.login_id LIKE %:loginIdLike%)")
    Page<StudentInfo> findByFacilitatorIdWithFilters(int facilitatorId, String nameLike, String loginIdLike, Pageable pageable);
}
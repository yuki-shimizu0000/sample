package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "class_id")
    private int classId;

    @Column(name = "class_name")
    private String className;
}

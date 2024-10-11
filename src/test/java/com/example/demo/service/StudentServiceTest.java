package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        studentList = new ArrayList<>();
        // テスト用のデータを作成
        studentList.add(new Student(1, "佐藤", "foo123", 1, null));
        studentList.add(new Student(2, "鈴木", "bar456", 2, null));
        studentList.add(new Student(3, "田中", "baz789", 1, null));
    }

    @Test
    public void testGetStudents() {
        // ページリクエストの作成
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        
        // モックの設定
        Page<Student> studentPage = new PageImpl<>(studentList);
        when(studentRepository.findByFacilitatorIdWithFilters(1, null, null, pageRequest)).thenReturn(studentPageInfo);

        // メソッドを呼び出す
        Map<String, Object> response = studentService.getStudents(1, 1, 10, "id", "asc", null, null);
        
        // 検証
        assertNotNull(response);
        assertEquals(3, response.get("totalCount"));
        List<Map<String, Object>> students = (List<Map<String, Object>>) response.get("students");
        assertEquals(3, students.size());
        assertEquals("佐藤", students.get(0).get("name"));
    }

    @Test
    public void testGetStudents_NotFound() {
        // ページリクエストの作成
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        // モックの設定（空の結果）
        when(studentRepository.findByFacilitatorIdWithFilters(1, null, null, pageRequest)).thenReturn(new PageImpl<>(new ArrayList<>()));

        // メソッドを呼び出す
        assertThrows(ResponseStatusException.class, () -> {
            studentService.getStudents(1, 1, 10, "id", "asc", null, null);
        });
    }
}

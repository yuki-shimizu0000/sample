package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.service.StudentService;

import jakarta.persistence.EntityNotFoundException;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudents() {
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("totalCount", 3);
        when(studentService.getStudents(1, 1, 5, "id", "asc", null, null)).thenReturn(mockResponse);

        // コントローラーを呼び出す
        ResponseEntity<Map<String, Object>> responseEntity = studentController.getStudents(1, 1, 5, "id", "asc", null, null);

        // 検証
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(3, responseEntity.getBody().get("totalCount"));
    }

    @Test
    public void testGetStudents_NotFound() {
        // モックの設定
        when(studentService.getStudents(1, 1, 5, "id", "asc", null, null)).thenThrow(new EntityNotFoundException());

        // コントローラーを呼び出す
        ResponseEntity<Map<String, Object>> responseEntity = studentController.getStudents(1, 1, 5, "id", "asc", null, null);

        // 検証
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}

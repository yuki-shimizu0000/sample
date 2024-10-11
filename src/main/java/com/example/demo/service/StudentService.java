package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.StudentInfo;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Map<String, Object> getStudents(int facilitatorId, int page, int limit, String sort, String order, String nameLike, String loginIdLike) {
        // ソートの方向
        Sort.Direction sortDirection = order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        // ページリクエストを作成
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(sortDirection, sort));

        // 生徒情報を取得
        System.out.println(facilitatorId + nameLike + loginIdLike + pageRequest);
        Page<StudentInfo> studentPage = studentRepository.findByFacilitatorIdWithFilters(facilitatorId, nameLike, loginIdLike, pageRequest);

        // 合致する生徒がいない場合は404エラーを投げる
        if (studentPage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // 生徒情報をマップに整形
        List<Map<String, Object>> students = studentPage.stream()
                .map(student -> {
                    Map<String, Object> studentData = new HashMap<>();
                    studentData.put("id", student.getStudentId());
                    studentData.put("name", student.getStudentName());
                    studentData.put("loginId", student.getLoginId());

                    // クラス情報を含める
                    Map<String, Object> classroomData = new HashMap<>();
                    classroomData.put("id", student.getClassId()); // IDをクラスから取得
                    classroomData.put("name", student.getClassName()); // クラス名を取得

                    studentData.put("classroom", classroomData);
                    return studentData;
                })
                .collect(Collectors.toList());

        // 結果をマップとして返す
        Map<String, Object> response = new HashMap<>();
        response.put("students", students);
        response.put("totalCount", studentPage.getTotalElements());

        return response;
    }
}

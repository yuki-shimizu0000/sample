package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindByFacilitatorIdWithFilters() {
        // 既存のデータに基づくテスト
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));
        Page<Student> students = studentRepository.findByFacilitatorIdWithFilters(1, null, null, pageRequest);

        assertThat(students).isNotEmpty(); // 生徒が存在することを確認
        assertThat(students.getTotalElements()).isGreaterThan(0); // 生徒の総数が0より大きいことを確認
    }

    @Test
    public void testFindByFacilitatorIdWithFilters_NoResults() {
        // 条件に合うデータが存在しない場合
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));
        Page<Student> students = studentRepository.findByFacilitatorIdWithFilters(9999, null, null, pageRequest);

        assertThat(students).isEmpty(); // 結果が空であることを確認
    }
}

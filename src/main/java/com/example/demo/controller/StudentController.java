package com.example.demo.controller;

import java.util.Map;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 生徒のリストを取得するエンドポイント
     * ページング、ソート、フィルタリングのパラメータを基に結果を返す
     *
     * @param facilitatorId 教師ID（必須、数字のみ許可）
     * @param page ページ番号（任意、デフォルトは1）
     * @param limit 1ページあたりの表示件数（任意、デフォルトは5）
     * @param sort ソートに使うフィールド名（任意、デフォルトはid）
     * @param order ソートの順序（asc: 昇順、desc: 降順、デフォルトはasc）
     * @param nameLike 生徒名の部分一致検索用フィルター（任意）
     * @param loginIdLike ログインIDの部分一致検索用フィルター（任意）
     * @return リクエストパラメータに基づいた生徒リスト
     */
    @GetMapping("/students")
    public ResponseEntity<Map<String, Object>> getStudents(
            // 教師IDは必須項目
            @RequestParam(name = "facilitator_id") 
            @NotNull
            @Min(value = 1)
            int facilitatorId,

            // ページ番号（1以上の整数）
            @RequestParam(name = "page", defaultValue = "1") 
            @Min(value = 1)
            int page,

            // 1ページあたりの表示件数（制限なし）
            @RequestParam(name = "limit", defaultValue = "5") 
            @Min(value = 1)
            int limit,

            // ソート対象のフィールド（id, name, loginIdのみ許可）
            @RequestParam(name = "sort", defaultValue = "id") 
            @Pattern(regexp = "id|name|loginId")
            String sort,

            // ソートの順序（asc または desc のみ許可）
            @RequestParam(name = "order", defaultValue = "asc") 
            @Pattern(regexp = "asc|desc")
            String order,

            // 部分一致検索用の生徒名（任意、制限なし）
            @RequestParam(name = "name_like", required = false)
            String nameLike,

            // 部分一致検索用のログインID（任意、制限なし）
            @RequestParam(name = "loginId_like", required = false)
            String loginIdLike) {

        try {
            // 明示的に Map<String, Object> 型を指定
            Map<String, Object> response = studentService.getStudents(facilitatorId, page, limit, sort, order, nameLike, loginIdLike);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException ex) {
            // リクエストパラメータに対応する生徒が存在しない場合、ステータス404（Not Found）を返す
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException ex) {
            // リクエストパラメータに不正なパラメータが含まれている場合、ステータス400（Bad Request）を返します
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

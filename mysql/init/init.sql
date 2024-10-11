CREATE TABLE classes (
    class_id INT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(20) NOT NULL
);

CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(255) NOT NULL,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    teacher_id INT,
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

INSERT INTO classes (class_id, class_name) VALUES 
(1, 'クラスA'), 
(2, 'クラスB'), 
(3, 'クラスC');

INSERT INTO students (student_id, student_name, login_id, teacher_id, class_id) VALUES 
(1, '佐藤', 'foo123', 1, 1), 
(2, '鈴木', 'bar456', 2, 2), 
(3, '田中', 'baz789', 1, 3), 
(4, '加藤', 'hoge0000', 1, 1), 
(5, '太田', 'fuga1234', 2, 2), 
(6, '佐々木', 'piyo5678', 1, 3), 
(7, '小田原', 'fizz9999', 1, 1), 
(8, 'Smith', 'buzz777', 2, 2), 
(9, 'Johnson', 'fizzbuzz#123', 1, 3), 
(10, 'Williams', 'xxx:42', 1, 1);

CREATE VIEW student_info (
    student_id,
    student_name,
    login_id,
    teacher_id,
    class_id,
    class_name
) AS SELECT 
    students.student_id,
    students.student_name,
    students.login_id,
    students.teacher_id,
    students.class_id,
    classes.class_name
FROM 
    students  
INNER JOIN 
    classes 
ON 
    students.class_id = classes.class_id;
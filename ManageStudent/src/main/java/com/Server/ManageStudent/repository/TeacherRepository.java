package com.Server.ManageStudent.repository;

import com.Server.ManageStudent.entity.Student;
import com.Server.ManageStudent.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    boolean existsByUser_Id(Long userId);

    boolean existsByTeacherCode(String teacherCode);

    Optional<Teacher> findByUser_Id(Long id);
}

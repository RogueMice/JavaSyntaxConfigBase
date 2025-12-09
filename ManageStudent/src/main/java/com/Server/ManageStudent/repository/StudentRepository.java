package com.Server.ManageStudent.repository.JPA;

import com.Server.ManageStudent.repository.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByUser_Id(Long userId);

    boolean existsByStudentCode(String studentCode);

    Optional<Student> findByUser_Id(Long id);
}

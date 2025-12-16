package com.Server.ManageStudent.repository;

import com.Server.ManageStudent.dto.student.StudentViewDto;
import com.Server.ManageStudent.dto.user.UserViewDto;
import com.Server.ManageStudent.entity.Student;
import com.Server.ManageStudent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByUser_Id(Long userId);

    boolean existsByStudentCode(String studentCode);

    Optional<Student> findByUser_Id(Long id);

    @Query("""
    SELECT s FROM Student s
    JOIN FETCH s.user
    WHERE s.user.id = :userid""")
    Optional<?> getStudentDetailByUserId(@Param("userid") Long userid);
}

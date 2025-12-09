package com.Server.ManageStudent.repository.JPA;

import com.Server.ManageStudent.repository.entity.Student;
import com.Server.ManageStudent.repository.entity.User;
import com.Server.ManageStudent.repository.entity.roleDefine.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByRole(Role role);

    User findByIdAndRole(Long id, Role role);

    @Query("SELECT u.role FROM User u WHERE u.id = :id")
    Role findRoleById(@Param("id") Long userId);
}

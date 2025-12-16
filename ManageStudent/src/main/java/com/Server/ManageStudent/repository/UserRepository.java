package com.Server.ManageStudent.repository;

import com.Server.ManageStudent.entity.User;
import com.Server.ManageStudent.entity.roleDefine.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository <User,Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByRole(Role role);

    User findByIdAndRole(Long id, Role role);

    @Query("SELECT u.role FROM User u WHERE u.id = :id")
    Role findRoleById(@Param("id") Long userId);

    // Search by role optional và username or fullName like
    @Query("SELECT u FROM User u " +
            "WHERE (:role IS NULL OR u.role = :role) " +
            "AND (:keyword IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<User> searchByRoleAndName(@Param("role") Role role,
                                   @Param("keyword") String keyword,
                                   Pageable pageable);
}

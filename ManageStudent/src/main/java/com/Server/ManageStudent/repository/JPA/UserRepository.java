package com.Server.ManageStudent.repository.JPA;

import com.Server.ManageStudent.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID> {

//    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 " +
//            "THEN true " +
//            "ELSE false END FROM user u " +
//            "WHERE u.username = :username",
//            nativeQuery = true)
//    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}

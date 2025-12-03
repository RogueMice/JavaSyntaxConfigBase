package com.Server.ManageStudent.service.implement;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.UserDto;
import com.Server.ManageStudent.exception.CustomException;
import com.Server.ManageStudent.repository.JPA.UserRepository;
import com.Server.ManageStudent.repository.model.User;
import com.Server.ManageStudent.repository.model.role.Role;
import com.Server.ManageStudent.service.interfaces.IUserService;
import com.Server.ManageStudent.util.HashPasswordUtil;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseConfig<UUID> createOrUpdate(UserDto dto) {
        User user = Optional.ofNullable(dto.id)
                .flatMap(userRepository::findById)
                .orElseGet(User::new);

        boolean isUpdate = user.getId() != null;

        if(user.getId() == null && userRepository.existsByUsername(dto.userName))
        {
            throw new CustomException.ConflictException("UserName already  existing !");
        }

//        if(Role.valueOf(dto.roleType).equals(Role.ADMIN)) {
//            throw new CustomException.BadRequestException("You can register with admin role !");
//        }

        user.setUsername(dto.userName);
        user.setPassword(HashPasswordUtil.hash(dto.password));
        user.setFullName(dto.fullName);
        user.setAddress(dto.address);
        user.setRole(Role.valueOf(dto.roleType.toUpperCase()));

        user = userRepository.save(user);

        String message = isUpdate ? "User updated successfully" : "User created successfully";
        return ResponseConfig.<UUID>success(user.getId(), message);
    }
}

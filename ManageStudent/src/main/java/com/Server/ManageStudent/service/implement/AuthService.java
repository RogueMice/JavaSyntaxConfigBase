package com.Server.ManageStudent.service.implement;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.auth.LoginDto;
import com.Server.ManageStudent.dto.auth.RegisterDto;
import com.Server.ManageStudent.exception.CustomException;
import com.Server.ManageStudent.repository.UserRepository;
import com.Server.ManageStudent.entity.User;
import com.Server.ManageStudent.entity.roleDefine.Role;
import com.Server.ManageStudent.service.interfaces.IAuthService;
import com.Server.ManageStudent.util.HashPasswordUtil;
import com.Server.ManageStudent.util.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;;
    }

    @Override
    public ResponseConfig register(RegisterDto dto) {
        if(userRepository.existsByUsername(dto.userName))
        {
            throw new CustomException.ConflictException("UserName already  existing !");
        }

        if(Role.valueOf(dto.roleType.toUpperCase()).equals(Role.ADMIN)) {
            boolean adminExisting = userRepository.existsByRole(Role.ADMIN);
            if (adminExisting)
                throw new CustomException.BadRequestException("You can register with admin role !");
        }

        User user = new User();
        user.setUsername(dto.userName);
        user.setPassword(HashPasswordUtil.hash(dto.password));
        user.setFullName(dto.fullName);
        user.setRole(Role.valueOf(dto.roleType.toUpperCase()));
        user = userRepository.save(user);

        return ResponseConfig.success("id: "+user.getId(), "Register is successfully !");
    }

    @Override
    public ResponseConfig<Object> login(LoginDto dto) {
        User user = userRepository.findByUsername(dto.userName)
                .filter(u -> HashPasswordUtil.matches(dto.password, u.getPassword()))
                .orElseThrow(() -> new CustomException.NotFoundException("UserName or password invalid"));

        //create Token
        String token = jwtTokenProvider.generateToken(user.getId(), user.getRole());
        return new ResponseConfig<>(true, "Login successful", Map.of(
                "token", token
        ));
    }
}

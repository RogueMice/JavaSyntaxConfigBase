package com.Server.ManageStudent.service.implement;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.LoginDto;
import com.Server.ManageStudent.exception.CustomException;
import com.Server.ManageStudent.repository.JPA.UserRepository;
import com.Server.ManageStudent.repository.model.User;
import com.Server.ManageStudent.service.interfaces.IAuthService;
import com.Server.ManageStudent.util.HashPasswordUtil;
import com.Server.ManageStudent.util.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;;
    }

    @Override
    public ResponseConfig<Object> login(LoginDto dto) {
        User user = userRepository.findByUsername(dto.userName)
                .filter(u -> HashPasswordUtil.matches(dto.password, u.getPassword()))
                .orElseThrow(() -> new CustomException.NotFoundException("UserName or password invalid"));

        //create Token
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
        return new ResponseConfig<>(true, "Login successful", Map.of(
                "token", token
        ));
    }
}

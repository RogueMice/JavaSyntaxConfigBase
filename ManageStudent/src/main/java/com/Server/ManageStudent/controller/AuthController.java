package com.Server.ManageStudent.controller;

import com.Server.ManageStudent.dto.auth.LoginDto;
import com.Server.ManageStudent.dto.auth.RegisterDto;
import com.Server.ManageStudent.service.interfaces.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}

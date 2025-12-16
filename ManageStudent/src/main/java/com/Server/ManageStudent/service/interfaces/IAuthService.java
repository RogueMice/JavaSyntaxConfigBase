package com.Server.ManageStudent.service.interfaces;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.auth.LoginDto;
import com.Server.ManageStudent.dto.auth.RegisterDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {

    ResponseConfig register(@RequestBody RegisterDto dto);

    ResponseConfig<Object> login(@RequestBody LoginDto dto);
}

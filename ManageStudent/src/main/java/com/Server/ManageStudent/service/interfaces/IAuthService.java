package com.Server.ManageStudent.service.interfaces;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.LoginDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {
    ResponseConfig<Object> login(@RequestBody LoginDto dto);
}

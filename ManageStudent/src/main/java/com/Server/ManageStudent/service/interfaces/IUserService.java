package com.Server.ManageStudent.service.interfaces;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.UserDto;

import java.util.UUID;

public interface IUserService {
    ResponseConfig<UUID> createOrUpdate(UserDto dto);
}

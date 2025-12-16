package com.Server.ManageStudent.service.interfaces;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.user.UserViewDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserService {

    ResponseConfig<List<UserViewDto>> getAllUsers(int page, int size, String roleType, String keyword);

    ResponseConfig<?> getUserById(@RequestParam("userId") Long userId);
}

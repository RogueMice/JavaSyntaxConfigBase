package com.Server.ManageStudent.dto.user;

import com.Server.ManageStudent.dto.student.StudentViewDto;
import com.Server.ManageStudent.dto.teacher.TeacherViewDto;

import java.time.LocalDateTime;

public class UserViewDto {
    public Long id;

    public String userName;

    public String fullName;

    public String roleType;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public StudentViewDto studentViewDto;

    public TeacherViewDto teacherViewDto;
}

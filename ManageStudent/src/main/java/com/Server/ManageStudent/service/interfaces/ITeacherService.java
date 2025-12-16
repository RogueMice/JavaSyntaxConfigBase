package com.Server.ManageStudent.service.interfaces;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.student.StudentDto;
import com.Server.ManageStudent.dto.student.StudentPatchDto;
import com.Server.ManageStudent.dto.teacher.TeacherPatchDto;
import com.Server.ManageStudent.dto.teacher.TeacherRequestDto;

public interface ITeacherService {

    ResponseConfig addOrUpdateTeacher(TeacherRequestDto dto);

    ResponseConfig<String> updateTeacher(TeacherPatchDto dto, Long userId);

    ResponseConfig<String> deleteTeacher(Long id);
}

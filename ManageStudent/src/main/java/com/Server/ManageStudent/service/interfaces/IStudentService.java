package com.Server.ManageStudent.service.interfaces;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.student.StudentDto;
import com.Server.ManageStudent.dto.student.StudentPatchDto;

public interface IStudentService {
    ResponseConfig<String> addOrUpdateStudent(StudentDto dto);

    ResponseConfig<String> updateStudent(StudentPatchDto dto, Long userId);

    ResponseConfig<String> deleteStudent(Long id);

}

package com.Server.ManageStudent.controller;

import com.Server.ManageStudent.dto.student.StudentDto;
import com.Server.ManageStudent.dto.student.StudentPatchDto;
import com.Server.ManageStudent.service.interfaces.IStudentService;
import com.Server.ManageStudent.util.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private final IStudentService studentService;
    private final JwtTokenProvider jwtTokenProvider;

    public StudentController(IStudentService studentService, JwtTokenProvider jwtTokenProvider) {
        this.studentService = studentService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("addOrUpdate")
    public ResponseEntity<?> createOrUpdate(@RequestBody StudentDto dto) {
        return ResponseEntity.ok(studentService.addOrUpdateStudent(dto));
    }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestBody StudentPatchDto dto) {
        return ResponseEntity.ok(studentService.updateStudent(dto,jwtTokenProvider.getUserId()));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}

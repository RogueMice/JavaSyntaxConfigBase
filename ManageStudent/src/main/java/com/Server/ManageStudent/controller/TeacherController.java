package com.Server.ManageStudent.controller;

import com.Server.ManageStudent.dto.student.StudentPatchDto;
import com.Server.ManageStudent.dto.teacher.TeacherPatchDto;
import com.Server.ManageStudent.dto.teacher.TeacherRequestDto;
import com.Server.ManageStudent.service.interfaces.ITeacherService;
import com.Server.ManageStudent.util.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {

    private final ITeacherService teacherService;
    private final JwtTokenProvider jwtTokenProvider;

    public TeacherController(ITeacherService teacherService, JwtTokenProvider jwtTokenProvider) {
        this.teacherService = teacherService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("addOrUpdate")
    public ResponseEntity<?> addOrUpdate(@Valid @RequestBody TeacherRequestDto dto) {
        return ResponseEntity.ok(teacherService.addOrUpdateTeacher(dto));
    }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestBody TeacherPatchDto dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(dto, jwtTokenProvider.getUserId()));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long teacherId) {
        return ResponseEntity.ok(teacherService.deleteTeacher(teacherId));
    }
}

package com.Server.ManageStudent.service.implement;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.teacher.TeacherPatchDto;
import com.Server.ManageStudent.dto.teacher.TeacherRequestDto;
import com.Server.ManageStudent.entity.Student;
import com.Server.ManageStudent.entity.Teacher;
import com.Server.ManageStudent.entity.roleDefine.Role;
import com.Server.ManageStudent.exception.CustomException;
import com.Server.ManageStudent.repository.TeacherRepository;
import com.Server.ManageStudent.repository.UserRepository;
import com.Server.ManageStudent.service.interfaces.ITeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseConfig addOrUpdateTeacher(TeacherRequestDto dto) {
        if(dto.id == null) {//create
            if(dto.userId == null) {
                throw new CustomException.BadRequestException("user id is null");
            }
            if(!userRepository.findRoleById(dto.userId).equals(Role.TEACHER)) {
                throw new CustomException.BadRequestException("User is not a TEACHER");
            }
            if(teacherRepository.existsByUser_Id(dto.userId)) {
                throw new CustomException.BadRequestException("Teacher already exists for this userId: "+dto.userId);
            }
            if(teacherRepository.existsByTeacherCode(dto.teacherCode)){
                throw new CustomException.BadRequestException("Student code exists for this studentCode: "+dto.teacherCode);
            }
            Teacher teacher = new Teacher();
            teacher.setAddress(dto.address);
            teacher.setPhoneNumber(dto.phoneNumber);
            teacher.setRelativePhone(dto.relativePhone);
            teacher.setTeacherCode(dto.teacherCode);
            teacher.setSalary(dto.salary);
            teacher.setUser(userRepository.findById(dto.userId).get());

            teacherRepository.save(teacher);
            return ResponseConfig.success(teacher.getId().toString(), "Added teacher successfully with id:"+ teacher.getId());
        }
        else { //update
            Teacher teacher = teacherRepository.findById(dto.id)
                    .orElseThrow(() -> new CustomException.BadRequestException("Teacher id not found"));
            teacher.setAddress(dto.address);
            teacher.setPhoneNumber(dto.phoneNumber);
            teacher.setRelativePhone(dto.relativePhone);
            teacher.setTeacherCode(dto.teacherCode);
            teacher.setSalary(dto.salary);

            teacherRepository.save(teacher);
            return ResponseConfig.success("id: "+teacher.getId(), "Updated teacher successfully");
        }
    }

    @Override
    public ResponseConfig<String> updateTeacher(TeacherPatchDto dto, Long userId) {
        Teacher teacher = teacherRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CustomException.NotFoundException("Teacher not found for userId: " + userId));

        if(!(teacher.getUser().getId() == userId)) {
            throw new CustomException.UnauthorizedException("You are not allowed to update this teacher");
        }

        if(dto.address != null) teacher.setAddress(dto.address);
        if(dto.phoneNumber != null) teacher.setPhoneNumber(dto.phoneNumber);
        if(dto.relativePhone != null) teacher.setRelativePhone(dto.relativePhone);

        teacherRepository.save(teacher);
        return ResponseConfig.success("id: "+teacher.getId(), "Updated successfully");
    }

    @Override
    public ResponseConfig<String> deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new CustomException.NotFoundException("Teacher id: " + id+" is not found!"));
        teacherRepository.delete(teacher);
        return ResponseConfig.success("id: "+teacher.getId(), "Deleted teacher successfully");
    }
}

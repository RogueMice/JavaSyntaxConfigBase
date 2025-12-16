package com.Server.ManageStudent.service.implement;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.student.StudentDto;
import com.Server.ManageStudent.dto.student.StudentPatchDto;
import com.Server.ManageStudent.exception.CustomException;
import com.Server.ManageStudent.repository.StudentRepository;
import com.Server.ManageStudent.repository.UserRepository;
import com.Server.ManageStudent.entity.Student;
import com.Server.ManageStudent.entity.roleDefine.Role;
import com.Server.ManageStudent.service.interfaces.IStudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseConfig addOrUpdateStudent(StudentDto dto) {
        if(dto.id == null) {//create
            if(dto.userId == null) {
                throw new CustomException.BadRequestException("user id is null");
            }
            if(!userRepository.findRoleById(dto.userId).equals(Role.STUDENT)) {
                throw new CustomException.BadRequestException("User is not a STUDENT");
            }
            if(studentRepository.existsByUser_Id(dto.userId)) {
                throw new CustomException.BadRequestException("Student already exists for this userId: "+dto.userId);
            }
            if(studentRepository.existsByStudentCode(dto.studentCode)){
                throw new CustomException.BadRequestException("Student code exists for this studentCode: "+dto.studentCode);
            }
            Student student = new Student();
            student.setAddress(dto.address);
            student.setPhoneNumber(dto.phoneNumber);
            student.setRelativePhone(dto.relativePhone);
            student.setStudentCode(dto.studentCode);
            student.setUser(userRepository.findById(dto.userId).get());

            studentRepository.save(student);
            return ResponseConfig.success(student.getId().toString(), "Added student successfully with id:"+ student.getId());
        }
        else { //update
            Student student = studentRepository.findById(dto.id)
                    .orElseThrow(() -> new CustomException.BadRequestException("Student id not found"));
            student.setAddress(dto.address);
            student.setPhoneNumber(dto.phoneNumber);
            student.setRelativePhone(dto.relativePhone);
            student.setStudentCode(dto.studentCode);

            studentRepository.save(student);
            return ResponseConfig.success("id: "+student.getId(), "Updated student successfully");
        }
    }

    @Override
    public ResponseConfig updateStudent(StudentPatchDto dto, Long userId) {
        Student student = studentRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CustomException.NotFoundException("Student not found for userId: " + userId));

        if(!(student.getUser().getId() == userId)) {
            throw new CustomException.UnauthorizedException("You are not allowed to update this student");
        }

        if(dto.address != null) student.setAddress(dto.address);
        if(dto.phoneNumber != null) student.setPhoneNumber(dto.phoneNumber);
        if(dto.relativePhone != null) student.setRelativePhone(dto.relativePhone);

        studentRepository.save(student);
        return ResponseConfig.success("id: "+student.getId(), "Updated successfully");
    }

    @Override
    public ResponseConfig<String> deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new CustomException.NotFoundException("Student id: "+id+" is not found!"));

        studentRepository.delete(student);
        return ResponseConfig.success("id: "+student.getId(), "Deleted successfully");
    }

}

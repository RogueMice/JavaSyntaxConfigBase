package com.Server.ManageStudent.service.implement;
import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.student.StudentViewDto;
import com.Server.ManageStudent.dto.teacher.TeacherViewDto;
import com.Server.ManageStudent.dto.user.UserViewDto;
import com.Server.ManageStudent.entity.Student;
import com.Server.ManageStudent.entity.Teacher;
import com.Server.ManageStudent.entity.User;
import com.Server.ManageStudent.entity.roleDefine.Role;
import com.Server.ManageStudent.exception.CustomException;
import com.Server.ManageStudent.repository.StudentRepository;
import com.Server.ManageStudent.repository.TeacherRepository;
import com.Server.ManageStudent.repository.UserRepository;
import com.Server.ManageStudent.service.interfaces.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public UserService(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public ResponseConfig<List<UserViewDto>> getAllUsers(int page, int size, String roleType, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Role role = (roleType != null && !roleType.isEmpty()) ? Role.valueOf(roleType.toUpperCase()) : null;

        Page<User> users = userRepository.searchByRoleAndName(role, keyword, pageRequest);

        List<UserViewDto> result = users.stream()
                .map(user -> {
                    UserViewDto dto = new UserViewDto();
                    dto.id = user.getId();
                    dto.userName = user.getUsername();
                    dto.fullName = user.getFullName();
                    dto.roleType = user.getRole().toString();
                    dto.createdAt = user.getCreatedAt();
                    dto.updatedAt = user.getUpdatedAt();
                    return dto;
                }).toList();
        return ResponseConfig.success(result, "users");
    }

    @Override
    public ResponseConfig<?> getUserById(Long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new CustomException.NotFoundException("user not found by id = "+userId));

        switch (user.getRole()) {
            case STUDENT -> {
                Student student = studentRepository.findByUser_Id(user.getId())
                        .orElseThrow(() -> new CustomException.NotFoundException("student not found by id = "+user.getId()));
                UserViewDto userViewDto = new UserViewDto();
                userViewDto.id = student.getUser().getId();
                userViewDto.userName = student.getUser().getUsername();
                userViewDto.fullName = student.getUser().getFullName();
                userViewDto.roleType = student.getUser().getRole().toString();
                userViewDto.createdAt = student.getUser().getCreatedAt();
                userViewDto.updatedAt = student.getUser().getUpdatedAt();

                StudentViewDto studentViewDto = new StudentViewDto();
                studentViewDto.address = student.getAddress();
                studentViewDto.phoneNumber = student.getPhoneNumber();
                studentViewDto.relativePhone =  student.getPhoneNumber();
                studentViewDto.studentCode = student.getStudentCode();

                userViewDto.studentViewDto = studentViewDto;
                return ResponseConfig.success(userViewDto, "student details");
            }
            case TEACHER ->  {
                Teacher teacher = teacherRepository.findByUser_Id(user.getId())
                        .orElseThrow(() -> new CustomException.NotFoundException("teacher not found by id = "+user.getId()));
                UserViewDto userViewDto = new UserViewDto();
                userViewDto.id = teacher.getUser().getId();
                userViewDto.userName = teacher.getUser().getUsername();
                userViewDto.fullName = teacher.getUser().getFullName();
                userViewDto.roleType = teacher.getUser().getRole().toString();
                userViewDto.createdAt = teacher.getUser().getCreatedAt();
                userViewDto.updatedAt = teacher.getUser().getUpdatedAt();

                TeacherViewDto teacherViewDto = new TeacherViewDto();
                teacherViewDto.address = teacher.getAddress();
                teacherViewDto.phoneNumber = teacher.getPhoneNumber();
                teacherViewDto.relativePhone = teacher.getPhoneNumber();
                teacherViewDto.salary = teacher.getSalary();
                teacherViewDto.teacherCode = teacher.getTeacherCode();

                userViewDto.teacherViewDto = teacherViewDto;
                return ResponseConfig.success(userViewDto, "teacher details");
            }
            case ADMIN -> {
                return ResponseConfig.success(user,"admin details");
            }
        }
        return ResponseConfig.success(user, "user");
    }
}

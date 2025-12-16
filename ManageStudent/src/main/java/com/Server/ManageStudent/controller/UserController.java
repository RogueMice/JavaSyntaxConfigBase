package com.Server.ManageStudent.controller;
import com.Server.ManageStudent.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAllUser")
    public  ResponseEntity<?> getAllUser(@RequestParam int page, @RequestParam int size, @RequestParam String typeRole, @RequestParam String keyword) {
        return ResponseEntity.ok(userService.getAllUsers(page, size,typeRole,keyword));
    }

    @GetMapping("getUserDetailByUserId")
    public ResponseEntity<?> getUserDetailByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}

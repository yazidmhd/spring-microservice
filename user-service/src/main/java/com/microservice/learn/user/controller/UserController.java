package com.microservice.learn.user.controller;

import com.microservice.learn.user.VO.ResponseTemplateVO;
import com.microservice.learn.user.entity.User;
import com.microservice.learn.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("inside saveUser of UserController");
        return userService.saveUser(user);
    }

    //create getUser along with the department it belongs too
    //need to create wrapper object(VO) that will contain the user and department object
    //department object needs to be inside this project
    //you don't need to create a user class inside the department project
    //cause its a duplication of code
    //there might be repetition of code - common classes and properties
    //but there is no coupling between the services
    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }
}

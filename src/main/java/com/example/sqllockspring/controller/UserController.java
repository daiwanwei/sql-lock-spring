package com.example.sqllockspring.controller;


import com.example.sqllockspring.dto.RegisterDto;
import com.example.sqllockspring.dto.UpdateUserDto;
import com.example.sqllockspring.persistence.model.User;
import com.example.sqllockspring.persistence.repository.UserDao;
import com.example.sqllockspring.pkg.Resp;
import com.example.sqllockspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/{id}")
    public Resp.DataResp<User> getUser(@PathVariable("id") Long id) {
        try {
            User user=userService.findUserById(id);
            return new Resp.DataResp<>(200,"OK",user);
        }catch (Exception e){
            return new Resp.DataResp<>(500,e.getMessage(),null);
        }

    }

    @GetMapping(value="/")
    public Resp.DataResp<List<User>> getUsers() {
        try {
            List<User> users=userService.findAllUser();
            return new Resp.DataResp<>(200,"OK",users);
        }catch (Exception e){
            return new Resp.DataResp<>(500,e.getMessage(),null);
        }

    }

    @PostMapping(value="/register")
    public Resp.NonDataResp register(@RequestBody RegisterDto dto) {
        try {
            userService.register(dto);
            return new Resp.NonDataResp(200,"OK");
        }catch (Exception e){
            return new Resp.NonDataResp(500,e.getMessage());
        }
    }

    @PostMapping(value="/{id}")
    public Resp.NonDataResp updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDto dto) {
        try {
            userService.updateUserById(id,dto);
            return new Resp.NonDataResp(200,"OK");
        }catch (Exception e){
            return new Resp.NonDataResp(500,e.getMessage());
        }
    }
}

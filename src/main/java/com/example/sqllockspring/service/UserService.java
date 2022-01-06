package com.example.sqllockspring.service;

import com.example.sqllockspring.dto.RegisterDto;
import com.example.sqllockspring.dto.UpdateUserDto;
import com.example.sqllockspring.persistence.model.User;
import com.example.sqllockspring.persistence.repository.UserDao;
import com.example.sqllockspring.pkg.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void updateUserById(Long id, UpdateUserDto dto) throws Exception {
        Optional<User> opt = userDao.findById(id);
        opt.orElseThrow(() -> new Exception("user not found"));
        User user = opt.get();
        user.setUserName(dto.getUserName());
        Thread.sleep(1000);
        userDao.save(user);
    }

    public void register(RegisterDto dto) {
        User user=new User();
        user.setUserName(dto.getUserName());
        userDao.save(user);
    }

    public List<User> findAllUser() {
        return userDao.findAll();
    }

    public User findUserById(Long id) throws Exception {
        Optional<User> opt = userDao.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }
}

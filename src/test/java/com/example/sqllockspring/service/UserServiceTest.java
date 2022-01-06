package com.example.sqllockspring.service;

import com.example.sqllockspring.dto.UpdateUserDto;
import com.example.sqllockspring.persistence.model.User;
import com.example.sqllockspring.persistence.repository.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import java.time.LocalDate;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void updateUser() throws Exception {
        Thread t1 = new UpdateUserThread("wooo111111");
        t1.start();
        Thread.sleep(500);
        Thread t2 = new UpdateUserThread("dooo111111");
        t2.start();
        t1.join();
        t2.join();
        User user = userService.findUserById(8L);
        Assertions.assertEquals("wooo111111", user.getUserName());
    }

    public class UpdateUserThread extends Thread {
        private String name;

        public UpdateUserThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            UpdateUserDto dto = new UpdateUserDto(this.name);
            try {
                System.out.println(Thread.currentThread().getName() + " update begin");
                userService.updateUserById(8L, dto);
                System.out.println(Thread.currentThread().getName() + " update success");
            } catch (OptimisticLockingFailureException e) {
                System.out.println("Optimistic locking occur");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

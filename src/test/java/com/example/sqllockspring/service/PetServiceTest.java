package com.example.sqllockspring.service;

import com.example.sqllockspring.dto.UpdatePetDto;
import com.example.sqllockspring.dto.UpdateUserDto;
import com.example.sqllockspring.persistence.model.Pet;
import com.example.sqllockspring.persistence.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import javax.persistence.LockTimeoutException;
import javax.persistence.PessimisticLockException;

@SpringBootTest
public class PetServiceTest {
    @Autowired
    private PetService petService;

    @Test
    public void updatePet() throws Exception {
        Thread t1 = new UpdatePetThread("wooo111111");
        t1.start();
        Thread.sleep(100);
        Thread t2 = new UpdatePetThread("dooo111111");
        t2.start();
        t1.join();
        t2.join();
        Pet pet = petService.findPetById(12L);
        Assertions.assertEquals("dooo111111", pet.getName());
    }

    public class UpdatePetThread extends Thread {
        private String name;

        public UpdatePetThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            UpdatePetDto dto = new UpdatePetDto(this.name);
            try {
                long start = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " update begin");
                petService.updatePetById(12L, dto);
                System.out.println(Thread.currentThread().getName() + " update success");
                System.out.println(Thread.currentThread().getName()+" block time was: " + (System.currentTimeMillis() - start));
            } catch (PessimisticLockException e) {
                System.out.println("PessimisticLockException occur");
            }catch (LockTimeoutException e) {
                System.out.println("LockTimeoutException occur");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

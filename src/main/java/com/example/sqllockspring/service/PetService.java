package com.example.sqllockspring.service;

import com.example.sqllockspring.dto.AdoptDto;
import com.example.sqllockspring.dto.RegisterDto;
import com.example.sqllockspring.dto.UpdatePetDto;
import com.example.sqllockspring.dto.UpdateUserDto;
import com.example.sqllockspring.persistence.model.Pet;
import com.example.sqllockspring.persistence.model.User;
import com.example.sqllockspring.persistence.repository.PetDao;
import com.example.sqllockspring.persistence.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetDao petDao;

    @Transactional(readOnly = false)
    public void updatePetById(Long id, UpdatePetDto dto) throws Exception {
        System.out.println("before find pet");
        Optional<Pet> opt = petDao.findById(id);
        System.out.println("after find pet");
        opt.orElseThrow(() -> new Exception("user not found"));
        Pet pet = opt.get();
        pet.setName(dto.getName());
        Thread.sleep(1000);
        System.out.println("before save pet");
        petDao.save(pet);
        System.out.println("after save pet");
//        Thread.sleep(1000);
    }

    public void adopt(AdoptDto dto) {
        Pet pet = new Pet();
        pet.setName(dto.getName());
        petDao.save(pet);
    }

    public List<Pet> findAllPet() {
        return petDao.findAll();
    }

    @Transactional(readOnly = false)
    public Pet findPetById(Long id) {
        Optional<Pet> opt = petDao.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }
}

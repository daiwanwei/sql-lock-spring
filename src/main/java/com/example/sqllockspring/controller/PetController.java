package com.example.sqllockspring.controller;


import com.example.sqllockspring.dto.AdoptDto;
import com.example.sqllockspring.dto.RegisterDto;
import com.example.sqllockspring.dto.UpdatePetDto;
import com.example.sqllockspring.dto.UpdateUserDto;
import com.example.sqllockspring.persistence.model.Pet;
import com.example.sqllockspring.persistence.model.User;
import com.example.sqllockspring.pkg.Resp;
import com.example.sqllockspring.service.PetService;
import com.example.sqllockspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/pet")
@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping(value="/{id}")
    public Resp.DataResp<Pet> getPet(@PathVariable("id") Long id) {
        try {
            Pet pet=petService.findPetById(id);
            return new Resp.DataResp<>(200,"OK",pet);
        }catch (Exception e){
            return new Resp.DataResp<>(500,e.getMessage(),null);
        }

    }

    @GetMapping(value="/")
    public Resp.DataResp<List<Pet>> getPets() {
        try {
            List<Pet> pets=petService.findAllPet();
            return new Resp.DataResp<>(200,"OK",pets);
        }catch (Exception e){
            return new Resp.DataResp<>(500,e.getMessage(),null);
        }

    }

    @PostMapping(value="/adopt")
    public Resp.NonDataResp adopt(@RequestBody AdoptDto dto) {
        try {
            petService.adopt(dto);
            return new Resp.NonDataResp(200,"OK");
        }catch (Exception e){
            return new Resp.NonDataResp(500,e.getMessage());
        }
    }

    @PostMapping(value="/{id}")
    public Resp.NonDataResp updatePet(@PathVariable("id") Long id, @RequestBody UpdatePetDto dto) {
        try {
            petService.updatePetById(id,dto);
            return new Resp.NonDataResp(200,"OK");
        }catch (Exception e){
            return new Resp.NonDataResp(500,e.getMessage());
        }
    }
}

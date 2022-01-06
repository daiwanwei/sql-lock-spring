package com.example.sqllockspring.persistence.repository;


import com.example.sqllockspring.persistence.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface PetDao extends JpaRepository<Pet, Long> {

    //    read:在別的session中僅能read不能write
    @Lock(LockModeType.PESSIMISTIC_READ)

    //    write:在別的session中會等別人session結束後才執行
//    @Lock(LockModeType.PESSIMISTIC_WRITE)

//    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<Pet> findById(Long id);
}

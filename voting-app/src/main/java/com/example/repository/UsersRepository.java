package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.example.entity.Users;


import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "select * from usr_user where usr_username=?1 or usr_email=?2", nativeQuery = true)
    Optional<Users> findByUsernameAndEmail(String usr_username, String usr_email);

    


    
}
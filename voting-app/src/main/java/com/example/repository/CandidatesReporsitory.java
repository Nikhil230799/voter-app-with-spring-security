package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Candidates;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CandidatesReporsitory  extends JpaRepository<Candidates,Integer>{

    
}

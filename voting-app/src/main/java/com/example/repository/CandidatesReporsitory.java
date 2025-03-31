package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Candidates;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CandidatesReporsitory extends JpaRepository<Candidates, Integer> {

    @Query(value = "select * from cd_candidates ", nativeQuery = true)
    List<Candidates> findAllCandidates();

}

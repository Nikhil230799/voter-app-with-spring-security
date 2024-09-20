package com.example.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "cd_candidates")
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cd_id;
    @Column
    private String cd_name;
    @Column
    private int cd_votes;
    @OneToOne(mappedBy = "usr_votedto")
    private Users user;

}

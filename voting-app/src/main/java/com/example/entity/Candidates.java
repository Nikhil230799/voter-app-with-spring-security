package com.example.entity;

import java.util.List;

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

    @OneToMany(mappedBy = "usr_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Users> voters;

    public int getCd_id() {
        return cd_id;
    }

    public void setCd_id(int cd_id) {
        this.cd_id = cd_id;
    }

    public String getCd_name() {
        return cd_name;
    }

    public void setCd_name(String cd_name) {
        this.cd_name = cd_name;
    }

    public int getCd_votes() {
        return cd_votes;
    }

    public void setCd_votes(int cd_votes) {
        this.cd_votes = cd_votes;
    }

    public List<Users> getVoters() {
        return voters;
    }

    public void setVoters(List<Users> voters) {
        this.voters = voters;
    }

    @Override
    public String toString() {
        return "Candidates [cd_id=" + cd_id + ", cd_name=" + cd_name + ", cd_votes=" + cd_votes + ", voters=" + voters
                + "]";
    }
    
}

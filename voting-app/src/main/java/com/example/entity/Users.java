package com.example.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usr_user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usr_id;
    @Column (unique = true)
    @Size(min = 4, max = 12, message = "Username size must be between 4 to 12 characters")
    private String usr_username;
    @Column
    @Size(min = 8, max=20, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must be alphanumeric")
    private String usr_password;
    @Column(unique = true)
    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message = "Email is not valid")    
    private String usr_email;
    @Column
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$", message = "Phone number must be in valid format")
    @NotEmpty(message = "contact number cannot be empty")
    private String usr_phoneNo;
    @Column
    private boolean usr_voteStatus=false;
    @Column
    private boolean usr_userStatus=true;
    @Column
    private String usr_role="voter";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usr_votedto", referencedColumnName = "cd_id")
    private Candidates usr_votedto;

    
    @Override
    public String toString() {
        return "Users [usr_id=" + usr_id + ", usr_username=" + usr_username + ", usr_password=" + usr_password
                + ", usr_email=" + usr_email + ", usr_phoneNo=" + usr_phoneNo + ", usr_voteStatus=" + usr_voteStatus
                + ", usr_userStatus=" + usr_userStatus + ", usr_role=" + usr_role + ", usr_votedto=" + usr_votedto
                + "]";
    }
    
    

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsr_username() {
        return usr_username;
    }

    public void setUsr_username(String usr_username) {
        this.usr_username = usr_username;
    }

    public String getUsr_password() {
        return usr_password;
    }

    public void setUsr_password(String usr_password) {
        this.usr_password = usr_password;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_phoneNo() {
        return usr_phoneNo;
    }

    public void setUsr_phoneNo(String usr_phoneNo) {
        this.usr_phoneNo = usr_phoneNo;
    }

    public boolean isUsr_voteStatus() {
        return usr_voteStatus;
    }

    public void setUsr_voteStatus(boolean usr_voteStatus) {
        this.usr_voteStatus = usr_voteStatus;
    }

    public boolean isUsr_userStatus() {
        return usr_userStatus;
    }

    public void setUsr_userStatus(boolean usr_userStatus) {
        this.usr_userStatus = usr_userStatus;
    }

    public String getUsr_role() {
        return usr_role;
    }

    public void setUsr_role(String usr_role) {
        this.usr_role = usr_role;
    }

    public Candidates getUsr_votedto() {
        return usr_votedto;
    }

    public void setUsr_votedto(Candidates usr_votedto) {
        this.usr_votedto = usr_votedto;
    }

    


}

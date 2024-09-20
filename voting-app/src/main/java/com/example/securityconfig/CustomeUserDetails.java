package com.example.securityconfig;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.Users;

    

public class CustomeUserDetails implements UserDetails {

    Users user;

    public CustomeUserDetails(Users user)
    {
        user=this.user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        
    }

    @Override
    public String getPassword() {
       return user.getUsr_password();
    }

    @Override
    public String getUsername() {
        return user.getUsr_username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        
    }

    @Override
    public boolean isAccountNonLocked() {
  
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    
}
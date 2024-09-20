package com.example.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entity.Users;
import com.example.repository.UsersRepository;

public class CustomeUserService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.getUserByUsername(username);
        System.out.println(user+"hello");
        if (user == null) {
            throw new UsernameNotFoundException("User does not exists");
        }
        
        CustomeUserDetails customeUserDetails = new CustomeUserDetails(user);

        return customeUserDetails;
    }

}

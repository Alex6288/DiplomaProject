package com.example.diploma.service;

import com.example.diploma.entity.User;
import com.example.diploma.repas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = this.userRep.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException("Пользователь c логином " + login +" не найден"));

        return UserDetailImpl.build(user);
    }

}

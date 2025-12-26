package com.angelos.koinoxrhsta.impl.op;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.User;

@Service
public class MyUserDetailOp implements UserDetailsService {

    GenericPersisterFactory gpf;

    MyUserDetailOp(GenericPersisterFactory gpf) {
        this.gpf = gpf;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);

        List<User> foundUser;
        try {
            foundUser = gpf.create(User.class).findAll(Example.of(user));
        } catch (DataException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        if(foundUser.size()==1) {
            user = foundUser.get(0);
            System.err.println(user);
        } else {
            throw new UsernameNotFoundException("User with username: " + username + "was not found");
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.builder();
        return builder.username(user.getUsername()).password(user.getPassword()).roles(user.getPermissionGroup().name()).build();
    }

}

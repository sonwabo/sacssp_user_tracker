package com.technologies.xelo.security.service;

import com.technologies.xelo.security.model.Role;
import com.technologies.xelo.security.model.RoleRepository;
import com.technologies.xelo.security.model.User;
import com.technologies.xelo.security.model.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2022/06/23
 * @TIME 15:20
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements  UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public User saveUser(User user) {

        if(this.userRepository.findByUsername(user.getUsername()) != null){
           throw new IllegalArgumentException(String.format("Username %s is already taken", user.getUsername()));
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()) );
        return this.userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.userRepository.findByUsername(username);
        if(user ==null){
            throw new IllegalArgumentException("Cannot find user with the given username");
        }
        Role role = this.roleRepository.findByName(roleName);
        if(role != null) {
            user.getRoles().add(role);
        }
    }

    @Override
    public User getUser(String username) {
        return  this.userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return   this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info( "Loading use: {}", s  );
        User user = this.userRepository.findByUsername( s );
        if(user == null){
            throw  new UsernameNotFoundException(String.format("User with username %s does not exist", s));
        }
        Collection<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}

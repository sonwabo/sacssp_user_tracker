package com.technologies.xelo.security.service;

import com.technologies.xelo.security.model.Role;
import com.technologies.xelo.security.model.User;

import java.util.List;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2022/06/22
 * @TIME 22:51
 */
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}

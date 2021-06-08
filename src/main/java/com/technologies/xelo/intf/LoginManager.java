package com.technologies.xelo.intf;

import com.technologies.xelo.dto.LoginDTO;

public interface LoginManager {
    /**
     * Login Validator
     * @param login
     * @return
     */
    String login(LoginDTO login);
}

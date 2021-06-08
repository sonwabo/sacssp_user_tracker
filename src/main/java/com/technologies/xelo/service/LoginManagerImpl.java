package com.technologies.xelo.service;

import com.technologies.xelo.dto.LoginDTO;
import com.technologies.xelo.intf.LoginManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/27
 * @TIME 22:14
 */
@Service
public class LoginManagerImpl implements LoginManager {

    @Override
    public String login(LoginDTO login) {
        return UUID.randomUUID().toString();
    }
}

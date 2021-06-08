package com.technologies.xelo.resource;

import com.technologies.xelo.dto.LoginDTO;
import com.technologies.xelo.intf.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/27
 * @TIME 22:09
 */
@CrossOrigin
@RestController
public class AuthResource {

    private final LoginManager loginManager;
    @Autowired
    ApplicationContext applicationContext;

    public AuthResource(final LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @PostMapping(value ="/v1/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(loginManager.login(loginDTO));
    }

    @GetMapping(value = "/v1/getBeanNames")
    public ResponseEntity<String> getBeanNames(){
        return ResponseEntity.ok(Arrays.asList(this.applicationContext.getBeanDefinitionNames()).toString());
    }
}

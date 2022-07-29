package com.technologies.xelo.resource;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technologies.xelo.security.model.Role;
import com.technologies.xelo.security.model.User;
import com.technologies.xelo.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/27
 * @TIME 22:09
 */
//@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthResource {

    private final UserService userService;

    @GetMapping(value = "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith("Bearer ")) {

            try{
                String refresh_token = authHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier jwt = JWT.require(algorithm).build();

                DecodedJWT decodedJWT = jwt.verify(refresh_token);

                String username = decodedJWT.getSubject();
                User user =  this.userService.getUser( username );

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt( new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer( request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue( response.getOutputStream(), tokens );

            }catch (Exception e){
                e.printStackTrace();
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());

                Map<String,String> error = new HashMap<>();
                error.put("error_message",e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue( response.getOutputStream(), error );
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
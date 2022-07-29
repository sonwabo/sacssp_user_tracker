package com.technologies.xelo.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technologies.xelo.security.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2022/06/24
 * @TIME 07:55
 */

/**
 * This determines if the  user has aaccess to the application
 */
@Slf4j
public class CustomAuthorisationFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("******** Filter  Chain **********");
        log.info("Path: {}",request.getServletPath());
        log.info( request.toString());

        if(request.getServletPath().equalsIgnoreCase("/login") || request.getServletPath().equalsIgnoreCase("/token/refresh") ){
            filterChain.doFilter(request,response);
        }else {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(authHeader != null && authHeader.startsWith("Bearer ")) {

                try{
                    String token = authHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier jwt = JWT.require(algorithm).build();

                    DecodedJWT decodedJWT = jwt.verify(token);

                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                    Collection<SimpleGrantedAuthority> authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request,response);
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
                filterChain.doFilter(request,response);
            }
        }
    }
}

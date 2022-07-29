package com.technologies.xelo.security.util;

//import com.technologies.xelo.security.service.CustomUserDetailsService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/07/10
 * @TIME 09:32
 */
//@Component
public class JwtRequestFilter {// extends OncePerRequestFilter {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authorization = request.getHeader("Authorization");
//        String username = null;
//        String jwt = null;
//        if (StringUtils.isNotBlank(authorization) && StringUtils.startsWith(authorization, "Bearer ")){
//           jwt = StringUtils.substring(authorization, 7);
//           username = this.jwtUtil.extractUsername(jwt);
//        }
//
//        if(StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            if(this.jwtUtil.validateToken(jwt, userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//
//    }
}

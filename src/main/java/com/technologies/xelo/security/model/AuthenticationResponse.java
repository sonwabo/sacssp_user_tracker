package com.technologies.xelo.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/07/10
 * @TIME 08:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}

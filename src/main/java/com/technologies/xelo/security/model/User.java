package com.technologies.xelo.security.model;

import lombok.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/07/10
 * @TIME 10:12
 */
@ToString
@Entity
@Table(schema = "data_capture", name = "user", catalog = "sacssp_data_capture")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class User implements Serializable, Cloneable {
    private static Log logger = LogFactory.getLog(User.class);


    private static final long serialVersionUID = 1L;
    @Column(nullable = false, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(nullable = false, name = "username", unique = true)
    private String username;

    @Basic
    @Column(nullable = false, name = "password")
    private String password;

  //  @Column(nullable = true, name = "roles", columnDefinition = "TEXT")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

}

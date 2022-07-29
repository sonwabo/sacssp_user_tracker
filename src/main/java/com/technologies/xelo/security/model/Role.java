package com.technologies.xelo.security.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2022/06/22
 * @TIME 22:36
 */

@ToString
@Entity
@Table(schema = "data_capture", name = "role", catalog = "sacssp_data_capture")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Role {

    private static final long serialVersionUID = 1L;
    @Column(nullable = false, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(nullable = false, name = "name")
    private String name;
}

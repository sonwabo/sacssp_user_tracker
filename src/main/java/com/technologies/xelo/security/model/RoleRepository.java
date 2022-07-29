package com.technologies.xelo.security.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role,Long> {
    Role findByName(String name);
}

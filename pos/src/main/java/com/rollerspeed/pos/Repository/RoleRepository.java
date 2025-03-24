package com.rollerspeed.pos.Repository;

import com.rollerspeed.pos.Model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);//Encuentra Rol por nombre

    boolean existsByName(String name); //Verifica si existe rol por su nombre
    
}

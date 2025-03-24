package com.rollerspeed.pos.Services;

import com.rollerspeed.pos.Model.Role;


import java.util.List;
import java.util.Optional;


public interface RoleService {

    Role saveRole(Role role);

    Optional<Role> findRoleById(Long id);

    Optional<Role> findRoleByName(String name);

    List<Role> findAllRoles();

    boolean roleExists(String name);

    void deleteRoleById(Long id);

    long countRoles();
    
}

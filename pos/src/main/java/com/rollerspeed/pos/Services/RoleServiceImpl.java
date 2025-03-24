package com.rollerspeed.pos.Services;

import com.rollerspeed.pos.Model.Role;

import com.rollerspeed.pos.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        if (roleExists(role.getName())) {
            throw new IllegalArgumentException("El rol ya existe: " + role.getName());
        }
        return roleRepository.save(role);
    }
    
    @Override
    public Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public boolean roleExists(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public void deleteRoleById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new IllegalArgumentException("El rol con ID " + id + " no existe.");
        }
        roleRepository.deleteById(id);
    }

    @Override
    public long countRoles() {
        return roleRepository.count();
    }
}

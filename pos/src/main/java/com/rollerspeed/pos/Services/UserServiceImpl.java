package com.rollerspeed.pos.Services;

import com.rollerspeed.pos.Model.User;
import com.rollerspeed.pos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private static final List<String> ALLOWED_ROLES = List.of(
        "ADMINISTRADOR",
        "ALUMNO", 
        "INSTRUCTOR",
        "PUBLICO"
    );

    @Autowired
    public UserServiceImpl(UserRepository userRepository, 
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        // Validación de usuario existente
        if (usernameExists(user.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está registrado");
        }
        
        // Validación de rol permitido
        String userRole = user.getRolename().toUpperCase();
        if (!ALLOWED_ROLES.contains(userRole)) {
            throw new IllegalArgumentException("Rol no permitido: " + userRole);
        }
        
        // Encriptación y guardado
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRolename(userRole); // Asegurar formato mayúsculas

        System.out.println("Usuario guardado correctamente: " + user); //Guarda usuario
        
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority(user.getRolename()))
        );
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario con ID " + id + " no existe");
        }
        userRepository.deleteById(id);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }
}


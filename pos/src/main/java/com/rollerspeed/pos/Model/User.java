package com.rollerspeed.pos.Model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
@Table(name= "users")
@NoArgsConstructor
@AllArgsConstructor
@Transactional

public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  @NotBlank(message = "El nombre de usuario es obligatorio")
  private String username;

  @Column(nullable = false, length = 255)
  @NotBlank(message = "La contraseña es obligatoria")
  @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
  private String password;

  @Column(nullable = false, length = 50)
  @NotBlank(message = "El rol es obligatorio")
  private String rolename;

  // Getters y Setters
  public Long getId() { return id; }
  public String getUsername() { return username; }
  public String getPassword() { return password; }
  public String getRolename() { return rolename; }

  public void setId(Long id) { this.id = id; }
  public void setUsername(String username) { this.username = username; }
  public void setPassword(String password) { this.password = password; }
  public void setRolename(String rolename) { this.rolename = rolename; }
}






    


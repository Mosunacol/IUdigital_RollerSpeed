package com.rollerspeed.pos.Model;

import lombok.*;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 40)
    private String password;
    
}

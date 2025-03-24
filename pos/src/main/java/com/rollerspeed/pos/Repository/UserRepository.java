package com.rollerspeed.pos.Repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.rollerspeed.pos.Model.User;

@Repository

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}
       


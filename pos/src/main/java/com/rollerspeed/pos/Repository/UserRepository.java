package com.rollerspeed.pos.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.rollerspeed.pos.Model.UserModel;

@Repository

public interface UserRepository extends CrudRepository<UserModel,Long>{
       
}


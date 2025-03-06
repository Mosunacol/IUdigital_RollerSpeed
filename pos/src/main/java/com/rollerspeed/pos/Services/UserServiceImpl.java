package com.rollerspeed.pos.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.rollerspeed.pos.Model.UserModel;
import com.rollerspeed.pos.Repository.UserRepository;


@Service

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired

    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
        
    public List<UserModel> listar() {
        return (List<UserModel>) (userRepository.findAll());
    }
}

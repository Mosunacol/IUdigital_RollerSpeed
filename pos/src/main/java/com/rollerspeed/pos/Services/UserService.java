package com.rollerspeed.pos.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rollerspeed.pos.Model.UserModel;

@Service

public interface UserService {
    List<UserModel> listar();
    
}

package com.cmpt213.finalProject.SYNC.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpt213.finalProject.SYNC.models.UserModel;
import com.cmpt213.finalProject.SYNC.repository.UserRepository;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public UserModel registerUser(String login, String password, String email){
        if(login == null && password == null){
            return null;
        }
        else{
            if(userRepository.findByLogin(login).isPresent()){
                System.out.println("Duplicate User");
                return null;
            }

            UserModel user = new UserModel();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            return userRepository.save(user);
        }
    }

    public UserModel authentication(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}

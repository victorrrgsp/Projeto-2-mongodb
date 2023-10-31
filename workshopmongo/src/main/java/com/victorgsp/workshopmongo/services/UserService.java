package com.victorgsp.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorgsp.workshopmongo.domain.User;
import com.victorgsp.workshopmongo.repository.UserRepository;

@Service
public class UserService {

    // o spring vai procurar a definição desse objeto e vai instaciar o mesmo
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        // o metodo findAll vai retorna todos os objetos do user no banco
        return userRepository.findAll();
    }
}

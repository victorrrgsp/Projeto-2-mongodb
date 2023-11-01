package com.victorgsp.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorgsp.workshopmongo.domain.User;
import com.victorgsp.workshopmongo.repository.UserRepository;
import com.victorgsp.workshopmongo.services.exceptions.ObjectNotFoundExcetion;

@Service
public class UserService {

    // o spring vai procurar a definição desse objeto e vai instaciar o mesmo
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        // o metodo findAll vai retorna todos os objetos do user no banco
        return userRepository.findAll();
    }

    public User findById(String id){
        // o metodo findOne para o useraio para vc
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundExcetion(id + " Objeto não encontrado!"));
    }

    // public User findByNamUser(String nome) {

    //      Optional<User> user = userRepository.findById(id);
    //     if (user == null) {
    //         throw new ObjectNotFoundExcetion("Objeto não encontrado");
    //     }
    //     return user;
    // }




}

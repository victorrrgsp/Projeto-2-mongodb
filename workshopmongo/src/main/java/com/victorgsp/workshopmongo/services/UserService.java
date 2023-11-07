package com.victorgsp.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorgsp.workshopmongo.domain.User;
import com.victorgsp.workshopmongo.dto.UserDTO;
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
        // o orElseThrow é um metodo do Optional, onde ele tenta retornar o que estar dentro do Optional, caso não tenha nada, ele retornará uma exceção
        return user.orElseThrow(() -> new ObjectNotFoundExcetion(id + " Objeto não encontrado!"));
    }

    // public User findByNamUser(String obj) {
    //     User user = userRepository.(obj);
    //         if (user == null) {
    //         throw new ObjectNotFoundExcetion("Objeto não encontrado");
    //      }
    //      return user;
    // }

    public User insert(User obj){
        return userRepository.insert(obj);
    }

    public void delete (String id){
        // vai buscar o id, se mão existir esse id buscado, irá retornar uma exceção
        findById(id);
        userRepository.deleteById(id);
    }

    public User update (User obj){
        Optional<User> newObj = userRepository.findById(obj.getId());
        updateData(newObj, obj);
        return userRepository.save(newObj.orElseThrow());
    }

    private void updateData(Optional<User> newObj, User obj) {
        newObj.get().setName(obj.getName());
        newObj.get().setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}

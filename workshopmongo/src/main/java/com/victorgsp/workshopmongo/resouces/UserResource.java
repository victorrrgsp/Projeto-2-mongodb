package com.victorgsp.workshopmongo.resouces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgsp.workshopmongo.domain.User;
import com.victorgsp.workshopmongo.dto.UserDTO;
import com.victorgsp.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <List<UserDTO>> findAll(){
        List<User> list = userService.findAll();
        // aqui estar convertendo a lista do tipo User para o tipo UserDTO, e no final do comando retorna novamente para lista de User
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        // o metodo ok vau instaciar o ResponseEntity com o codigo de resposta http
        // o metodo body vai ser o corpo da memoria
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <UserDTO> findById(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }
}

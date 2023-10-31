package com.victorgsp.workshopmongo.resouces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgsp.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <List<User>> findAll(){
        User maria = new User("1", "Maria", "maria@gmail.com");
        User alex = new User("2", "Alex", "alex@gmail.com");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(maria,alex));
        // o metodo ok vau instaciar o ResponseEntity com o codigo de resposta http
        // o metodo body vai ser o corpo da memoria
        return ResponseEntity.ok().body(list);
    }


}

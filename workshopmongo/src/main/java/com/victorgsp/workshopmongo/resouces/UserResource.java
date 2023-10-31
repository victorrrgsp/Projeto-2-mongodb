package com.victorgsp.workshopmongo.resouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgsp.workshopmongo.domain.User;
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
    public ResponseEntity <List<User>> findAll(){
        List<User> list = userService.findAll();
        // o metodo ok vau instaciar o ResponseEntity com o codigo de resposta http
        // o metodo body vai ser o corpo da memoria
        return ResponseEntity.ok().body(list);
    }


}

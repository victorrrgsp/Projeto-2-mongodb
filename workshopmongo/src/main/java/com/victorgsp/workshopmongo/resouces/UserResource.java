package com.victorgsp.workshopmongo.resouces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victorgsp.workshopmongo.domain.Post;
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

    // @GetMapping(value = "/{name}")
    // /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
    //  *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    // */ 
    // public ResponseEntity <UserDTO> findByNamUser(@PathVariable String name){
    //     User obj = userService.findByNamUser(name);
    //     return ResponseEntity.ok().body(new UserDTO(obj));
    // }

    @PostMapping 
    // pro endpoint aceitar o obj ded UserDTO, necessita da anotetion @RequestBody
    public ResponseEntity <Void> insert(@RequestBody UserDTO objDto){
        User obj = userService.fromDTO(objDto);
        obj = userService.insert(obj);
        /* criação de um novo cabeçalho com a URl do novo recurso criado, isso é uma boa pratica 
         * o comando abaixo vai pegar o indereço do novo objeto que eu inseri
        */ 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        // o created retorna o codigo 201, que é um codigo Http quando você cria um novo recurso
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        // quando voce faz uma operação e ela não retorna nada, vai ser uma resposta de codigo 204 que é o noContent do ResponseEntity
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
        User obj = userService.fromDTO(objDto);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <List<Post>> findPosts(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}

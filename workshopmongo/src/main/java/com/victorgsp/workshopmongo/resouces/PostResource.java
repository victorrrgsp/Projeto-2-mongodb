package com.victorgsp.workshopmongo.resouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorgsp.workshopmongo.domain.Post;
import com.victorgsp.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService PostService;

    @GetMapping(value = "/{id}")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <Post> findById(@PathVariable String id){
        Post obj = PostService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}

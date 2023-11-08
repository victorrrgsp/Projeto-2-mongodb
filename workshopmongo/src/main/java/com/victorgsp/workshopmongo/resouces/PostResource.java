package com.victorgsp.workshopmongo.resouces;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victorgsp.workshopmongo.domain.Post;
import com.victorgsp.workshopmongo.resouces.util.URL;
import com.victorgsp.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <Post> findById(@PathVariable String id){
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        // isso decodifica o text para o usuário
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

     @GetMapping(value = "/fullsearch")
    /* o ResponseEntity ele vai encapsular toda uma estrutura necessaria 
     *para retornar respostas http com possiveis erros, cabeçalhos, entre outro
    */ 
    public ResponseEntity <List<Post>> fullsearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        // isso decodifica o text para o usuário
        text = URL.decodeParam(text);
        // se der algum problema na converção do minDate, o program irá pegar a data minima do sistema que é 01/jan/1970
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}

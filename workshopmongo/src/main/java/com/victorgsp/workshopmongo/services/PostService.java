package com.victorgsp.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorgsp.workshopmongo.domain.Post;
import com.victorgsp.workshopmongo.repository.PostRepository;
import com.victorgsp.workshopmongo.services.exceptions.ObjectNotFoundExcetion;

@Service
public class PostService {

    // o spring vai procurar a definição desse objeto e vai instaciar o mesmo
    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        // o metodo findOne para o Postaio para vc
        Optional<Post> Post  = postRepository.findById(id);
        // o orElseThrow é um metodo do Optional, onde ele tenta retornar o que estar dentro do Optional, caso não tenha nada, ele retornará uma exceção
        return Post.orElseThrow(() -> new ObjectNotFoundExcetion(id + " Objeto não encontrado!"));
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDAte, Date maxDate){
        // vai pegar o instante inicial dessa data onde irá somar 24 horas
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDAte, maxDate);
    }
}

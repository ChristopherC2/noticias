package com.example.newsapi.domain.repository;

import com.example.newsapi.domain.model.Noticia;
import com.example.newsapi.domain.response.request.NoticiaRequest;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NoticiaRepository {

    private final Map<Integer, Noticia> noticiaMap = new HashMap<>();

    public Noticia getById(int id){
        return noticiaMap.get(id);
    }

    public List<Noticia> getAll(){
        return new ArrayList<> (noticiaMap.values());
    }

    public Noticia save(NoticiaRequest noticiaRequest){

        List<Noticia> noticias = getAll();
        Optional<Integer> maxId = noticias.stream().map(Noticia::getId).max(Integer::compare);

        Noticia noticia = Noticia.builder()
                .id(maxId.orElse(0) + 1)
                .titulo(noticiaRequest.getTitulo()).build();

        noticiaMap.put(noticia.getId(), noticia);
        return noticia;
    }

    public void delete(int id){
        noticiaMap.remove(id);
    }

    public Noticia update(Noticia noticia){

        if(noticiaMap.containsKey(noticia.getId())){

            Noticia noticiaExistente = noticiaMap.get(noticia.getId());
            noticiaExistente.setTitulo(noticia.getTitulo());
            return noticiaExistente;
        }else{
            throw new RuntimeException("Elemento com ID" + noticia.getId() + " não encontrado");
        }
    }
}

package com.example.newsapi.domain.service;

import com.example.newsapi.config.exception.ContentException;
import com.example.newsapi.config.exception.NoticiaException;
import com.example.newsapi.domain.model.Noticia;
import com.example.newsapi.domain.repository.NoticiaRepository;
import com.example.newsapi.domain.response.NoticiaResponse;
import com.example.newsapi.domain.response.request.NoticiaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    final NoticiaRepository repository;

    @Override
    public NoticiaResponse getById(int id) {

        if(Objects.isNull(id))
            throw new NoticiaException("O id não pode ser nulo.");

        Noticia noticia = repository.getById(id);

        if(Objects.isNull(noticia))
            throw new NoticiaException("Nenhuma noticia encontrada para o id informado.");

        return this.domainToResponse(noticia);
    }

    @Override
    public List<NoticiaResponse> getAll() {

        List<Noticia> noticias = repository.getAll();

        if(Objects.isNull(noticias))
            throw new NoticiaException("Nenhuma noticia encontrada.");

        return this.domainToResponseList(noticias);
    }

    @Override
    public void delete(int id) {

        Noticia noticia = repository.getById(id);

        if(Objects.isNull(noticia))
            throw new NoticiaException("Nenhuma noticia encontrada.");

        repository.delete(id);
    }

    @Override
    public NoticiaResponse update(int id, NoticiaRequest noticiaRequest) {

        if(noticiaRequest.getTitulo().trim().isEmpty())
            throw new NoticiaException("Para realizar uma inserção a noticia deve ter um título valido.");

        Noticia noticia = repository.getById(id);

        if(Objects.isNull(noticia))
            throw new NoticiaException("Nenhuma noticia encontrada.");

        this.setFields(noticia, noticiaRequest);

        Noticia noticiaAtualizada = repository.update(noticia);
        return this.domainToResponse(noticiaAtualizada);
    }

    @Override
    public NoticiaResponse save(NoticiaRequest noticiaRequest) {

        if(noticiaRequest.getTitulo().trim().isEmpty())
            throw new NoticiaException("Para realizar uma inserção a noticia deve ter um título valido.");

        Noticia noticia = repository.save(noticiaRequest);

        if(Objects.isNull(noticia))
            throw new ContentException("Erro ao inserir a noticia.");

        return this.domainToResponse(noticia);
    }

    private NoticiaResponse domainToResponse(Noticia noticia){
        return NoticiaResponse.builder()
                .id(noticia.getId())
                .titulo(noticia.getTitulo()).build();
    }

    private List<NoticiaResponse> domainToResponseList(List<Noticia> noticias){

        List<NoticiaResponse> noticiaResponses = new ArrayList<>();

        for (Noticia noticia : noticias){

            NoticiaResponse noticiaResponse = NoticiaResponse.builder()
                    .id(noticia.getId())
                    .titulo(noticia.getTitulo()).build();

            noticiaResponses.add(noticiaResponse);
        }

        return noticiaResponses;
    }

    private void setFields(Noticia noticia, NoticiaRequest noticiaRequest){
        noticia.setTitulo(noticiaRequest.getTitulo());
    }
}

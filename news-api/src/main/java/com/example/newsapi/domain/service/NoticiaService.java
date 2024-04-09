package com.example.newsapi.domain.service;

import com.example.newsapi.domain.response.NoticiaResponse;
import com.example.newsapi.domain.response.request.NoticiaRequest;

import java.util.List;

public interface NoticiaService {

    NoticiaResponse getById(int id);

    List<NoticiaResponse> getAll();

    NoticiaResponse save(NoticiaRequest noticiaRequest);

    void delete(int id);

    NoticiaResponse update(int id, NoticiaRequest noticiaRequest);
}

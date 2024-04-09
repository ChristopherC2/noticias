package com.example.newsapi.domain.service;

import com.example.newsapi.config.exception.NoticiaException;
import com.example.newsapi.domain.model.Noticia;
import com.example.newsapi.domain.repository.NoticiaRepository;
import com.example.newsapi.domain.response.NoticiaResponse;
import com.example.newsapi.domain.response.request.NoticiaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoticiaServiceImplTest {

    @Mock
    private NoticiaRepository repository;

    @InjectMocks
    private NoticiaServiceImpl noticiaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByIdSuccess() {

        int id = 1;
        Noticia noticiaMock = new Noticia(id, "Titulo");
        when(repository.getById(id)).thenReturn(noticiaMock);

        NoticiaResponse response = noticiaService.getById(id);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Titulo", response.getTitulo());
    }

    @Test
    public void testGetByIdNotFound() {

        int id = 1;
        when(repository.getById(id)).thenReturn(null);

        assertThrows(NoticiaException.class, () -> {
            noticiaService.getById(id);
        });
    }

    @Test
    public void testGetAllSuccess() {

        List<Noticia> noticiasMock = new ArrayList<>();
        noticiasMock.add(new Noticia(1, "Titulo 1"));
        noticiasMock.add(new Noticia(2, "Titulo 2"));
        when(repository.getAll()).thenReturn(noticiasMock);

        List<NoticiaResponse> responseList = noticiaService.getAll();

        assertNotNull(responseList);
        assertEquals(2, responseList.size());
        assertEquals("Titulo 1", responseList.get(0).getTitulo());
        assertEquals("Titulo 2", responseList.get(1).getTitulo());
    }

    @Test
    public void testDeleteSuccess() {

        int id = 1;
        Noticia noticiaMock = new Noticia(id, "Titulo");
        when(repository.getById(id)).thenReturn(noticiaMock);

        noticiaService.delete(id);

        verify(repository, times(1)).delete(id);
    }

    @Test
    public void testDeleteNotFound() {

        int id = 1;
        when(repository.getById(id)).thenReturn(null);

        assertThrows(NoticiaException.class, () -> {
            noticiaService.delete(id);
        });
    }

    @Test
    public void testUpdateSuccess() {

        int id = 1;
        NoticiaRequest request = new NoticiaRequest("Up Titulo");
        Noticia noticiaMock = new Noticia(id, "Titulo");
        when(repository.getById(id)).thenReturn(noticiaMock);
        when(repository.update(noticiaMock)).thenReturn(noticiaMock);

        NoticiaResponse response = noticiaService.update(id, request);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Up Titulo", response.getTitulo());
    }

    @Test
    public void testUpdateNotFound() {

        int id = 1;
        NoticiaRequest request = new NoticiaRequest("Up Titulo");
        when(repository.getById(id)).thenReturn(null);

        assertThrows(NoticiaException.class, () -> {
            noticiaService.update(id, request);
        });
    }

    @Test
    public void testSaveSuccess() {

        NoticiaRequest request = new NoticiaRequest("Titulo");
        Noticia noticiaMock = new Noticia(1, "Titulo");
        when(repository.save(request)).thenReturn(noticiaMock);

        NoticiaResponse response = noticiaService.save(request);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("Titulo", response.getTitulo());
    }

    @Test
    public void testSaveInvalidRequest() {

        assertThrows(NoticiaException.class, () -> {
            noticiaService.save(new NoticiaRequest(""));
        });
    }

}
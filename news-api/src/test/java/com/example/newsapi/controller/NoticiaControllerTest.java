package com.example.newsapi.controller;

import com.example.newsapi.domain.response.NoticiaResponse;
import com.example.newsapi.domain.response.request.NoticiaRequest;
import com.example.newsapi.domain.service.NoticiaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NoticiaControllerTest {

    @Mock
    private NoticiaService noticiaService;

    @InjectMocks
    private NoticiaController noticiaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() {

        int id = 1;
        NoticiaResponse noticiaResponseMock = new NoticiaResponse(id, "Test Title");
        when(noticiaService.getById(id)).thenReturn(noticiaResponseMock);

        ResponseEntity<NoticiaResponse> responseEntity = noticiaController.getById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(noticiaResponseMock, responseEntity.getBody());
    }

    @Test
    public void testGetAll() {

        List<NoticiaResponse> noticiasMock = Arrays.asList(
                new NoticiaResponse(1, "Title 1"),
                new NoticiaResponse(2, "Title 2")
        );
        when(noticiaService.getAll()).thenReturn(noticiasMock);
        ResponseEntity<List<NoticiaResponse>> responseEntity = noticiaController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(noticiasMock, responseEntity.getBody());
    }

    @Test
    public void testSave() {

        NoticiaRequest noticiaRequest = new NoticiaRequest("New Title");
        NoticiaResponse noticiaResponseMock = new NoticiaResponse(1, "New Title");
        when(noticiaService.save(noticiaRequest)).thenReturn(noticiaResponseMock);

        ResponseEntity<NoticiaResponse> responseEntity = noticiaController.save(noticiaRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(noticiaResponseMock, responseEntity.getBody());
    }

    @Test
    public void testDelete() {

        ResponseEntity<Void> responseEntity = noticiaController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(noticiaService, times(1)).delete(1);
    }

    @Test
    public void testUpdate() {

        NoticiaRequest noticiaRequest = new NoticiaRequest("Updated Title");
        ResponseEntity<Void> responseEntity = noticiaController.update(1, noticiaRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(noticiaService, times(1)).update(1, noticiaRequest);
    }

}
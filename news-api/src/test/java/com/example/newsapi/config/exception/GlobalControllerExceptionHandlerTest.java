package com.example.newsapi.config.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalControllerExceptionHandlerTest {

    private final GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();

    @Test
    public void testHandleNoticiaException() {

        String errorMessage = "Noticia não encontrada.";
        RuntimeException runtimeException = new NoticiaException(errorMessage);

        ResponseEntity<String> responseEntity = exceptionHandler.handleNoticiaException(runtimeException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    public void testHandleContentException() {

        String errorMessage = "Conteúdo não encontrado.";
        RuntimeException runtimeException = new ContentException(errorMessage);

        ResponseEntity<String> responseEntity = exceptionHandler.handleContentException(runtimeException);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }
}
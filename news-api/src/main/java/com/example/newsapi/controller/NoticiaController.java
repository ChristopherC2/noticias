package com.example.newsapi.controller;

import com.example.newsapi.domain.response.NoticiaResponse;
import com.example.newsapi.domain.response.request.NoticiaRequest;
import com.example.newsapi.domain.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticia")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaService noticiaService;

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<NoticiaResponse> getById(@PathVariable("id") int id ){
        return ResponseEntity.status(HttpStatus.OK).body(noticiaService.getById(id));
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<NoticiaResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(noticiaService.getAll());
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoticiaResponse> save(@RequestBody NoticiaRequest noticiaRequest){
        NoticiaResponse noticiaResponse = noticiaService.save(noticiaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(noticiaResponse);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        noticiaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") int id,
                                       @RequestBody NoticiaRequest noticiaRequest){
        noticiaService.update(id, noticiaRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package com.example.newsapi.domain.response.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaRequest {
    private String titulo;
}

package com.urlshortener.controller;

import static com.urlshortener.util.Constants.GET_BY_ID_RETURN;
import static com.urlshortener.util.Constants.SAVE_RETURN;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.model.Url;
import com.urlshortener.service.UrlShortenerService;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveUrl(String originalUrl) {
        Url url = urlShortenerService.save(originalUrl);

        return ResponseEntity.ok(String.format(SAVE_RETURN, url.getId()));
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<String> getById(String id) {
        Optional<Url> url = urlShortenerService.findById(id);

        return url.map(value -> ResponseEntity.ok(String.format(GET_BY_ID_RETURN, value.getOriginal())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

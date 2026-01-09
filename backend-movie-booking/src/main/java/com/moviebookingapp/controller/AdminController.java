package com.moviebookingapp.controller;

import org.springframework.web.bind.annotation.*;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.service.MovieService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final MovieService service;

    public AdminController(MovieService service){
        this.service = service;
    }

    @PostMapping("/add")
    public Movie add(@RequestBody Movie movie){
        return service.save(movie);
    }
}

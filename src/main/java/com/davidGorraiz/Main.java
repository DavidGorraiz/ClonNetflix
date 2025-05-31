package com.davidGorraiz;

import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.GenreService;
import com.davidGorraiz.service.UserService;

public class Main {
    public static void main(String[] args) {
        GenreService genreService = new GenreService();
        UserService userService = new UserService();
        ContentService contentService = new ContentService();

        userService.findAll();
        genreService.findAll();
        contentService.findAll();
    }
}
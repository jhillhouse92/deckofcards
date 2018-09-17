package com.appian.deckofcards.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeckController {

    @RequestMapping("/api/v1/deck")
    public String index() {
        return "The Deck of Cards REST API is coming soon!";
    }
}

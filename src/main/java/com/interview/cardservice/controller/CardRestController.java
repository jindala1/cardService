package com.interview.cardservice.controller;

import java.util.List;

import com.interview.cardservice.service.CardService;
import com.interview.cardservice.model.CardDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/api")
public class CardRestController {

    @Autowired
    private CardService cardService;

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/listCards")
    public ResponseEntity<List<CardDetails>> listAllCards() {
        List<CardDetails> creditCards = cardService.findAll();
        return new ResponseEntity<>(creditCards, HttpStatus.OK);
    }

    @PostMapping("/addCard")
    public ResponseEntity<CardDetails> addCard(@RequestBody CardDetails creditCard) {
        cardService.save(creditCard);
        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);
    }
}


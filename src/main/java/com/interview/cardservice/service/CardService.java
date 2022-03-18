package com.interview.cardservice.service;

import com.interview.cardservice.exception.NoRecordFoundException;
import com.interview.cardservice.repository.CardRepository;
import com.interview.cardservice.validation.CardValidation;
import com.interview.cardservice.exception.ValidationFailureException;
import com.interview.cardservice.model.CardDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private CardValidation cardValidation = new CardValidation();

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Fetches List of all availabl ecards and throw  404 if list is empty
     * @return LIst of Credit Cards
     */
    public List<CardDetails> findAll() {
        List<CardDetails> listOfCards = cardRepository.findAll();
        if(listOfCards == null || listOfCards.isEmpty()){
            throw new NoRecordFoundException("No Cards Available" );
        }
        return listOfCards;
    }

    /**
     * Adds a valid card
     * @param creditCard
     * @throws Throwable
     */
    public void save(CardDetails creditCard)  {
        if(!cardValidation.validateCardNumber(creditCard.getCardNumber())){
            throw new ValidationFailureException("Incorrect Card Number");
        }
        if(!cardValidation.validateCardLimit(creditCard.getCardLimit())){
            throw new ValidationFailureException("Invalid Card Limit");
        }
        cardRepository.save(creditCard);
    }
}

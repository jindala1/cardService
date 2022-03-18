package com.interview.cardservice.service;

import com.interview.cardservice.exception.NoRecordFoundException;
import com.interview.cardservice.exception.ValidationFailureException;
import com.interview.cardservice.model.CardDetails;
import com.interview.cardservice.repository.CardRepository;
import com.interview.cardservice.validation.CardValidation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    CardRepository creditcardRepository;

    @Mock
    CardValidation cardValidation;

    @InjectMocks
    CardService cardService;

    List<CardDetails> creditCardList = new ArrayList<>();
    List<CardDetails> creditCardLisEmpty = new ArrayList<>();
    CardDetails cardDetailsA;
    CardDetails cardDetailsB;
    CardDetails cardDetailsC;
    CardDetails cardDetailsD;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        cardDetailsA = new CardDetails("41111111111111111","validCardA", 500.00);
        cardDetailsB = new CardDetails("20202020","validCardB", 500.00);
        cardDetailsC = new CardDetails("444BB56","validCardC", -900.00);
        cardDetailsD = new CardDetails("411111111111111111111111111111111111111111","validCardA", 500.00);
        creditCardList = new ArrayList<>();
        creditCardList.add(cardDetailsA);
        creditCardList.add(cardDetailsB);
        creditCardList.add(cardDetailsC);
    }

    @Test
    public void testValidfindAll(){
        Mockito.when(creditcardRepository.findAll()).thenReturn(creditCardList);
        List<CardDetails> creditCard = cardService.findAll();
        assertFalse(creditCard.isEmpty());
        assertEquals(3,creditCardList.size());
    }



    @Test
    void testInValidfindAll() {
        Mockito.when(creditcardRepository.findAll()).thenReturn(creditCardLisEmpty);
        NoRecordFoundException thrown = assertThrows(
                NoRecordFoundException.class,
                () -> cardService.findAll(),
                "No Cards Available"
        );
    }

    @Test
    public void testValidSave(){
        Mockito.when(cardValidation.validateCardNumber("41111111111111111")).thenReturn(true);
        Mockito.when(cardValidation.validateCardLimit(500.00)).thenReturn(true);
        cardService.save(cardDetailsA);
        Mockito.verify(creditcardRepository, Mockito.times(1)).save(eq(cardDetailsA));
    }

    @Test
    void testInValidCardNumSave() {
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> cardService.save(cardDetailsB),
                "Incorrect Card Number"
        );
    }

    @Test
    void testInValidCardLimitSave() {
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> cardService.save(cardDetailsB),
                "Invalid Card Limit"
        );
    }

    @Test
    void testCardOnlyNumericSave() {
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> cardService.save(cardDetailsD),
                "Invalid Card Number"
        );
    }
}

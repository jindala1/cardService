package com.interview.cardservice.validation;

import com.interview.cardservice.validation.CardValidation;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class CardValidationTest {

    CardValidation cardValidator = new CardValidation();
    @Test
    public void testCardNumberNull(){
        String cardNumberNull = null;
        assertFalse(cardValidator.validateCardNumber(cardNumberNull));
    }

    @Test
    public void testCardNumberEmpty(){
        CardValidation cardValidator = new CardValidation();
        String cardNumberEMpty = "";
        assertFalse(cardValidator.validateCardNumber(cardNumberEMpty));
    }

    @Test
    public void testInvalidCard(){
        CardValidation cardValidator = new CardValidation();
        String cardNumberEMpty = "56khkj";
        assertFalse(cardValidator.validateCardNumber(cardNumberEMpty));
    }

    @Test
    public void testValiCard(){
        CardValidation cardValidator = new CardValidation();
        String cardNumberEMpty = "4111111111111111";
        assertTrue(cardValidator.validateCardNumber(cardNumberEMpty));
    }

    @Test
    public void testValiCardLimit(){
        CardValidation cardValidator = new CardValidation();
        Double cardLimit =  500.00;
        assertTrue(cardValidator.validateCardLimit(cardLimit));
    }

    @Test
    public void testInValiCardLimit(){
        CardValidation cardValidator = new CardValidation();
        Double cardLimit =  -89.00;
        assertFalse(cardValidator.validateCardLimit(cardLimit));
    }

    @Test
    public void testNullValiCardLimit(){
        CardValidation cardValidator = new CardValidation();
        Double cardLimit = null;
        assertFalse(cardValidator.validateCardLimit(cardLimit));
    }
    @Test
    public void testOnlyNumercCardNumber(){
        CardValidation cardValidator = new CardValidation();
        String cardNumber = "411FJGJ587687";
        assertFalse(cardValidator.validateCardNumber(cardNumber));
    }

    @Test
    public void testLOngerCardNumber(){
        CardValidation cardValidator = new CardValidation();
        String cardNumber = "4111111111111111111111111";
        assertFalse(cardValidator.validateCardNumber(cardNumber));
    }

}

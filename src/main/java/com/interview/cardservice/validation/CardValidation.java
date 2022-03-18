package com.interview.cardservice.validation;

import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class CardValidation {

    /**
     * Does LUHN Check on Card Number
     * @param cardNumber
     * @return
     */
    public boolean validateCardNumber(String cardNumber) {
        if(cardNumber == null || cardNumber.length() < 1 || cardNumber.length() > 19 || !cardNumber.matches("[0-9]+"))
            return false;
        int cardLength = cardNumber.length();
        int sumOfDigits = 0;
        boolean isAlternate = false;
        for (int i = cardLength - 1; i >= 0; i--) {
            int d = Character.getNumericValue(cardNumber.charAt(i));
            if (isAlternate == true)
                d = d * 2;
                sumOfDigits += d / 10;
                sumOfDigits += d % 10;
                isAlternate = !isAlternate;
        }
        return (sumOfDigits % 10 == 0);
    }

    /**
     * Does check on card Limit
     * @param cardLimit
     * @return
     */
    public boolean validateCardLimit(Double cardLimit) {
        if(cardLimit == null || cardLimit < 0) {
            return false;
        } else {
            return true;
        }
    }
}


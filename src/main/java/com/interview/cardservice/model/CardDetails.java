package com.interview.cardservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "card_details")
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_limit")
    private Double cardLimit = 0.0;

    public CardDetails() {

    }

    public CardDetails(String cardNumber, String cardName, Double cardLimit) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cardLimit = cardLimit;
    }

    public long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Double cardLimit) {
        this.cardLimit = cardLimit;
    }

    @Override
    public String toString() {
        return "CreditCard [id=" + id + ", cardNumber=" + cardNumber + ", cardName=" + cardName + ", cardLimit=" + cardLimit + "]";
    }

}
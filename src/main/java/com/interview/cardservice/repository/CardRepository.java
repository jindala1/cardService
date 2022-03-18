package com.interview.cardservice.repository;

import com.interview.cardservice.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardRepository extends JpaRepository<CardDetails, Long> {

}
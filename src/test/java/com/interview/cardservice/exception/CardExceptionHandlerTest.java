package com.interview.cardservice.exception;

import com.interview.cardservice.exception.handler.CardExceptionHandler;
import com.interview.cardservice.validation.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.Assert.*;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class CardExceptionHandlerTest {

    private CardExceptionHandler cardExceptionHandler;

    @BeforeEach
    public void setUp() {
        cardExceptionHandler = new CardExceptionHandler();
    }
    @Test
    public void testHandleNoRecordFoudException() {
        NoRecordFoundException noRecordFoundException = new NoRecordFoundException("No Cards Available");

        ResponseEntity<ErrorResponse> responseEntity = cardExceptionHandler
                .noRecordFoundExceptionHandler(noRecordFoundException);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);

        assertEquals(responseEntity.getBody().getErrorMessage(),"No Cards Available");
        assertEquals(responseEntity.getBody().getErrorCode(), "API_02_EMPTY");
    }

    @Test
    public void testHandlValidateFailureException() {
        ValidationFailureException validationFailureException = new ValidationFailureException("Incorrect Card Number");

        ResponseEntity<ErrorResponse> responseEntity = cardExceptionHandler
                .validationFailureExceptionHandler(validationFailureException);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        assertEquals(responseEntity.getBody().getErrorMessage(),"Incorrect Card Number");
        assertEquals(responseEntity.getBody().getErrorCode(), "API_01_LUHN");
    }

    @Test
    public void testHandlGlobleExcpetionHandler() {
        Exception exception = mock(Exception.class);

        ResponseEntity<ErrorResponse> responseEntity = cardExceptionHandler
                .globalExceptionHandler(exception);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(responseEntity.getBody().getErrorCode(), "API_03_ERROR");
    }

}

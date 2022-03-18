package com.interview.cardservice.controller;

import com.interview.cardservice.exception.NoRecordFoundException;
import com.interview.cardservice.model.CardDetails;
import com.interview.cardservice.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = CardRestController.class)
public class CardRestControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    List<CardDetails> creditCardList;

    @Test
    @WithMockUser(value = "interview")
    public void listAllCards() throws Exception {
        Mockito.when(
                cardService.findAll()).thenReturn(creditCardList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/v1/api/listCards").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "interview")
    public void listAllCardsEmpty() throws Exception {
        Mockito.when(
                cardService.findAll()).thenThrow(NoRecordFoundException.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/v1/api/listCards").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @WithMockUser(value = "interview")
    public void saveCardValid() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/v1/api/addCard").contentType(MediaType.APPLICATION_JSON).content(
                       "    {\n" +
                               "        \"cardnumber\": \"33\",\n" +
                               "        \"cardname\": \"Dangote\",\n" +
                               "        \"cardlimit\": \"500\"\n" +
                               "    }")).andReturn().getResponse();
        assertEquals(response.getStatus(),(HttpStatus.CREATED.value()));
    }

}
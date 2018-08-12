package com.traderev.carauctionsystem.controller;

import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.service.BidService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * JUnit testing of Bid Controller class.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-12
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BidController.class)
public class BidControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidService bidService;

    String bidJson = "{\"carId\":\"1\",\"userId\":\"2\",\"bidAmount\":\"1000\"}";
    List<Bid> mockBidList = new ArrayList<>();
    Bid mockBidResponse = new Bid(1L, 2L, new BigDecimal(1000) );

    @Test
    public void addOrUpdateBid() throws Exception {
        Mockito.when(
                bidService.saveBid(Mockito.any(Bid.class))).thenReturn(mockBidResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bids").accept(
                MediaType.APPLICATION_JSON).content(bidJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void getBids() throws Exception {
        mockBidList.add(mockBidResponse);
        Mockito.when(
                bidService.getAllBids()).thenReturn(mockBidList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bids");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "[{carId:1,userId:2,bidAmount:1000}]";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
package com.traderev.carauctionsystem.controller;

import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.Car;
import com.traderev.carauctionsystem.service.BidService;
import com.traderev.carauctionsystem.service.CarService;
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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit testing of Car Controller class.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-12
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private BidService bidService;

    Car mockCarResponse = new Car("MAF", "Rouge", Car.Type.SUV, Car.Color.BLACK, new Date(), "Nissan", new BigDecimal(1001));
    String carJson = "{\"modelNumber\":\"MAF\",\"name\":\"Rouge\",\"type\":\"SUV\",\"color\":\"BLACK\", \"buildYear\":\"2018-02-11\", \"manufacturerName\":\"Nissan\", \"minimumBidAmount\":\"1001\"}";

    List<Car> mockCarList = new ArrayList<>();
    List<Bid> mockBidList = new ArrayList<>();

    Bid mockBidResponse = new Bid(1L, 2L, new BigDecimal(1000) );


    @Test
    public void addCar() throws Exception {
        Mockito.when(
                carService.saveCar(Mockito.any(Car.class))).thenReturn(mockCarResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cars").accept(
                MediaType.APPLICATION_JSON).content(carJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //String expected = "{modelNumber:MAF,name:Rouge,type:SUV,color:BLACK, buildYear:2018-02-11, manufacturerName:Nissan, minimumBidAmount:1001}";
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    public void updateCar() throws Exception{
        Mockito.when(
                carService.updateCar(Mockito.any(Car.class), Mockito.anyLong())).thenReturn(mockCarResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cars/1").accept(
                MediaType.APPLICATION_JSON).content(carJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "{modelNumber:MAF,name:Rouge,type:SUV,color:BLACK, manufacturerName:Nissan, minimumBidAmount:1001}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getCars() throws Exception{
        mockCarList.add(mockCarResponse);
        Mockito.when(
                carService.getAllCars()).thenReturn(mockCarList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cars");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "[{modelNumber:MAF,name:Rouge,type:SUV,color:BLACK, manufacturerName:Nissan, minimumBidAmount:1001}]";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void findCarById() throws Exception {
        Mockito.when(
                carService.getCarById(Mockito.anyLong())).thenReturn(mockCarResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cars/1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "{modelNumber:MAF,name:Rouge,type:SUV,color:BLACK, manufacturerName:Nissan, minimumBidAmount:1001}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void deleteCarById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cars/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void findCarBidHistory() throws Exception {
        mockBidList.add(mockBidResponse);
        Mockito.when(
                bidService.findCarBids(Mockito.anyLong())).thenReturn(mockBidList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cars/1/bids");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "[{carId:1,userId:2,bidAmount:1000}]";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void findCarWinningBid() throws Exception {
        Mockito.when(
                bidService.findCarWinningBid(Mockito.anyLong())).thenReturn(mockBidResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cars/1/winningBid");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "{carId:1,userId:2,bidAmount:1000}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
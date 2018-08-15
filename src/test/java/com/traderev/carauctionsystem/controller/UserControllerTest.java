package com.traderev.carauctionsystem.controller;

import com.traderev.carauctionsystem.model.Bid;
import com.traderev.carauctionsystem.model.User;
import com.traderev.carauctionsystem.service.BidService;
import com.traderev.carauctionsystem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * JUnit testing of User Controller class.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-11
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private BidService bidService;

    User mockUserResponse = new User(1L, "shina", "abc@gmail.com", "abc123", false);
    String userJson = "{\"userId\":\"1\",\"name\":\"shina\",\"emailId\":\"abc@gmail.com\",\"password\":\"abc123\", \"" +
            "adminUser\":false}";
    Bid mocKBidResponse = new Bid(1L, 2L, new BigDecimal(1000));

    List<User> mockUserList = new ArrayList<>();
    List<Bid> mockBidList = new ArrayList<>();

    @Test
    public void addUser() throws Exception {
        Mockito.when(
                userService.saveUser(Mockito.any(User.class))).thenReturn(mockUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(
                MediaType.APPLICATION_JSON).content(userJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "{userId:1,name:shina,emailId:abc@gmail.com,password:abc123, adminUser:false}";
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void updateUser() throws Exception{
        Mockito.when(
                userService.updateUser(Mockito.any(User.class), Mockito.anyLong())).thenReturn(mockUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/1").accept(
                MediaType.APPLICATION_JSON).content(userJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "{userId:1,name:shina,emailId:abc@gmail.com,password:abc123, adminUser:false}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getUsers() throws Exception {
        mockUserList.add(mockUserResponse);
        Mockito.when(
                userService.findAllUsers()).thenReturn(mockUserList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "[{userId:1,name:shina,emailId:abc@gmail.com,password:abc123, adminUser:false}]";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void findUserById() throws Exception{
        Mockito.when(
                userService.findUserById( Mockito.anyLong())).thenReturn(mockUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "{userId:1,name:shina,emailId:abc@gmail.com,password:abc123, adminUser:false}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void deleteUserById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void findUserBids() throws Exception{
        mockBidList.add(mocKBidResponse);
        Mockito.when(
                bidService.findUserBids( Mockito.anyLong())).thenReturn(mockBidList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/2/bids");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "[{carId:1,userId:2,bidAmount:1000}]";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void findUserBidOnCar() throws Exception{
        Mockito.when(
                bidService.findUserBidOnCar( Mockito.anyLong(), Mockito.anyLong())).thenReturn(mocKBidResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/2/bid?carId=1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expected = "{carId:1,userId:2, bidAmount:1000}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
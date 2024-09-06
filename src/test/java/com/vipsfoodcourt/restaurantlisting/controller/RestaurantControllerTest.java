package com.vipsfoodcourt.restaurantlisting.controller;

import com.vipsfoodcourt.restaurantlisting.dto.RestaurantDto;
import com.vipsfoodcourt.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.AssertionErrors;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {



    @InjectMocks
    RestaurantController restaurantController;


    @Mock
    RestaurantService restaurantService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testfetchAllRestaurants(){

    // Mock the service behaviour

        List<RestaurantDto> mockRestaurants = Arrays.asList(

                new RestaurantDto(1,"Restaurant 1","Address 1","City 1","Desc 1"),
                new RestaurantDto(2,"Restaurant 2","Address 2","City 2","Desc 2")
        );
        
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);


        //call the controller method
        ResponseEntity<List<RestaurantDto>> response = restaurantController.fetchAllRestaurants();


        //verify the response
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockRestaurants,response.getBody());

        // verify that the service method was called
        verify(restaurantService,times(1)).findAllRestaurants();


    }


    @Test
    public void testSaveRestaurant(){


        //create a mock resturant to be saved
        RestaurantDto mockRestaurant = new RestaurantDto(1,"Restaurent 1","Address 1","City 1","Describ 1");

        //mock the service behaviour
        when(restaurantService.addRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);

        //call the controller method
        ResponseEntity<RestaurantDto> response = restaurantController.saveRestaurant(mockRestaurant);

        //verify the response
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(mockRestaurant,response.getBody());


        //verify the service method to be called
        verify(restaurantService,times(1)).addRestaurantInDB(mockRestaurant);


    }

    @Test
    public void testFindRestaurantById(){
        //create a mock resturant ID
        Integer mockRestaurantID =1;

        //create a mock resturant to be returned by the service
        RestaurantDto mockRestaurant = new RestaurantDto(1,"Restaurent 1","Address 1","City 1","Describ 1");


        //Mock the service behavoiur
        when(restaurantService.findRestaurantById(mockRestaurantID)).thenReturn(new ResponseEntity<>(mockRestaurant,HttpStatus.OK));

        // call the controller method
        ResponseEntity<RestaurantDto> response = restaurantController.findRestaurantById(mockRestaurantID);

        // verify the response
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockRestaurant,response.getBody());

        //verify the service was called
        verify(restaurantService,times(1)).findRestaurantById(mockRestaurantID);


    }

}

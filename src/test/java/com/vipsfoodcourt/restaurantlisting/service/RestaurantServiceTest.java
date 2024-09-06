package com.vipsfoodcourt.restaurantlisting.service;

import com.vipsfoodcourt.restaurantlisting.dto.RestaurantDto;
import com.vipsfoodcourt.restaurantlisting.entity.Restaurant;
import com.vipsfoodcourt.restaurantlisting.mapper.RestaurantMapper;
import com.vipsfoodcourt.restaurantlisting.repo.RestaurantRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepo restaurantRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testFindAllRestaurants() {

        // create mock restaurant
        List<Restaurant> mockRestaurants = Arrays.asList(new Restaurant(1, "Restaurent 1", "Address 1", "City 1", "Describ 1"),
                new Restaurant(2, "Restaurent 2", "Address 2", "City 2", "Describ 2"));


        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

        //call service method
        List<RestaurantDto> restaurantDTOList = restaurantService.findAllRestaurants();


        //verify the result
        assertEquals(restaurantDTOList.size(), mockRestaurants.size());
        for (int i = 0; i < restaurantDTOList.size(); i++) {

            RestaurantDto expectedDto = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(mockRestaurants.get(i));
            assertEquals(restaurantDTOList.get(i), expectedDto);

        }


        //verify that the repository method was called
        verify(restaurantRepo, times(1)).findAll();


    }


    @Test
    public void testAddRestaurantInDB() {

        //create a restuaruant mock
        RestaurantDto mockRestaurantDto = new RestaurantDto(1, "Restaurent 1", "Address 1", "City 1", "Describ 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDto);

        when(restaurantRepo.save(mockRestaurant)).thenReturn(mockRestaurant);

        RestaurantDto savedRestaurantDto = restaurantService.addRestaurantInDB(mockRestaurantDto);

        assertEquals(savedRestaurantDto, mockRestaurantDto);

        verify(restaurantRepo,times(1)).save(mockRestaurant);


    }

    @Test
    public void testFindRestaurantById_ExistingID(){


        Integer mockRestaurantID = 1;

        Restaurant mockRestaurant = new Restaurant(1, "Restaurent 1", "Address 1", "City 1", "Describ 1");

        when(restaurantRepo.findById(mockRestaurantID)).thenReturn(Optional.of(mockRestaurant));


        ResponseEntity<RestaurantDto> response = restaurantService.findRestaurantById(mockRestaurantID);

        assertEquals(mockRestaurant.getId(),response.getBody().getId());
        assertEquals(HttpStatus.OK,response.getStatusCode());

        verify(restaurantRepo,times(1)).findById(mockRestaurantID);





    }


    @Test
    public void testFindRestaurantById_NonExistingID(){

        Integer mockRestaurantID = 1;

        when( restaurantRepo.findById(mockRestaurantID)).thenReturn(Optional.empty());

        ResponseEntity<RestaurantDto> response = restaurantService.findRestaurantById(mockRestaurantID);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(null,response.getBody());

        verify(restaurantRepo,times(1)).findById(mockRestaurantID);

    }


}

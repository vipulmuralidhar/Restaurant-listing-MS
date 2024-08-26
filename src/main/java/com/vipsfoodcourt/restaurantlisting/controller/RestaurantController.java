package com.vipsfoodcourt.restaurantlisting.controller;


import com.vipsfoodcourt.restaurantlisting.dto.RestaurantDto;
import com.vipsfoodcourt.restaurantlisting.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;


    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDto>> fetchAllRestaurants() {

        List<RestaurantDto> allRestaurants = restaurantService.findAllRestaurants();

        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);

    }


    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDto> saveRestaurant(@RequestBody RestaurantDto restaurantDto){

        RestaurantDto restaurantAdded = restaurantService.addRestaurantInDB(restaurantDto);

        return new ResponseEntity<>(restaurantAdded,HttpStatus.CREATED);
    }


    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDto> findRestaurantById(@PathVariable Integer id){

        return restaurantService.findRestaurantById(id);
    }


}

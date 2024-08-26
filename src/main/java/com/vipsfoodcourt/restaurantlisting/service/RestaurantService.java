package com.vipsfoodcourt.restaurantlisting.service;


import com.vipsfoodcourt.restaurantlisting.dto.RestaurantDto;
import com.vipsfoodcourt.restaurantlisting.entity.Restaurant;
import com.vipsfoodcourt.restaurantlisting.mapper.RestaurantMapper;
import com.vipsfoodcourt.restaurantlisting.repo.RestaurantRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {


    private RestaurantRepo restaurantRepo;


    public List<RestaurantDto> findAllRestaurants() {

        List<Restaurant> restaurants = restaurantRepo.findAll();
        //map it to list of DTOs

        List<RestaurantDto> restaurantsDTO = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant)).collect(Collectors.toList());

        return restaurantsDTO;
    }

    public RestaurantDto addRestaurantInDB(RestaurantDto restaurantDto) {
        Restaurant savedRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDto));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(savedRestaurant);
    }





    public ResponseEntity<RestaurantDto> findRestaurantById(Integer id) {

        Optional<Restaurant> restaurant = restaurantRepo.findById(id);

        if (restaurant.isPresent())
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant.get()), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
}

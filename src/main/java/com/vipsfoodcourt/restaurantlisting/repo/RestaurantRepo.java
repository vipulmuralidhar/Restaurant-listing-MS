package com.vipsfoodcourt.restaurantlisting.repo;

import com.vipsfoodcourt.restaurantlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {


}

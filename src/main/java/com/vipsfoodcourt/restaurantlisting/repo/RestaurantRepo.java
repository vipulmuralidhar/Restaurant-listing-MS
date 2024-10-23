package com.vipsfoodcourt.restaurantlisting.repo;

import com.vipsfoodcourt.restaurantlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {


   /*
      If we want to call complex queries using jpa repository,quries would be like this
   @Repository
    public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
        @Query("SELECT r FROM Restaurant r WHERE r.rating > :rating")
        List<Restaurant> findRestaurantsWithHighRatings(@Param("rating") double rating);
    }
*/


}

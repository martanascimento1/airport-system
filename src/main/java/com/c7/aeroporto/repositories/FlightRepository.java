package com.c7.aeroporto.repositories;

import com.c7.aeroporto.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.plane.name = :name AND f.status < 6 ORDER BY f.departureTime DESC")
    Optional<Flight> findByPlaneName(@Param("name") String planeName);

    Optional<List<Flight>> findByDestinationCity(String city);
}

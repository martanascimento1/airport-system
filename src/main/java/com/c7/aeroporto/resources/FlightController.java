package com.c7.aeroporto.resources;

import com.c7.aeroporto.dtos.BaggageInfoDTO;
import com.c7.aeroporto.entities.Flight;
import com.c7.aeroporto.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    
    @GetMapping
    public ResponseEntity<List<Flight>> findAll(){
        List<Flight> flights = flightService.findAll();
        return ResponseEntity.ok().body(flights);
    }

    // FIND BY ID (CODE)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> findById(@PathVariable Long id){
        Flight flight = flightService.findById(id);
        return ResponseEntity.ok().body(flight);
    }

    // FIND BY PLANE
    @GetMapping(value = "/plane/{planeName}")
    public ResponseEntity<Flight> findByPlaneName(@PathVariable String planeName){
        Flight flight = flightService.findByPlaneName(planeName);
        return ResponseEntity.ok().body(flight);
    }

    // FIND BY DESTINATION.. CITY
    @GetMapping(value = "/destination/{city}")
    public ResponseEntity<List<Flight>> findByDestinationCity(@PathVariable String city){
        List<Flight> flights = flightService.findByDestinationCity(city);
        return ResponseEntity.ok().body(flights);
    }

    // BAGGAGE INFO

    @GetMapping(value = "/baggageInfo/{id}")
    public ResponseEntity<BaggageInfoDTO> baggageInfo(@PathVariable Long id){
        BaggageInfoDTO baggageInfoDTO = flightService.baggageInfo(id);
        return ResponseEntity.ok().body(baggageInfoDTO);
    }


}

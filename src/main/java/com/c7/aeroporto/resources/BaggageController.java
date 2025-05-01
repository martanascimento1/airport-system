package com.c7.aeroporto.resources;

import com.c7.aeroporto.dtos.BaggagePolicyDTO;
import com.c7.aeroporto.services.FlightService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baggage")
public class BaggageController {

    private final FlightService flightService;

    public BaggageController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/policy/{flightId}")
    public BaggagePolicyDTO getBaggagePolicy(@PathVariable Long flightId) {
        return flightService.getBaggagePolicy(flightId);
    }
}
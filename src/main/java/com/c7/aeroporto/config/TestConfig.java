package com.c7.aeroporto.config;

import com.c7.aeroporto.entities.Flight;
import com.c7.aeroporto.entities.Plane;
import com.c7.aeroporto.entities.vo.Address;
import com.c7.aeroporto.repositories.FlightRepository;
import com.c7.aeroporto.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PlaneRepository planeRepository;

    @Override
    public void run(String... args) throws Exception{

        //Plane p1 = new Plane(null ,"Aviator", "Boeing 752", 100, 10000.0, 100.20, 150.0, 100000.5);
        Plane p1 = Plane.builder()
                .name("Aviator")
                .model("Boeing 752")
                .maxPassengers(100)
                .maxLuggageWeight(10000.0)
                .maxWeightPerLuggage(100.20)
                .maxWeightPerPassenger(150.0)
                .totalAllowedPassengerWeight(100000.5)
                .build();
        planeRepository.save(p1);

        Address a1 = new Address("Fix Ders", 20, 5210, "Los Angeles", "California");
        Address a2 = new Address("Holes Drude", 32, 1230, "Las Vegas", "Nevada");

        LocalDateTime horarioPartida = LocalDateTime.now();
        LocalDateTime horarioChegada = horarioPartida.plusHours(3);

        Flight f1 = Flight.builder()
                .status(1)
               // .plane(aviao)
                .departureTime(LocalDateTime.now())
                .arrivalTime(horarioChegada)
                .origin(new Address())
                .destination(new Address())
                .flightPrice(750.0)
                .overweightBaggageFee(50.0)
                .build();
        flightRepository.save(f1);


    }

}

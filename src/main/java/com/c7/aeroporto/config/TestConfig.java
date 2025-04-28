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
        public void run(String... args) throws Exception {

            // Criação do avião
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

            // Endereços
            Address a1 = new Address("Fix Ders", 20, 5210, "Los Angeles", "California");
            Address a2 = new Address("Holes Drude", 32, 1230, "Las Vegas", "Nevada");

            // Horários
            LocalDateTime horarioPartida = LocalDateTime.now();
            LocalDateTime horarioChegada = horarioPartida.plusHours(3);

            // Criação do voo COM o avião associado
            Flight f1 = Flight.builder()
                    .status(1)
                    .plane(p1)  // Associando o avião criado anteriormente
                    .departureTime(horarioPartida)
                    .arrivalTime(horarioChegada)
                    .origin(a1)  // Usando o endereço criado
                    .destination(a2)  // Usando o endereço criado
                    .flightPrice(750.0)
                    .overweightBaggageFee(50.0)
                    .build();

            flightRepository.save(f1);
        }
    }
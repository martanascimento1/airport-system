package com.c7.aeroporto.services;


import com.c7.aeroporto.dtos.BaggageInfoDTO;
import com.c7.aeroporto.dtos.BaggagePolicyDTO;
import com.c7.aeroporto.entities.Flight;
import com.c7.aeroporto.entities.Plane;
import com.c7.aeroporto.entities.Reservation;
import com.c7.aeroporto.observer.FlightNotifier;
import com.c7.aeroporto.observer.PassengerObserver;
import com.c7.aeroporto.repositories.FlightRepository;
import com.c7.aeroporto.repositories.PlaneRepository;
import com.c7.aeroporto.repositories.ReservationRepository;
import com.c7.aeroporto.services.exceptions.ResourceNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebResourcesRuntimeHints;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PlaneRepository planeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // observer é ativado
    // Instância concreta que dispara o evento que ocorreu
    public Flight updateStatus(Long flightId, Integer newStatus) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException(flightId));

        flight.setStatus(newStatus);
        flightRepository.save(flight);

        // Busca todas as reservas relacionadas a esse voo
        List<Reservation> reservations = reservationRepository.findByFlightId(flightId);

        // Cria o notificador e adiciona os passageiros como observadores
        FlightNotifier notifier = new FlightNotifier();
        for (Reservation reservation : reservations) {
            notifier.addObserver(new PassengerObserver(reservation));
        }

        // Define a mensagem com base no novo status
        String statusMessage = switch (newStatus) {
            case 1 -> "Voo agendado.";
            case 2 -> "Voo em embarque.";
            case 3 -> "Voo atrasado.";
            case 4 -> "Voo cancelado.";
            default -> "Status do voo atualizado.";
        };

        // Notificação para os passageiros
        notifier.notifyObservers(statusMessage);

        return flight;
    }

    public List<Flight> findAll(){
        return flightRepository.findAll();
    }

    public Flight findById(Long id){
        return flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }


    public Flight findByPlaneName(String planeName) {

        return flightRepository.findByPlaneName(planeName).orElseThrow(() -> new ResourceNotFoundException(planeName));
    }

    public List<Flight> findByDestinationCity(String city){
        return flightRepository.findByDestinationCity(city).orElseThrow(() -> new ResourceNotFoundException(city));
    }

    public BaggageInfoDTO baggageInfo(Long flightId){

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResourceNotFoundException(flightId));

        BaggageInfoDTO baggageInfoDTO = new BaggageInfoDTO(flight, flight.getOverweightBaggageFee(), flight.getPlane().getMaxLuggageWeight(), flight.getPlane().getMaxWeightPerLuggage());

        return baggageInfoDTO;

    }

    //valida a criação de objetos e permite a criacao parcial de objetos:
    public Flight createNewFlight(Integer status, Long planeId) {
        Plane plane = planeRepository.findById(planeId).orElseThrow();
        return Flight.builder()
                .status(status)
                .plane(plane)
                .departureTime(LocalDateTime.now())
                .build();
    }

    public BaggagePolicyDTO getBaggagePolicy(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Voo não encontrado"));

        return new BaggagePolicyDTO(
                flight.getPlane().getMaxWeightPerLuggage(),
                flight.getOverweightBaggageFee()
        );
    }
}
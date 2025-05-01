package com.c7.aeroporto.repositories;

import com.c7.aeroporto.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    // Métodos customizados podem ser adicionados aqui
    Passenger findByPassportNumber(String passportNumber);
}
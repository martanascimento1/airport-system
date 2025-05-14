package com.c7.aeroporto.entities;

import com.c7.aeroporto.entities.vo.Address;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "tb_flights")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer status;

    @ManyToOne
    private Plane plane;
    private Double flightPrice;
    private Double overweightBaggageFee;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "origin_street")),
            @AttributeOverride(name = "number", column = @Column(name = "origin_number")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "origin_zip_code")),
            @AttributeOverride(name = "city", column = @Column(name = "origin_city")),
            @AttributeOverride(name = "state", column = @Column(name = "origin_state"))
    })
    private Address origin;

    @ManyToOne
    private Airport originAirport;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "destination_street")),
            @AttributeOverride(name = "number", column = @Column(name = "destination_number")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "destination_zip_code")),
            @AttributeOverride(name = "city", column = @Column(name = "destination_city")),
            @AttributeOverride(name = "state", column = @Column(name = "destination_state"))
    })
    private Address destination;

    @ManyToOne
    private Airport destinationAirport;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    // Construtor privado para JPA e Builder
    public Flight() {}

    // O padrão Builder foi implementado para melhorar a criação de objetos complexos como Flight e Plane, os quais possuem muitos atributos.

    public static Builder builder() {
        return new Builder();
    }

    // classe com Builder interno que contem os métodos para setar os atributos do objeto
    public static class Builder {
        private final Flight flight;

        //metodos para cada atributo do objeto construido
        public Builder() {
            this.flight = new Flight();
        }
        // Cada metodo retorna o proprio Builder:
        public Builder id(Long id) {
            flight.id = id;
            return this;
        }

        public Builder status(Integer status) {
            flight.status = status;
            return this;
        }

        public Builder plane(Plane plane) {
            flight.plane = plane;
            return this;
        }

        public Builder flightPrice(Double flightPrice) {
            flight.flightPrice = flightPrice;
            return this;
        }

        public Builder overweightBaggageFee(Double fee) {
            flight.overweightBaggageFee = fee;
            return this;
        }

        public Builder origin(Address origin) {
            flight.origin = origin;
            return this;
        }

        public Builder originAirport(Airport airport) {
            flight.originAirport = airport;
            return this;
        }

        public Builder destination(Address destination) {
            flight.destination = destination;
            return this;
        }

        public Builder destinationAirport(Airport airport) {
            flight.destinationAirport = airport;
            return this;
        }

        public Builder departureTime(LocalDateTime time) {
            flight.departureTime = time;
            return this;
        }

        public Builder arrivalTime(LocalDateTime time) {
            flight.arrivalTime = time;
            return this;
        }

        public Flight build() { //retorna a instancia criada
            validateMandatoryFields();
            validateBusinessRules();
            return flight;
        }

                //verificacao de campos obrigatórios
        private void validateMandatoryFields() {
            StringBuilder errors = new StringBuilder();

            if (flight.status == null) {
                errors.append("Status não pode ser nulo. ");
            }

            if (flight.plane == null) {
                errors.append("Avião não pode ser nulo. ");
            }

            if (flight.departureTime == null) {
                errors.append("Horário de partida não pode ser nulo. ");
            }

            if (flight.destinationAirport == null && flight.destination == null) {
                errors.append("Pelo menos um destino (aeroporto ou endereço) deve ser definido. ");
            }

            if (flight.originAirport == null && flight.origin == null) {
                errors.append("Pelo menos uma origem (aeroporto ou endereço) deve ser definida. ");
            }

            if (errors.length() > 0) {
                throw new IllegalStateException("Erro na criação do voo: " + errors.toString());
            }
        }

        private void validateBusinessRules() {
            StringBuilder errors = new StringBuilder();

            if (flight.status != null && (flight.status < 1 || flight.status > 5)) {
                errors.append("Status do voo deve estar entre 1 e 5. ");
            }


            if (flight.flightPrice != null && flight.flightPrice <= 0) {
                errors.append("Preço do voo deve ser positivo. ");
            }

            if (flight.overweightBaggageFee != null && flight.overweightBaggageFee < 0) {
                errors.append("Taxa de bagagem excessiva não pode ser negativa. ");
            }

            if (flight.departureTime != null && flight.arrivalTime != null &&
                    flight.arrivalTime.isBefore(flight.departureTime)) {
                errors.append("O horário de chegada deve ser posterior ao de partida. ");
            }

            if (errors.length() > 0) {
                throw new IllegalStateException("Erro de regras de negócio: " + errors.toString());
            }
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getStatus() { return status; }
    public Plane getPlane() { return plane; }
    public Double getOverweightBaggageFee() { return overweightBaggageFee; }
    public void setStatus(Integer status) { this.status = status; }
    public void setOverweightBaggageFee(Double fee) { this.overweightBaggageFee = fee; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public Address getOrigin() { return origin; }
    public Airport getOriginAirport() { return originAirport; }
    public Address getDestination() { return destination; }
    public Airport getDestinationAirport() { return destinationAirport; }
    public Double getFlightPrice() { return flightPrice; }
}
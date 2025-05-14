package com.c7.aeroporto.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_planes")
public class Plane implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;

    @OneToMany
    private List<Flight> flights = new ArrayList<>();

    private Integer maxPassengers;
    private Double maxLuggageWeight;
    private Double maxWeightPerLuggage;
    private Double maxWeightPerPassenger;
    private Double totalAllowedPassengerWeight;

    // Construtor privado para JPA e Builder
    private Plane() {}

    public static Builder builder() {
        return new Builder();
    }

    //  classe para construir o objeto
    public static class Builder {
        private final Plane plane;

        //metodos para cada atributo do objeto construido
        public Builder() {
            this.plane = new Plane();
        }

        public Builder id(Long id) {
            plane.id = id;
            return this;
        }

        public Builder name(String name) {
            plane.name = name;
            return this;
        }

        public Builder model(String model) {
            plane.model = model;
            return this;
        }

        public Builder maxPassengers(Integer maxPassengers) {
            plane.maxPassengers = maxPassengers;
            return this;
        }

        public Builder maxLuggageWeight(Double maxLuggageWeight) {
            plane.maxLuggageWeight = maxLuggageWeight;
            return this;
        }

        public Builder maxWeightPerLuggage(Double maxWeightPerLuggage) {
            plane.maxWeightPerLuggage = maxWeightPerLuggage;
            return this;
        }

        public Builder maxWeightPerPassenger(Double maxWeightPerPassenger) {
            plane.maxWeightPerPassenger = maxWeightPerPassenger;
            return this;
        }

        public Builder totalAllowedPassengerWeight(Double totalAllowedPassengerWeight) {
            plane.totalAllowedPassengerWeight = totalAllowedPassengerWeight;
            return this;
        }

        public Plane build() {
            validateMandatoryFields();
            validateBusinessRules();
            return plane;
        }

        private void validateMandatoryFields() {
            StringBuilder errors = new StringBuilder();

            if (plane.name == null || plane.name.trim().isEmpty()) {
                errors.append("Nome do avião não pode ser nulo ou vazio. ");
            }

            if (plane.model == null || plane.model.trim().isEmpty()) {
                errors.append("Modelo do avião não pode ser nulo ou vazio. ");
            }

            if (plane.maxPassengers == null) {
                errors.append("Número máximo de passageiros não pode ser nulo. ");
            }

            if (errors.length() > 0) {
                throw new IllegalStateException("Erro na criação do avião: " + errors.toString());
            }
        }

        private void validateBusinessRules() {
            StringBuilder errors = new StringBuilder();

            if (plane.maxPassengers != null && plane.maxPassengers <= 0) {
                errors.append("Número máximo de passageiros deve ser positivo. ");
            }

            if (plane.maxLuggageWeight != null && plane.maxLuggageWeight <= 0) {
                errors.append("Peso máximo de bagagem deve ser positivo. ");
            }

            if (plane.maxWeightPerLuggage != null && plane.maxWeightPerLuggage <= 0) {
                errors.append("Peso máximo por bagagem deve ser positivo. ");
            }

            if (plane.maxWeightPerPassenger != null && plane.maxWeightPerPassenger <= 0) {
                errors.append("Peso máximo por passageiro deve ser positivo. ");
            }

            if (plane.totalAllowedPassengerWeight != null && plane.totalAllowedPassengerWeight <= 0) {
                errors.append("Peso total permitido para passageiros deve ser positivo. ");
            }

            if (plane.maxWeightPerLuggage != null && plane.maxLuggageWeight != null &&
                    plane.maxWeightPerLuggage > plane.maxLuggageWeight) {
                errors.append("Peso máximo por bagagem não pode ser maior que o peso máximo total de bagagem. ");
            }

            if (plane.maxWeightPerPassenger != null && plane.totalAllowedPassengerWeight != null &&
                    plane.maxWeightPerPassenger > plane.totalAllowedPassengerWeight) {
                errors.append("Peso máximo por passageiro não pode ser maior que o peso total permitido para passageiros. ");
            }

            if (errors.length() > 0) {
                throw new IllegalStateException("Erro de regras de negócio: " + errors.toString());
            }
        }
    }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setModel(String model) { this.model = model; }


    public Long getId() { return id; }
    public String getName() { return name; }
    public String getModel() { return model; }
    public Integer getMaxPassengers() { return maxPassengers; }
    public Double getMaxLuggageWeight() { return maxLuggageWeight; }
    public Double getMaxWeightPerLuggage() { return maxWeightPerLuggage; }
    public Double getMaxWeightPerPassenger() { return maxWeightPerPassenger; }
    public Double getTotalAllowedPassengerWeight() { return totalAllowedPassengerWeight; }
    public List<Flight> getFlights() { return flights; }


}
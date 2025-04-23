package com.c7.aeroporto.dtos;

import com.c7.aeroporto.entities.Flight;

public class BaggageInfoDTO {

    private Flight flight;
    private Double baggageFee;
    private Double maxLuggageWeight;
    private Double maxWeightPerLuggage;

    // Construtor privado para Builder
    public BaggageInfoDTO() {}

    public BaggageInfoDTO(Flight flight, Double overweightBaggageFee, Double maxLuggageWeight, Double maxWeightPerLuggage) {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final BaggageInfoDTO dto = new BaggageInfoDTO();

        public Builder flight(Flight flight) {
            dto.flight = flight;
            return this;
        }

        public Builder baggageFee(Double fee) {
            dto.baggageFee = fee;
            return this;
        }

        public Builder maxLuggageWeight(Double weight) {
            dto.maxLuggageWeight = weight;
            return this;
        }

        public Builder maxWeightPerLuggage(Double weight) {
            dto.maxWeightPerLuggage = weight;
            return this;
        }

        public BaggageInfoDTO build() {
            if (dto.flight == null) {
                throw new IllegalStateException("Flight não pode ser nulo");
            }
            return dto;
        }
    }

    
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Double getBaggageFee() {
        return baggageFee;
    }

    public void setBaggageFee(Double baggageFee) {
        this.baggageFee = baggageFee;
    }

    public Double getMaxLuggageWeight() {
        return maxLuggageWeight;
    }

    public void setMaxLuggageWeight(Double maxLuggageWeight) {
        this.maxLuggageWeight = maxLuggageWeight;
    }

    public Double getMaxWeightPerLuggage() {
        return maxWeightPerLuggage;
    }

    public void setMaxWeightPerLuggage(Double maxWeightPerLuggage) {
        this.maxWeightPerLuggage = maxWeightPerLuggage;
    }

}

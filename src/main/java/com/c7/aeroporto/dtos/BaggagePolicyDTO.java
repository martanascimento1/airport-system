package com.c7.aeroporto.dtos;

public record BaggagePolicyDTO(
        double maxWeightPerLuggage,
        double overweightFee
) {}
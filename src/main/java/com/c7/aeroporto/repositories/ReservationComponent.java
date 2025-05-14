package com.c7.aeroporto.repositories;

// Interface base que define as operações comuns que serão implementadas tanto pelo componente concreto quanto pelos decoradores:
public interface ReservationComponent {

    String getDetails(); // retorna os detalhes da reserva
    double getCost(); // retorna o custo total

}

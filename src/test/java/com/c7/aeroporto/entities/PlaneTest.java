package com.c7.aeroporto.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaneTest {
    @Test
    public void testPlaneBuilder() {
        //criando um objeto usando o padrao
        Plane plane = Plane.builder()
                .name("Boeing 747")
                .model("747-400")
                .maxPassengers(300)
                .maxLuggageWeight(5000.0)
                .maxWeightPerLuggage(23.0)
                .maxWeightPerPassenger(100.0)
                .totalAllowedPassengerWeight(30000.0)
                .build();

        assertNotNull(plane);
        assertEquals("Boeing 747", plane.getName());
        assertEquals("747-400", plane.getModel());
        assertEquals(300, plane.getMaxPassengers());
        assertEquals(5000.0, plane.getMaxLuggageWeight());
        assertEquals(23.0, plane.getMaxWeightPerLuggage());
    }

    @Test
    public void testMissingMandatoryFields() {
        // Teste para nome ausente
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Plane.builder()
                    .model("747-400")
                    .maxPassengers(300)
                    .build();
        });
        assertTrue(exception.getMessage().contains("Nome do avião não pode ser nulo"));

        // Teste para modelo ausente
        exception = assertThrows(IllegalStateException.class, () -> {
            Plane.builder()
                    .name("Boeing 747")
                    .maxPassengers(300)
                    .build();
        });
        assertTrue(exception.getMessage().contains("Modelo do avião não pode ser nulo"));

        // Teste para maxPassengers ausente
        exception = assertThrows(IllegalStateException.class, () -> {
            Plane.builder()
                    .name("Boeing 747")
                    .model("747-400")
                    .build();
        });
        assertTrue(exception.getMessage().contains("Número máximo de passageiros não pode ser nulo"));
    }

    @Test
    public void testBusinessRuleViolations() {
        // Teste para maxPassengers inválido
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Plane.builder()
                    .name("Boeing 747")
                    .model("747-400")
                    .maxPassengers(0) // Inválido: deve ser positivo
                    .build();
        });
        assertTrue(exception.getMessage().contains("Número máximo de passageiros deve ser positivo"));

        // Teste para maxLuggageWeight inválido
        exception = assertThrows(IllegalStateException.class, () -> {
            Plane.builder()
                    .name("Boeing 747")
                    .model("747-400")
                    .maxPassengers(300)
                    .maxLuggageWeight(-100.0) // Inválido: deve ser positivo
                    .build();
        });
        assertTrue(exception.getMessage().contains("Peso máximo de bagagem deve ser positivo"));

        // Teste para inconsistência entre pesos
        exception = assertThrows(IllegalStateException.class, () -> {
            Plane.builder()
                    .name("Boeing 747")
                    .model("747-400")
                    .maxPassengers(300)
                    .maxLuggageWeight(1000.0)
                    .maxWeightPerLuggage(1500.0) // Inválido: maior que maxLuggageWeight
                    .build();
        });
        assertTrue(exception.getMessage().contains("Peso máximo por bagagem não pode ser maior que o peso máximo total"));
    }
}
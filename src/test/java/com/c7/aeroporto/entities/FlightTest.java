package com.c7.aeroporto.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import com.c7.aeroporto.entities.vo.Address;
import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    // Criação de objetos para testes com builder
    @Test
    public void testFlightBuilder() {
        Plane plane = Plane.builder()
                .name("Boeing 747")
                .model("747-400")
                .maxPassengers(300)
                .build(); //ja faz a validação das regras e exceçoes

        LocalDateTime departureTime = LocalDateTime.now();
        LocalDateTime arrivalTime = departureTime.plusHours(2);

        Address origin = new Address();
        Address destination = new Address();
        //criar instancias do objeto para os testes unitários
        // inicia a construção de um objeto Flight completo:
        Flight flight = Flight.builder()
                .status(1)
                .plane(plane)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .origin(origin)
                .destination(destination)
                .flightPrice(500.0)
                .overweightBaggageFee(50.0)
                .build();

        assertNotNull(flight);
        assertEquals(1, flight.getStatus());
        assertEquals("Boeing 747", flight.getPlane().getName());
        assertEquals(departureTime, flight.getDepartureTime());
        assertEquals(arrivalTime, flight.getArrivalTime());
        assertEquals(500.0, flight.getFlightPrice());
        assertEquals(50.0, flight.getOverweightBaggageFee());
    }

    @Test
    public void testMissingMandatoryFields() {

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Flight.builder()
                    .plane(validPlane())
                    .departureTime(LocalDateTime.now())
                    .build();
        });
        assertTrue(exception.getMessage().contains("Status não pode ser nulo"));

        exception = assertThrows(IllegalStateException.class, () -> {
            Flight.builder()
                    .status(1)
                    .departureTime(LocalDateTime.now())
                    .build();
        });
        assertTrue(exception.getMessage().contains("Avião não pode ser nulo"));

        exception = assertThrows(IllegalStateException.class, () -> {
            Flight.builder()
                    .status(1)
                    .plane(validPlane())
                    .build();
        });
        assertTrue(exception.getMessage().contains("Horário de partida não pode ser nulo"));

        // Teste para destino ausente
        exception = assertThrows(IllegalStateException.class, () -> {
            Flight.builder()
                    .status(1)
                    .plane(validPlane())
                    .departureTime(LocalDateTime.now())
                    .origin(new Address())
                    .build();
        });
        assertTrue(exception.getMessage().contains("destino"));
    }

    @Test
    public void testBusinessRuleViolations() {
        // Teste para status inválido
        // Com o builder a validacao de erros pode ser feita diretamente nos testes
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Flight.builder()
                    .status(6) // Inválido: deve estar entre 1-5
                    .plane(validPlane())
                    .departureTime(LocalDateTime.now())
                    .destination(new Address())
                    .origin(new Address())
                    .build();
        });
        assertTrue(exception.getMessage().contains("Status do voo deve estar entre 1 e 5"));

        // Teste para preço inválido
        exception = assertThrows(IllegalStateException.class, () -> {
            Flight.builder()
                    .status(1)
                    .plane(validPlane())
                    .departureTime(LocalDateTime.now())
                    .destination(new Address())
                    .origin(new Address())
                    .flightPrice(-100.0) // Inválido: deve ser positivo
                    .build();
        });
        assertTrue(exception.getMessage().contains("Preço do voo deve ser positivo"));

        // Teste para horários inconsistentes
        LocalDateTime departure = LocalDateTime.now().plusHours(2);
        LocalDateTime arrival = LocalDateTime.now(); // Antes da partida

        exception = assertThrows(IllegalStateException.class, () -> {
            Flight.builder()
                    .status(1)
                    .plane(validPlane())
                    .departureTime(departure)
                    .arrivalTime(arrival)
                    .destination(new Address())
                    .origin(new Address())
                    .build();
        });
        assertTrue(exception.getMessage().contains("horário de chegada deve ser posterior"));
    }

    private Plane validPlane() {
        return Plane.builder()
                .name("Boeing 747")
                .model("747-400")
                .maxPassengers(300)
                .build();
    }
}
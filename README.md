
# Airline Reservation System
## Implementation of system requested in software project discipline
**Student** : Marta Mirely Nascimento dos Santos
**Teacher** : Baldoíno Fonseca
## Features:
1. Flight Search: Users can see information such as origin, destination, times and status;
3. Reservation Management: Users can book, cancel and modify flight reservations; *
4. Online Check-in: Users can check in online for their flights; *
5. Baggage Information: Information on baggage allowances and fees;
6. Flight Status Updates: Real-time updates on flight status;
### Classes:
1. Airport
2. Plane
3. Flight
4. Passenger
5. Reservation
### Design Patterns implementados:
1.Builder: Contrução de objetos complexos nas classes Flight e Plane:
* Implementação da criação de objetos com builder nas classes FlightTest.java e PlaneTest.java, ambos no caminho : /src/test/java/com/c7/aeroporto/entities

2.Decorator: criação de reservas com informações adicionais conforme a escolha de cada passageiro:


* Interface Base: **ReservationComponent.java** - Interface base que define as operações comuns que serão implementadas tanto pelo componente concreto quanto pelos decoradores.
* Componente Concreto: **BasicReservation.java** - Implementação padrão de uma reserva, pode ser usada sozinha ou envolvida com algum decorador para adicionar mais funcionalidades.
* Decorador Abstrato: **ReservationDecorator.java** - Implementa a mesma interface de Reservation Component.
* Decorador Concreto: **SpecialMeal.java**, **SeatSelection.java**, **TravelInsurance**



3.Observer: Notificar os passageiros em casos de atualizações no status de voos em que eles possuem reservas:

* Subject: **FlightNotifier.java** - Armazena a lista de observadores (passageiros) e notifica todos eles quando algo acontece com o voo.
* Observer: **Observer.java** - Interface base, define o contrato para qualquer classe que queira ser notificada.
* Concrete: **PassengerObserver.java** - Implementa Observer e representa um passageiro.


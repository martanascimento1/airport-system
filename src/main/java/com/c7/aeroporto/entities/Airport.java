package com.c7.aeroporto.entities;

import com.c7.aeroporto.entities.vo.Address;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_airport")
public class Airport implements Serializable {

    @Id
    private Long id;

    @Embedded
    private Address address;

    // VOOS QUE ESTAO NO AEROPORTO OU QUE O DESTINO EH O AEROPORTO
    @OneToMany
    private List<Flight> flights = new ArrayList<>();


}

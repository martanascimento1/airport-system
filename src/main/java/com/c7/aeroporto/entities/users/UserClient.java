package com.c7.aeroporto.entities.users;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_userClient")
public class UserClient extends User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private Integer loyaltyPoints;

}

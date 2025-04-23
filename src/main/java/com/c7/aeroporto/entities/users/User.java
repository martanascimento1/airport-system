package com.c7.aeroporto.entities.users;

import com.c7.aeroporto.entities.vo.Address;
import jakarta.persistence.Embedded;

public abstract class User {

    private String name;
    private Integer CPF;
    private Integer age;
    private String email;

    @Embedded
    private Address address;

    public User(){}

}

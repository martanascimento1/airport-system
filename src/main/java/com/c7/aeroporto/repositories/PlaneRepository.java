package com.c7.aeroporto.repositories;

import com.c7.aeroporto.entities.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

}

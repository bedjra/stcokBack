package com.Megatram.Megatram.repository;


import com.Megatram.Megatram.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNom(String nom);

}

package com.lab.laboratorio.repository;

import com.lab.laboratorio.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

}

package com.lab.laboratorio.repository;

import com.lab.entities.Client;

import java.util.List;

public interface ClientRepositoryCustom {
    List<com.lab.laboratorio.entities.Client> findAllByIdentify(String name);
}

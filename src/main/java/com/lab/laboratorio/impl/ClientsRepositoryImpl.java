package com.lab.laboratorio.impl;
import com.lab.laboratorio.entities.Client;
import com.lab.laboratorio.repository.ClientRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientsRepositoryImpl implements ClientRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> findAllByIdentify(String identify) {
        String jpql = "SELECT p FROM Client p WHERE LOWER(p.identify) = LOWER(:identify)";
        TypedQuery<Client> query = entityManager.createQuery(jpql, com.lab.laboratorio.entities.Client.class);
        query.setParameter("identify", identify);
        return query.getResultList();
    }
}

package com.entrepiscoynazca.appweb.repository;

import com.entrepiscoynazca.appweb.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ClienteRepository extends JpaRepository<Cliente, Integer>{

    
}
package com.entrepiscoynazca.appweb.repository;

import com.entrepiscoynazca.appweb.model.Contacto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ContactoRepository extends JpaRepository<Contacto, Integer>{

    
}
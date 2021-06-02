package com.entrepiscoynazca.appweb.repository;

import com.entrepiscoynazca.appweb.model.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PagoRepository extends JpaRepository<Pago, Integer>{

    
}
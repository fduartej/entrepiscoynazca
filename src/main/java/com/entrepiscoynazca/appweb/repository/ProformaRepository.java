package com.entrepiscoynazca.appweb.repository;

import com.entrepiscoynazca.appweb.model.Proforma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProformaRepository extends JpaRepository<Proforma, Integer>{
    
}

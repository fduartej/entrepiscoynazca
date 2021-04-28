package com.entrepiscoynazca.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entrepiscoynazca.appweb.domain.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, String>{

    
}
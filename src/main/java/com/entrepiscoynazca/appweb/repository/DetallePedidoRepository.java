package com.entrepiscoynazca.appweb.repository;

import java.util.List;

import com.entrepiscoynazca.appweb.model.DetallePedido;
import com.entrepiscoynazca.appweb.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{

    @Query(value = "SELECT o FROM DetallePedido o WHERE o.pedido=?1")
    List<DetallePedido> findItemsByPedido(Pedido pedido);
    
}
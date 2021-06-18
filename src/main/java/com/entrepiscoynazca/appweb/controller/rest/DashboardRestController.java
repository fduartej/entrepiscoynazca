package com.entrepiscoynazca.appweb.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.*;

import com.entrepiscoynazca.appweb.repository.*;

@RestController
@RequestMapping(value = "api/dashboard", produces = "application/json")
public class DashboardRestController {

    private final DetallePedidoRepository pedidosData;

    public DashboardRestController(DetallePedidoRepository pedidosData){
        this.pedidosData = pedidosData;
    } 

    @GetMapping(value = "/pedidostotales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Object>>> productos(){
        return  new ResponseEntity<List<Map<String, Object>>>(
            pedidosData.querySumaTotal(), HttpStatus.OK);
    }


}
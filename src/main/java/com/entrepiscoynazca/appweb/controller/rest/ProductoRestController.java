package com.entrepiscoynazca.appweb.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.*;

import com.entrepiscoynazca.appweb.model.Producto;
import com.entrepiscoynazca.appweb.repository.*;

@RestController
@RequestMapping(value = "api/productos", produces = "application/json")
public class ProductoRestController {
    
    private final ProductoRepository productsData;

    public ProductoRestController(ProductoRepository productsData){
        this.productsData = productsData;
    } 

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> productos(){
        return  new ResponseEntity<List<Producto>>(
            productsData.findAll(), HttpStatus.OK);
    }
}

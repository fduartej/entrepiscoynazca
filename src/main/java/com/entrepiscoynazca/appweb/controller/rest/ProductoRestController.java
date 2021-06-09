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

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Producto e){
        productsData.save(e);
        productsData.flush();
        return new ResponseEntity<Integer>(e.getId(),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> Productos(@PathVariable int id){
        Optional<Producto> optinalEntity = productsData.findById(id);
        if(optinalEntity.isPresent())
            return new ResponseEntity<Producto>(
                optinalEntity.get(), HttpStatus.OK);
        else
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable int id){
        productsData.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> update(@RequestBody Producto update){
        Optional<Producto> optinalEntity = productsData.findById(update.getId());
        if(optinalEntity.isPresent()){
            Producto current = optinalEntity.get();
            current.setDescripcion(update.getDescripcion());
            create(current);
        }
        return new ResponseEntity<Producto>(HttpStatus.OK);
    }
}

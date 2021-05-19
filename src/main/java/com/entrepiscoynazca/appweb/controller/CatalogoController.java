package com.entrepiscoynazca.appweb.controller;

import java.util.List;

import com.entrepiscoynazca.appweb.model.Producto;
import com.entrepiscoynazca.appweb.repository.ProductoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class CatalogoController{

    private static final String INDEX ="catalogo/index"; 
    private final ProductoRepository productsData;
    

    public CatalogoController(ProductoRepository productsData
        ){
        this.productsData = productsData;
        
    }      

    @GetMapping("/catalogo/index")
    public String index(Model model){
        List<Producto> listProducto = this.productsData.getAllActiveProductos();
        model.addAttribute("products",listProducto);
        return INDEX;
    }    

    @GetMapping("/catalogo/add/{id}")
    public String add(@PathVariable("id") int id, 
        Model model){
        return INDEX;
    }  
}
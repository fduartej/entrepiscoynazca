package com.entrepiscoynazca.appweb.controller;

import java.util.List;

import com.entrepiscoynazca.appweb.model.Producto;
import com.entrepiscoynazca.appweb.model.Proforma;
import com.entrepiscoynazca.appweb.model.Usuario;
import com.entrepiscoynazca.appweb.repository.ProductoRepository;
import com.entrepiscoynazca.appweb.repository.ProformaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;

@Controller
public class CatalogoController{

    private static final String INDEX ="catalogo/index"; 
    private final ProductoRepository productsData;
    private final ProformaRepository proformaData;
    

    public CatalogoController(ProductoRepository productsData,
        ProformaRepository proformaData
        ){
        this.productsData = productsData;
        this.proformaData = proformaData; 
        
    }      

    @GetMapping("/catalogo/index")
    public String index(Model model){
        List<Producto> listProducto = this.productsData.getAllActiveProductos();
        model.addAttribute("products",listProducto);
        return INDEX;
    }    

    @GetMapping("/catalogo/add/{id}")
    public String add(@PathVariable("id") int id, 
        HttpSession session,
        Model model){
        Usuario user = (Usuario)session.getAttribute("user"); 
        if(user==null) {
            //TODO: debe loguearse
        }else{
            Producto productSeleccionado = productsData.getOne(id);
            Proforma itemCarrito = new Proforma();
            itemCarrito.setCantidad(1);
            itemCarrito.setUser(user);
            itemCarrito.setPrecio(productSeleccionado.getPrecio());
            itemCarrito.setProduct(productSeleccionado);
            proformaData.save(itemCarrito);
        }   

        return INDEX;
    }  
}
package com.entrepiscoynazca.appweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ClienteController {
    
    private static final String INDEX ="cliente/index"; 

    @GetMapping("/cliente/index")
    public String index(Model model) {
        return INDEX;
    }    

}

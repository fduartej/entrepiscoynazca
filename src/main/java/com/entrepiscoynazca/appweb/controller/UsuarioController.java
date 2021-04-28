package com.entrepiscoynazca.appweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class UsuarioController {
    
    private static final String INDEX ="usuario/login"; 

    @GetMapping("/usuario/login")
    public String login(Model model) {
        return INDEX;
    }  
}

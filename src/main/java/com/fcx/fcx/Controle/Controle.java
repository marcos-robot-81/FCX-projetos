// Pacote em letras minúsculas
package com.fcx.fcx.Controle; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;;


@Controller
public class Controle {

    @GetMapping("/")
    public String getIndex(Model model){
        
        return "test"; 
    }

    @GetMapping("home")
    public String gethome( Model model){ {
        
        model.addAttribute("message", "Bem-vindo ao FCX!");
        return "home";
    }
    
}}
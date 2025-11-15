// Pacote em letras minúsculas
package com.fcx.fcx.Controle; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;;


@Controller
public class Controle {

    @GetMapping("/")
    public String getIndex(Model model){
        
        return "login"; 
    }

    @PostMapping("home")
    public String PostHome(
        @RequestParam("name") String name, 
        @RequestParam("Senha") String Senha,
        Model model
    ) {
        
        model.addAttribute("message", "Bem-vindo ao FCX! " +name);
        return "home";
    }
    @GetMapping("home")
    public String gethome(Model model){return "login";}
    


    
    
}
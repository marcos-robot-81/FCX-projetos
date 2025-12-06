package com.fcx.fcx.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Calendar;

import com.fcx.fcx.dynamo.*;

//Test da versão alfa 
import com.fcx.fcx.DB.*;

@Controller
public class Controle {

    @GetMapping("/")
    public String getIndex(Model model) {
        return "login";
    }

    @PostMapping("home")
    public String PostHome(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "Senha", required = false) String Senha,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "bid", required = false) String bid,
            @RequestParam(value = "bname", required = false) String bname,
            @RequestParam(value = "bquantidade", required = false) String bquantidade,
            Model model) {
        //Funcionario func = new Funcionario();

        //func.setMatricola(bid);
        //func.setNome(name);
        //func.setQualtidade(bquantidade);

        Calendar hoje = Calendar.getInstance();
        model.addAttribute("message", "Bem-vindo ao FCX! " + name);
        String data;
        // ! set date
        if (date != null) {
            data = date;
        } else {
            data = hoje.get(Calendar.YEAR) + "-" + (hoje.get(Calendar.MONTH) + 1) + "-"
                    + hoje.get(Calendar.DAY_OF_MONTH); // yes ISO
            // String data = (hoje.get(Calendar.DAY_OF_MONTH) + "-" +
            // (hoje.get(Calendar.MONTH) + 1) + "-" + hoje.get(Calendar.YEAR)); // not ISO

        }

        // ! get Dados and set to model
        GDados gd = new GDados();
        ArrayList<Pessoa> pessoas = gd.GetDados("");
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("bquantidade", bquantidade);
        model.addAttribute("bid", bid);
        model.addAttribute("bname", bname);
        model.addAttribute("Data", data);
        return "home";
    }

    @GetMapping("home")
    public String gethome(Model model) {
        return "login";
    }

    // teste de codigo
    @GetMapping("test1")
    public String getTestWeb1(Model model) {

        return "testDb";
    }

    @PostMapping("test1")
    public String getTestWeb1(
            @RequestParam(value = "name", required = false) String name,
            Model model) {

        FuncionarioRepository fr = new FuncionarioRepository(null);

        return "testDb";
    }

}
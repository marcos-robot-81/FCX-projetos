package com.fcx.fcx.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//import java.util.List;

// Importa classes do Dynamo
import com.fcx.fcx.dynamo.Funcionario;
import com.fcx.fcx.dynamo.FuncionarioRepository;

@Controller
public class Controle {

    private final FuncionarioRepository funcionarioRepository;

    public Controle(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

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
            @RequestParam(value = "bcargo", required = false) String bcargo, 
            @RequestParam(value = "anterior", required = false) String anterior,
            @RequestParam(value = "atual", required = false) String atual,
            @RequestParam(value = "proxima", required = false) String proxima,
            Model model) {
        
        Funcionario func = new Funcionario(bid, bname, bcargo);
        ListaMetados lm = new ListaMetados(funcionarioRepository, model);

        lm.criaLista(func);
        lm.SetData(date);
        lm.setPagina(anterior, atual, proxima);
        lm.setModel(name);

        return "home";
    }

    @GetMapping("home")
    public String gethome(Model model) {
        ListaMetados lm = new ListaMetados(funcionarioRepository, model);
        lm.criaLista(null);
        lm.SetData();
        lm.setPagina(null, null, null);
        lm.setModel();
        return "home";
    }

    @GetMapping("/Adicionar")
    public String getAdciona(Model model) {
        Funcionario func = new Funcionario();    
        model.addAttribute("idiqual", false);
        model.addAttribute("funcionario", func);

        return "adicionar";
    }
    
    @PostMapping("/Adicionar")
    public String getMethodName(
        @RequestParam(value = "matricula", required = false) String matricula,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "cargo", required = false) String cargo,
        Model model) {

        Funcionario func = new Funcionario(matricula, name, cargo);    
        func = funcionarioRepository.adcionaFuncionario(func);
        boolean idiqual = func.getMatricula().equals(matricula);


        model.addAttribute("idiqual", idiqual);
        model.addAttribute("funcionario", func);

        return "adicionar";
    }

    @PostMapping("/D")
    public void postMethodName(@RequestBody String entity) {
        System.out.println("Deletando: " + entity);
        funcionarioRepository.deletar(entity);
        System.out.println("Deletado!");
    }
    
    @GetMapping("/Deleta")
    public String getMethodName(Model model) {
        model.addAttribute("status", "");
        return "deleta";
    }

    @PostMapping("/Deleta")
    public String postMethodName(Model model,@RequestParam String matricula) {
        Funcionario func = funcionarioRepository.buscarPorId(matricula);
        model.addAttribute("funcionario", func);
        model.addAttribute("status", funcionarioRepository.deletar(matricula));
        return "deleta";
    }
    
    
    
    

}
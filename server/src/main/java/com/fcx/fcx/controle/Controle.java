package com.fcx.fcx.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.List;

// Importa suas classes do Dynamo
import com.fcx.fcx.dynamo.Funcionario;
import com.fcx.fcx.dynamo.FuncionarioRepository;

// Importa suas classes de teste 
//import com.fcx.fcx.DB.*;

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
            @RequestParam(value = "bid", required = false) String bid, // matricula
            @RequestParam(value = "bname", required = false) String bname,
            @RequestParam(value = "bquantidade", required = false) String bquantidade, // Agora será o Cargo
            Model model) {
        
        Funcionario func = new Funcionario();

        func.setMatricula(bid);      
        func.setNome(name);
        func.setCargo(bquantidade);

        

        Calendar hoje = Calendar.getInstance();
        String dataFormatada;
        
        if (date != null && !date.isEmpty()) {
            dataFormatada = date;
        } else {
            dataFormatada = hoje.get(Calendar.YEAR) + "-" + (hoje.get(Calendar.MONTH) + 1) + "-" + hoje.get(Calendar.DAY_OF_MONTH);
        }
        func.setData(dataFormatada);

        // (Só salva se tiver pelo menos um ID/Nome para não sujar o banco com vazios)
        //if (bid != null && !bid.isEmpty()) {
        //    funcionarioRepository.salvar(func);
        //    System.out.println("Funcionario Salvo no DynamoDB: " + func.getNome());
        //}

        model.addAttribute("message", "Bem-vindo! " + name);

        // ! Seus dados antigos de teste (Mantive para não quebrar sua tela)
        //GDados gd = new GDados();
        //ArrayList<Pessoa> pessoas = gd.GetDados("");

        // busca no banco DynamoDB por matricola (bid);
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        if(bid != null && !bid.isEmpty()){
            funcionarios = (ArrayList<Funcionario>) funcionarioRepository.buscarPorIdRL(bid);
        }else{
            funcionarios = (ArrayList<Funcionario>) funcionarioRepository.listarTodos();

        }    


        model.addAttribute("pessoas", funcionarios);
        //model.addAttribute("Funcionario" , funcionarios);
        
        // Passa de volta para a tela
        model.addAttribute("bquantidade", bquantidade);
        model.addAttribute("bid", bid);
        model.addAttribute("bname", bname);
        model.addAttribute("Data", dataFormatada);

        
        
        return "home";
    }

    @GetMapping("home")
    public String gethome(Model model) {
        return "login";
    }
}
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
            @RequestParam(value = "anterior", required = false) String anterior,
            @RequestParam(value = "atual", required = false) String atual,
            @RequestParam(value = "proxima", required = false) String proxima,
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
        model.addAttribute("message", "Bem-vindo! " + name);


        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        if(bid != null && !bid.isEmpty()){
            funcionarios = (ArrayList<Funcionario>) funcionarioRepository.buscarPorIdRL(bid);
        }else{
            funcionarios = (ArrayList<Funcionario>) funcionarioRepository.listarTodos();

        }
        
        // número de pajinas
        int Pagina =(int) (funcionarios.size() / 10)+1;
        if(  anterior != null && !anterior.isEmpty() ){
            if( Integer.parseInt(anterior) > 0 ){
                atual = anterior;
                proxima = String.valueOf( Integer.parseInt(anterior) +1 );
                anterior = String.valueOf( Integer.parseInt(anterior) -1 );

            }else{
                anterior = "0";
                atual = "1";
                proxima = "2";
            }
        }
        if(  proxima != null && !proxima.isEmpty() && atual== null ){
            if( Integer.parseInt(proxima) <= Pagina ){
                atual = proxima;
                anterior = String.valueOf( Integer.parseInt(proxima) -1 );
                proxima = String.valueOf( Integer.parseInt(proxima) +1 );
            }else{
                anterior = String.valueOf(Integer.parseInt(proxima) -2);
                atual = String.valueOf(Integer.parseInt(proxima) -1);
            }
        }

        anterior = ( anterior == null || anterior.isEmpty()) ? "0" : anterior;
        atual = ( atual == null || atual.isEmpty()) ? "1" : atual;
        proxima = ( proxima == null || proxima.isEmpty()) ? "2" : proxima;

        ArrayList<Funcionario> funcionarios2 = new ArrayList<>();

        for(int i = (Integer.parseInt(atual)-1)*10; i < (Integer.parseInt(atual)-1)*10+10 ;i++){
            if( i < funcionarios.size() ){
                funcionarios2.add(funcionarios.get(i));
            }else{
                funcionarios2.add(new Funcionario("| "," "," "," "));
            }
        }
        

        model.addAttribute("pessoas", funcionarios2);
        //model.addAttribute("Funcionario" , funcionarios);
        
        // Passa de volta para a tela
        model.addAttribute("bquantidade", bquantidade);
        model.addAttribute("bid", bid);
        model.addAttribute("bname", bname);
        model.addAttribute("Data", dataFormatada);
        model.addAttribute("Pagina", "");
        model.addAttribute("anterior", anterior);
        model.addAttribute("atual", atual);
        model.addAttribute("proxima", proxima);


        
        return "home";
    }

    @GetMapping("home")
    public String gethome(Model model) {
        return "login";
    }
}
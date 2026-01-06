package com.fcx.fcx.controle;

// configurações utilitarios
import org.springframework.ui.Model;
import java.util.ArrayList;
import com.fcx.fcx.dynamo.FuncionarioRepository;
import com.fcx.fcx.dynamo.Funcionario;
import java.util.Calendar;

public class ListaMetados {
    private FuncionarioRepository funcionarioRepository;
    private Model model;
    private ArrayList<Funcionario> funcionarios;
    private Funcionario func;
    private String pagina[] = { "", "", "" };
    private String dataFormatada;

    public ListaMetados(FuncionarioRepository funcionarioRepository, Model model) {
        this.funcionarioRepository = funcionarioRepository;
        this.model = model;
    }

    public void criaLista(Funcionario func) {
        if (func == null) {
            func = new Funcionario("", "", "");
        }
        this.func = func;

        String bid = func.getMatricula();
        // pega todos os funcionarios {
        if (bid != null && !bid.isEmpty()) {
            this.funcionarios = (ArrayList<Funcionario>) funcionarioRepository.buscarPorIdRL(bid);
        } else {
            this.funcionarios = (ArrayList<Funcionario>) funcionarioRepository.listarTodos();
        }
        // }

        String bname = func.getNome();
        // busca por nome {
        if (bname != null && !bname.isEmpty()) {
            ArrayList<Funcionario> f = new ArrayList<Funcionario>();
            for (Funcionario funcionario : this.funcionarios) {
                if (funcionario.getNome().toLowerCase().contains(bname.toLowerCase())) {
                    f.add(funcionario);
                }
            }
            this.funcionarios = f;
        }
        // }
    }

    public void setPagina(String anterior, String atual, String proxima) {

        int Pagina = (int) (this.funcionarios.size() / 10) + 1;

        if (anterior != null && !anterior.isEmpty()) {
            if (Integer.parseInt(anterior) > 0) {
                atual = anterior;
                proxima = String.valueOf(Integer.parseInt(anterior) + 1);
                anterior = String.valueOf(Integer.parseInt(anterior) - 1);

            } else {
                anterior = "0";
                atual = "1";
                proxima = "2";
            }
        }
        if (proxima != null && !proxima.isEmpty() && atual == null) {
            if (Integer.parseInt(proxima) <= Pagina) {
                atual = proxima;
                anterior = String.valueOf(Integer.parseInt(proxima) - 1);
                proxima = String.valueOf(Integer.parseInt(proxima) + 1);
            } else {
                anterior = String.valueOf(Integer.parseInt(proxima) - 2);
                atual = String.valueOf(Integer.parseInt(proxima) - 1);
            }
        }
        // }

        // valores padrão para paginas {
        anterior = (anterior == null || anterior.isEmpty()) ? "0" : anterior;
        atual = (atual == null || atual.isEmpty()) ? "1" : atual;
        proxima = (proxima == null || proxima.isEmpty()) ? "2" : proxima;
        // }

        // pega o funcionario e atribui a pagina {
        ArrayList<Funcionario> funcionarios2 = new ArrayList<>();
        for (int i = (Integer.parseInt(atual) - 1) * 10; i < (Integer.parseInt(atual) - 1) * 10 + 10; i++) {
            if (i < this.funcionarios.size()) {
                funcionarios2.add(this.funcionarios.get(i));
            } else {
                funcionarios2.add(new Funcionario("| ", " ", " ", " "));
            }
        }
        // }
        System.out.println("i:  atual: " + atual);
        this.pagina[0] = anterior;
        this.pagina[1] = atual;
        this.pagina[2] = proxima;
        this.funcionarios = funcionarios2;
    }

    public void SetData(String date) {
        // Data de hoje se não for informada {
        Calendar hoje = Calendar.getInstance();
        String dataFormatada;

        if (date != null && !date.isEmpty()) {
            dataFormatada = date;
        } else {
            dataFormatada = hoje.get(Calendar.YEAR) + "-" + (hoje.get(Calendar.MONTH) + 1) + "-"
                    + hoje.get(Calendar.DAY_OF_MONTH);
        }
        func.setData(dataFormatada);
    }

    public void SetData() {
        Calendar hoje = Calendar.getInstance();
        String dataFormatada;
        dataFormatada = hoje.get(Calendar.YEAR) + "-" + (hoje.get(Calendar.MONTH) + 1) + "-"
                + hoje.get(Calendar.DAY_OF_MONTH);
        func.setData(dataFormatada);
    }

    public Model setModel() {

        model.addAttribute("pessoas", this.funcionarios);
        model.addAttribute("message", "Bem-vindo! ");
        model.addAttribute("bcargo", func.getCargo());
        model.addAttribute("bid", func.getMatricula());
        model.addAttribute("bname", func.getNome());
        model.addAttribute("Data", func.getData());
        model.addAttribute("Pagina", "");
        model.addAttribute("anterior", this.pagina[0]);
        model.addAttribute("atual", this.pagina[1]);
        model.addAttribute("proxima", this.pagina[2]);

        System.out.println(this.pagina);
        return this.model;
    }

    public Model setModel(String name) {

        model.addAttribute("message", "Bem-vindo! " + name);
        model.addAttribute("pessoas", this.funcionarios);
        model.addAttribute("bcargo", func.getCargo());
        model.addAttribute("bid", func.getMatricula());
        model.addAttribute("bname", func.getNome());
        model.addAttribute("Data", func.getData());
        model.addAttribute("Pagina", "");
        model.addAttribute("anterior", pagina[0]);
        model.addAttribute("atual", pagina[1]);
        model.addAttribute("proxima", pagina[2]);
        return this.model;
    }

}

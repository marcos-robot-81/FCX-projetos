package com.fcx.fcx.dynamo;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class FuncionarioRepository {

    private final DynamoDbTable<Funcionario> tabela;

    public FuncionarioRepository(DynamoDbEnhancedClient enhancedClient) {
        // Conecta na tabela "Funcionario" usando a classe atualizada
        this.tabela = enhancedClient.table("Funcionario", TableSchema.fromBean(Funcionario.class));
    }

    public Funcionario adcionaFuncionario(Funcionario funcionario) {
        if (funcionario.getMatricula() == null || (funcionario.getMatricula().isEmpty() || funcionario.getMatricula().equals("-0") )  )  {
            funcionario.setMatricula(UUID.randomUUID().toString());

        }
        while( !buscarPorId(funcionario.getMatricula()).getMatricula().equals("-0") ) {
            funcionario.setMatricula(UUID.randomUUID().toString());
        }
        tabela.putItem(funcionario);
        return funcionario;
    }

    // 
    //public Funcionario salvar-F(Funcionario funcionario) {
    //    // Verifica se a MATRICULA está vazia
    //    if (funcionario.getMatricula() == null || funcionario.getMatricula().isEmpty()) {
    //        funcionario.setMatricula(UUID.randomUUID().toString());
    //    }
    //    
    //    tabela.putItem(funcionario);
    //    return funcionario;
    //}

    public Funcionario buscarPorId(String matricula) {
        Key key = Key.builder().partitionValue(matricula).build();

        Funcionario f = tabela.getItem(key);
        if(f == null){
            f = new Funcionario();
            f.setMatricula("-0");
            f.setNome("não econtrado");
            f.setData("Ou não exist");
            return f;
        }else{
        return f;
        }
    }

    public List<Funcionario> buscarPorIdRL(String matricula) {
        return ToList(buscarPorId(matricula));
    }

    public void deletar(String matricula) {
        Key key = Key.builder().partitionValue(matricula).build();
        tabela.deleteItem(key);
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        lista = (ArrayList<Funcionario>) tabela.scan().items().stream().collect(Collectors.toList());
        return lista;
    }

    private List<Funcionario> ToList( Funcionario fuc) {
        List<Funcionario> list = new ArrayList<>();
        list.add(fuc);
        return list;
    }
}
package com.fcx.fcx.DBtest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fcx.fcx.DB.*;

@SpringBootTest
public class DBt {
    
    @Test
    public void testGDados() {
        System.out.println("Iniciando teste de GDados...");
        GDados DB = new GDados();
        ArrayList<Pessoa> dados;


        try{
            dados = DB.GetDados("2000-01");

            System.out.println("Verificando se o terceiro ID é 3...");
            boolean test = dados.get(1).getId().equals("700");
            assertEquals(true, test);
        }catch(Exception e){
            e.printStackTrace();
            assertEquals(true, false);
        }
    }


}

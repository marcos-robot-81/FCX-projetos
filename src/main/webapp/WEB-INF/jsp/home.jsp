<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.fcx.fcx.DB.*" %>
<%   %>
 <html> 
 <head>
     <title> Home </title>
 </head>
    <body>
    <h2>${message}</h2>
    </br>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Quantidade</th>
        </tr>
        
        <%
            GDados gd = new GDados();
            ArrayList<Pessoa> pessoas = gd.GetDados(""); 
            for (int i = 1; i < pessoas.size(); i++) {
                Pessoa p = pessoas.get(i);
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNome() %></td>
            <td><%= p.getQuantidade() %></td>
        </tr>
        
        <%
            }
        %>

  

    </table>
    </body>
 </html>
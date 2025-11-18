<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.fcx.fcx.DB.*" %>
<%  %>
 <html> 
 <head>
     <title> Home </title>
 </head>
    <body>
    <header>
    <h1> Saida e entrada </h1>
    <h2>${message}</h2>
    </header>
    </br>
        <main>
        <form action="home" method="post">
            <input type="date" name="date" value="${Data}" min="2025-11-15" onchange="this.form.submit()"/>
        
            <table border="1">
                <%
                    ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) request.getAttribute("pessoas");
                    if (pessoas != null && !pessoas.isEmpty()) {
                        Pessoa cabecalho = pessoas.get(0);
                %>
                <tr>
                    <th><%= cabecalho.getId() %></th>
                    <th><%= cabecalho.getNome() %></th>
                    <th><%= cabecalho.getQuantidade() %></th>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td><input type="text" name="bid" placeholder="ID" value="${bid}" onchange="this.form.submit()"></td>
                    <td><input type="text" name="bname" placeholder="Nome" value="${bname}" onchange="this.form.submit()"></td>
                    <td><input type="text" name="bquantidade" placeholder="Quantidade" value="${bquantidade}" onchange="this.form.submit()"></td>
                </tr>
                <%
                    // 4. Iterar sobre a lista para exibir os dados.
                    for (int i = 1; i < pessoas.size(); i++ ) {
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

                <tr>
                <td><-- 0</td>
                <td> 1 </td>
                <td> 2 --></td>
                
                </tr>
            </table>
        </form>
        

    </main>
    </body>
 </html>

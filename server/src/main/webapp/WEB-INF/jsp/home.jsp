<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.fcx.fcx.DB.Pessoa, com.fcx.fcx.dynamo.Funcionario" %>
<%  %>
 <html> 
 <head>
     <title> Home </title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">

 </head>
    <body>
    <header>
    <h1> Saida e entrada </h1>
    <h2>${message}</h2>
    </header>
    </br>
        <main>
        <form action="home" method="post">
            <input id="date" type="date" name="date" value="${Data}" min="2025-11-15" onchange="this.form.submit()"/>
        
            <table border="1">
            
                <tr>
                    <th><P>Matricola</P></th>
                    <th> <P>Nome</P></th>
                    <th> <P> Cargo </P></th>
                </tr>
                
                <tr>
                    <td><input type="text" name="bid" placeholder="ID" value="${bid}" onchange="this.form.submit()"></td>
                    <td><input type="text" name="bname" placeholder="Nome" value="${bname}" onchange="this.form.submit()"></td>
                    <td><input type="text" name="bcargo" placeholder="cargo" value="${bcargo}" onchange="this.form.submit()"></td>
                </tr>
                <%
                    // 4. Iterar sobre a lista para exibir os dados.
                    ArrayList<Funcionario> pessoas = (ArrayList<Funcionario>) request.getAttribute("pessoas");
                    for (int i = 0; i < pessoas.size(); i++ ) {
                        Funcionario p = pessoas.get(i);
                %>
                <tr>
                    <td><%= p.getMatricula() %></td>
                    <td><%= p.getNome() %></td>
                    <td><%= p.getCargo() %></td>
                </tr>
                <%
                    }
                %>

                <tr id="footer-tabela" >
                <td> <p><-- <input type="submit" value="${anterior}" name="anterior" > </p> </td>
                <td> <p  name="atual" >${atual}</p> </td>
                <td> <p><input  type="submit" value="${proxima}" name="proxima" > --></p> </td>
                
                </tr >
            </table>
            <a href="/Adicionar"><input type="button" value="Adicionar Funcionario"></a>
        </form>
        

    </main>
    <footer></footer>
    </body>
 </html>

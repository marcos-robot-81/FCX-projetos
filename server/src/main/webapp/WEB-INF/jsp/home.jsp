<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.fcx.fcx.dynamo.Funcionario" %>
<%  %>
 <html> 
 <head>
     <title> Home </title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.home.css">
     <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

 </head>
    <body>
    <header>
        <h1>Controle de Funcionários</h1>
        <h2>${message}</h2>
    </header>
    
        <main>
        <form action="home" method="post">
            <div class="toolbar">
                <input id="date" type="date" name="date" value="${Data}" min="2025-11-15" onchange="this.form.submit()"/>
                <div class="actions">
                    <a href="/Adicionar" class="btn-primary">Adicionar Novo</a>
                    <a href="/Deleta" class="btn-danger">Deletar</a>
                </div>
            </div>
        
            <div class="table-container">
            <table class="styled-table">
            
                <tr>
                    <th>Matrícula</th>
                    <th>Nome</th>
                    <th>Cargo</th>
                </tr>
                
                <tr>
                    <td><input type="text" name="bid" placeholder="Filtrar Matrícula" value="${bid}" onchange="this.form.submit()"></td>
                    <td><input type="text" name="bname" placeholder="Filtrar Nome" value="${bname}" onchange="this.form.submit()"></td>
                    <td><input type="text" name="bcargo" placeholder="Filtrar Cargo" value="${bcargo}" onchange="this.form.submit()"></td>
                </tr>
                <%
                    // 4. Iterar sobre a lista para exibir os dados.
                    ArrayList<Funcionario> Funcionario = (ArrayList<Funcionario>) request.getAttribute("pessoas");
                    for (int i = 0; i < Funcionario.size(); i++ ) {
                        Funcionario p = Funcionario.get(i);
                %>
                <tr>
                    <td><%= p.getMatricula() %></td>
                    <td><%= p.getNome() %></td>
                    <td><%= p.getCargo() %></td>
                </tr>
                <%
                    }
                %>

            </table>
            </div>
            
            <div class="pagination">
                <input type="submit" value="${anterior}" name="anterior" class="btn-page">
                <span class="current-page">${atual}</span>
                <input type="submit" value="${proxima}" name="proxima" class="btn-page">
            </div>
        </form>
        

    </main>
    <footer></footer>
    </body>
 </html>

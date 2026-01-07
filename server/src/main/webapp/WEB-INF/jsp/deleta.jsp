<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.fcx.fcx.dynamo.Funcionario" %>
<% 
        Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
        String status = (String) request.getAttribute("status");
        
%>
<html>
    <head> 
    <title> Deleta </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.deleta.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">


    </head>
    <body>
    <header>
        <h1>Excluir Funcionário</h1>
    </header>
    <main>
    <form action="Deleta" method="post" class="form-styled">
        <% if(status.equals("deletado")) { %>
            <div class="alert warning">
                <h2> Funcionário <% if(!funcionario.getMatricula().equals("-0")) { 
                    %>removido com sucesso </h2>
                    <p> <strong>Matrícula:</strong> ${funcionario.matricula} </p>
                    <p> <strong>Nome:</strong> ${funcionario.nome} </p>
                    <p> <strong>Cargo:</strong> ${funcionario.cargo} </p>
                    <% }else { %>
                     não encontrado </h2>
                     <p>Verifique a matrícula informada.</p>
                     <% } %>
            </div>
        <% } %>

        <div class="form-group">
            <label>Matrícula para exclusão</label>
            <input type="text" name="matricula" value="" placeholder="Digite a matrícula" />
        </div>
        <div class="form-actions">
            <a href="/home" class="btn-secondary">Voltar</a>
            <input type="submit" value="Deletar" class="btn-danger"/>
        </div>
    </form>

    </main>
    <footer>

    </footer>
    </body>
</html>

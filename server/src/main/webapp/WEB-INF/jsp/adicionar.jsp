<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.fcx.fcx.dynamo.Funcionario" %>

<html>
<head>
    <title> Adicionar Funcionario </title>

</head>
    <body>
    <header>
    <h1> Adicionar Funcionario </h1>
    </header>

    <main>
    <a href="/home"><input type="button" value="Voltar para lista"></a>
    <%
        Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
        Boolean idiqual = (Boolean) request.getAttribute("idiqual");
    %>
    <% if(funcionario.getMatricula() != null) { %>
        <% if(idiqual == true){ %>
            <h2> Funcionario adicionado com sucesso! </h2>
            <p>Dados do funcionario:</p>
            <p> Matricula: ${funcionario.getMatricula()} </p>
            <p> Nome: ${funcionario.getNome()} </p>
            <p> Cargo: ${funcionario.getCargo()} </p> 
        <% }else{ %>
            <h2> Funcionario foi adicionado com id : <%= funcionario.getMatricula() %> </h2>
    <% }}else{ %>
        <h2> Preencha os campos para adicionar um novo funcionario </h2>
    <% }%>

    <form action="Adicionar" method="post">
        Matricula: <input type="text" name="matricula" value=""/> <br/>
        Nome: <input type="text" name="name" value=""/> <br/>
        Cargo: <input type="text" name="cargo" value=""/> <br/>
        <input type="submit" value="Adicionar"/>
    </main>
    <footer></footer>
    </body>
</html>
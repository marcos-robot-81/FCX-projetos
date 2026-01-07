<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.fcx.fcx.dynamo.Funcionario" %>

<html>
<head>
    <title> Adicionar Funcionario </title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.adicionar.css">
     <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

</head>
    <body>
    <header>
        <h1> Adicionar Funcionário </h1>
    </header>

    <main>
    <%
        Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
        Boolean idiqual = (Boolean) request.getAttribute("idiqual");
    %>
    <% if(funcionario.getMatricula() != null) { %>
        <% if(idiqual == true){ %>
            <div class="alert success">
                <h2> Funcionário adicionado com sucesso! </h2>
                <p><strong>Matrícula:</strong> ${funcionario.getMatricula()} </p>
                <p><strong>Nome:</strong> ${funcionario.getNome()} </p>
                <p><strong>Cargo:</strong> ${funcionario.getCargo()} </p> 
            </div>
        <% }else{ %>
            <div class="alert info">
                <h2> Funcionário foi adicionado com id : <%= funcionario.getMatricula() %> </h2>
            </div>
    <% }}else{ %>
        <h2 class="subtitle"> Preencha os campos abaixo </h2>
    <% }%>

    <form action="Adicionar" method="post" class="form-styled">
        <div class="form-group">
            <label>Matrícula</label>
            <input type="text" name="matricula" value="" placeholder="Ex: 12345"/>
        </div>
        <div class="form-group">
            <label>Nome</label>
            <input type="text" name="name" value="" placeholder="Nome completo"/>
        </div>
        <div class="form-group">
            <label>Cargo</label>
            <input type="text" name="cargo" value="" placeholder="Cargo do funcionário"/>
        </div>
        <div class="form-actions">
            <a href="/home" class="btn-secondary">Voltar</a>
            <input type="submit" value="Salvar" class="btn-primary"/>
        </div>
    </form>
    </main>
    <footer></footer>
    </body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Login </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.login.css?v=1">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="login-container">
        <h2>Bem-vindo</h2>
        <form action="home" method="post">
            <div class="form-group">
                <label>Usuário</label>
                <input type="text" name="name" placeholder="Digite seu usuário" required/>
            </div>
            <div class="form-group">
                <label>Senha</label>
                <input type="password" name="Senha" placeholder="Digite sua senha" required/>
            </div>
            <input type="submit" value="Entrar"/>
        </form>
    </div>
</body>
</html>
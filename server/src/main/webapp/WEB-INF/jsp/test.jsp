<%@
 page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.*"
 %>
<html>
<head>
    <title> Minha primeira página JSP </title>
<body>
    <h1> eu estou aqui </h1>
    <% System.out.println(" nova");  %>
    <p> Olá JSP! </p>
    <p> ------------------------------------ </p>
    <p> teste de obte dados de fomularios  </p>
    <form action="">
        name: <input type="text" name="nome" value="10"/> <br/>
        <input type="submit" value="Enviar"/>
    </form>
    <%= request.getParameter("nome") %>
    <%= "Finn"  %>
    <form action="home" method="post">
        name: <input type="text" name="name" value=""/> <br/>
        <input type="submit" value="Enviar"/>
    </form>
</body>
 </html>
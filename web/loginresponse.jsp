<%-- 
    Document   : response
    Created on : 10/12/2016, 18:40:05
    Author     : Heitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema AgroProv</title>

    </head>
    <body>

    <center><table cellspacing="10" >
            <tr>
                <td><center> <img src="imagens/log3.png"/> </center></td>
            </tr> 
        </table></center>

    <jsp:useBean id="beanlogin" scope="session" class="org.classes.Login" />
    <jsp:setProperty name="beanlogin" property="senha" />
    <jsp:setProperty name="beanlogin" property="login" />



    <table cellspacing="10">
        <tr>
            <td><right><img src="imagens/logo.png"/> </right></td>
    <td>Bem Vindo <jsp:getProperty name="beanlogin" property="login" />!</td>


</tr>

<tr><td>Bem Vindo !<p> 
            Login :<jsp:getProperty name="beanlogin" property="login" /> 
            Senha :<jsp:getProperty name="beanlogin" property="senha" /> 
    </td></tr>

</table>


</body>
</html>

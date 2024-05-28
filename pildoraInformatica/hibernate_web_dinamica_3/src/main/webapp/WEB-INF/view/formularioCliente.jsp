<%--
  Created by IntelliJ IDEA.
  User: vipera
  Date: 18/4/24
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Formulario cliente nuevo</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilo.css"/>
</head>
<body>
<h1>Formulario cliente nuevo</h1>
<%--
modelAttribute: es para asociar el cliente creado en el controlador que invoca a este jsp, el nombre dado en el addAtributte
action: es al controlador que va a llamar, PostMapping
method: post porque estamos añadiendo contenido y va un cliente en el paquete
--%>
<form:form action="insertarCliente" modelAttribute="clienteForm" method="post" >
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Nombre</td>
            <td><form:input path="nombre"/></td>
        </tr>
        <tr>
            <td>Apellidos</td>
            <td><form:input path="apellido"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <%--Colspan es para que unifique dos celdas en una--%>
            <td colspan="2"><input type="submit" value="Aceptar"></td>
        </tr>
    </table>
</form:form>

</body>
</html>

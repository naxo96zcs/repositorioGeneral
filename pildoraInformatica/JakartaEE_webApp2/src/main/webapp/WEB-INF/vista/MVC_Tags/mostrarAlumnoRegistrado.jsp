<%--
  Created by IntelliJ IDEA.
  User: vipera
  Date: 6/3/24
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mostrando alumno</title>
    <!-- utilizado para linkear el css a la pagina .JSP, igual que la foto-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/estilo.css">
</head>
<body>

<h1>El alumno se registró <br/></h1>

<strong>${alumno.nombre} ${alumno.apellido}</strong> con edad <strong>${alumno.edad}</strong><br/>
Email: <strong>${alumno.email}</strong> <br/>
Codigo postal: <strong>${alumno.codigoPostal}</strong> <br/>
Asignatura:<strong>${alumno.optativa}</strong> <br/>
Evaluacion:<strong>${alumno.evalucion}</strong> <br/>
Idiomas escogidos:<strong>${alumno.idiomas}</strong> <br/>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: vipera
  Date: 4/3/24
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--
<form action="anadirInfoProcesoFormulario" method="get">
  <input type="text" name="nombre_alumno">
  <input type="submit">
</form>
-->

<form action="anadirInfoProcesoFormularioRequestParam" method="get">
    <input type="text" name="nombre_alumno">
    <input type="submit">
</form>
<br>
<form action="ruta-relativa/anadirInfoProcesoFormularioRequestParam" method="get">
    <input type="text" name="nombre_alumno">
    <input type="submit" value="ruta relativa">
</form>

</body>
</html>

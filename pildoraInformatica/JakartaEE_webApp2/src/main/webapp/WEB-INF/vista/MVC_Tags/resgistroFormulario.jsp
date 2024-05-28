<%--
  Created by IntelliJ IDEA.
  User: vipera
  Date: 6/3/24
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Una especie de dependencia para utilizar los forms con elementos de Spring-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Formulario registro</title>
</head>
<body>
<h1>Mostrar formulario</h1>

<!--
    Importante el uso de "form:form" para que haga ref a los forms de spring,
     el siguiente meteodo se encarga de procesar la informacion
    -modelAttribute: nombre con el que se hace referencia en el action
-->
<form:form action="mostrarAlumno" modelAttribute="alumno">
    <!-- la path es el valor que tiene, el atributo nombre en "AlumnoMVCTags", necesario para los getter y setters -->
    Nombre: <form:input path="nombre"/>
    <form:errors path="nombre" style="color:red" />
    <br/><br/>
    Apellido: <form:input path="apellido"/>
    <form:errors path="apellido" style="color:red" />
    <br/><br/>
    Edad: <form:input path="edad"/>
    <form:errors path="edad" style="color:red" />
    <br/><br/>
    Email: <form:input path="email"/>
    <form:errors path="email" style="color:red" />
    <br/><br/>
    Codigo postal: <form:input path="codigoPostal"/>
    <form:errors path="codigoPostal" style="color:red" />

    <br/><br/><br/>
    Asignatura optativa:
    <br/>
    <!-- seleccion en una lista de un elemento, si se quiere seleccionar mas con crtl + click y multiple a true -->
    <form:select path="optativa">
        <form:option value="Diseño" label="Diseño"/>
        <form:option value="Desarrollo" label="Desarrollo"/>
        <form:option value="Arquitectura" label="Arquitectura"/>
    </form:select>
    <br/><br/>

    <h3>Escoja evaluacion</h3>
    <!-- Seleccion Radiobutton -->
    <form:radiobutton path="evalucion" value="Anual"/>Anual
    <form:radiobutton path="evalucion" value="Por trabajos"/>Por trabajos<br/>
    <form:radiobutton path="evalucion" value="Por semestre"/>Por semestre<br/>
    <br/><br/>

    <h3>Idiomas</h3>
    <!-- Seleccion checkboxes -->
    Frances<form:checkbox path="idiomas" value="Frances"/><br/>
    Aleman<form:checkbox path="idiomas" value="Aleman"/><br/>
    Chino<form:checkbox path="idiomas" value="Chino"/><br/>
    <br/><br/><br/>
    <input type="submit" value="Registrar">

</form:form>

</body>
</html>

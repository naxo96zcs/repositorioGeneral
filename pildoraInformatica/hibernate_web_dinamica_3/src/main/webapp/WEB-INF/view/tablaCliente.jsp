<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lista clientes</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilo.css"/>
</head>
<body>
<h1>Hemos llegado a la lista de clientes!</h1>
<table>
    <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <td>Modificar</td>
        <td>Eliminar</td>
    </tr>
    <c:forEach var="clienteTenp" items="${clienteList}">

        <%--jsp tag, que carga en la variable link actualizar la url del controlador que se llama abajo y como parametro
         a esa url le a�ade la id del cliente con el nombre de "clienteID"--%>
        <c:url var="linkActualizar" value="/cliente/muestraFormularioActualizar">
            <c:param name="clienteId" value="${clienteTenp.id}"></c:param>
        </c:url>
        <c:url var="linkEliminar" value="/cliente/eliminarCliente">
            <c:param name="clienteId" value="${clienteTenp.id}"></c:param>
        </c:url>
    <tr>
        <td>${clienteTenp.nombre}</td>
        <td>${clienteTenp.apellido}</td>
        <td>${clienteTenp.email}</td>
        <%--Boton para modificar el cliente, esta variable contiene una url la cual llevara a un controllador que cargar�
        el cliente y con sus campos para ser modificados--%>
        <td><a href="${linkActualizar}"><input type="button" value="Modificar"></a></td>
        <%--Con el 'onclick' con una venta emergente para confirmar el borrado, configruar los botones de aceptacion o
        negacion. si el cliente cancela retorna false--%>
        <td><a href="${linkEliminar}"><input type="button" value="Eliminar"
                                             onclick="if(!(confirm('Desea borrar al cliente ${clienteTenp.nombre} ${clienteTenp.apellido}?'))) return false"></a></td>
    </tr>
    </c:forEach>
</table>
<h2>Opciones</h2>
<%--Tiene que ser una referencia al conrtolador la href en onclick--%>
<input type="button" value="Agregar cliente" onclick="window.location.href='formularioCliente'">


</body>
</html>

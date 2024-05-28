<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <!-- utilizado para linkear el css a la pagina .JSP, igual que la foto-->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/estilo.css">
</head>
<body>
<h1>Funsiona pls <img src="${pageContext.request.contextPath}/resources/phill.jpg"/></h1>
<!-- No poner el .jsp directamente mejor al metodo que lo obtiene, sino al metodo del controlador su requestMapping-->
<a href="muestraFormulario"> Formulario</a>
<br/>
<br/>

<br/>
<a href="alumno/resgistroFormulario"> Registro</a>
<!--
Estos hacen lo mismo pero su metodo es diferente:
<a href="alumno/resgistroFormulario2"> Registro con la etiqueta @ModelAttribute</a>
-->
<br/>


<!--
Los recursos deben especificarse en el beans del "spring_mvc_servlet.xml"
-pageContext.request.contextPath: devuelve la raiz de sitio web, y despues se busca la imagen
-La imagen debe estar dentro de la carpeta de web app si se carga desde el .JSP
-->





</body>
</html>
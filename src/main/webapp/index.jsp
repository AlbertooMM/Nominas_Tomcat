<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestión de Nóminas</title>
    <link rel="stylesheet" href="./views/CSS/index.css">
</head>

<body>
<div class="contenedor">
    <h1>Gestión de Nóminas</h1>
    <p>Selecciona la operación que deseas realizar.</p>

    <div class="menu">
        <a href="<%= request.getContextPath() %>/nominas?accion=listar">
            1. Mostrar todos los empleados
        </a>

        <a href="<%= request.getContextPath() %>/views/consultar-salario.jsp">
            2. Consultar salario de un empleado
        </a>

        <a href="<%= request.getContextPath() %>/views/modificar-empleado.jsp">
            3. Modificar datos de un empleado
        </a>

    </div>
</div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Laboral.Modelo.EmpleadoModel" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de empleados</title>
    <link rel="stylesheet" href="./views/CSS/empleados.css">


</head>

<body>
<div class="contenedor">
    <h1>Listado de empleados</h1>

    <%
        List<EmpleadoModel> empleados =
                (List<EmpleadoModel>) request.getAttribute("empleados");
    %>

    <% if (empleados == null) { %>
    <p>No se han recibido los datos de los empleados. Accede a esta página desde el menú.</p>
    <% } else if (empleados.isEmpty()) { %>
    <p>No hay empleados en la base de datos.</p>
    <% } else { %>

    <div class="tabla-contenedor">
        <table>
            <thead>
            <tr>
                <th>Nombre</th>
                <th>DNI</th>
                <th>Sexo</th>
                <th>Categoría</th>
                <th>Años trabajados</th>
            </tr>
            </thead>

            <tbody>
            <% for (EmpleadoModel e : empleados) { %>
            <tr>
                <td><%= e.getNombre() %>
                </td>
                <td><%= e.getDni() %>
                </td>
                <td><%= e.getSexo() %>
                </td>
                <td><%= e.getCategoria() %>
                </td>
                <td><%= e.getAnyos() %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <% } %>

    <a href="<%= request.getContextPath() %>/index.jsp">
        Volver al menú
    </a>
</div>
</body>
</html>

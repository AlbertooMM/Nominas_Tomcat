<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Laboral.Modelo.EmpleadoModel" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de empleados</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            padding: 30px 15px;
            color: #1e293b;
        }

        .contenedor {
            max-width: 1000px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 12px;
        }

        h1 { color: #1d4ed8; }

        .tabla-contenedor { overflow-x: auto; }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #e2e8f0;
            padding: 12px;
            text-align: left;
        }

        th { background: #2563eb; color: white; }
        tr:nth-child(even) { background: #f8fafc; }

        .volver {
            display: inline-block;
            margin-top: 20px;
            color: #2563eb;
        }
    </style>
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
                <td><%= e.getNombre() %></td>
                <td><%= e.getDni() %></td>
                <td><%= e.getSexo() %></td>
                <td><%= e.getCategoria() %></td>
                <td><%= e.getAnyos() %></td>
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

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Resultado de la operación</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            padding: 40px 20px;
            color: #1e293b;
        }

        .contenedor {
            max-width: 500px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 5px 20px #00000012;
        }

        h1 { color: #1d4ed8; }
        p { font-size: 16px; line-height: 1.5; }

        .volver {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 16px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }
        .volver:hover { background: #1d4ed8; }
    </style>
</head>

<body>
<div class="contenedor">
    <h1>Resultado de la operación</h1>

    <%
        // Recibimos los objetos enviados desde el Servlet
        Laboral.Modelo.EmpleadoModel empleado = (Laboral.Modelo.EmpleadoModel) request.getAttribute("empleado");
        Laboral.Modelo.NominaModel nomina = (Laboral.Modelo.NominaModel) request.getAttribute("nomina");

        if (empleado != null) {
    %>
    <p><strong>Nombre:</strong> <%= empleado.getNombre() %></p>
    <p><strong>DNI:</strong> <%= empleado.getDni() %></p>
    <p><strong>Categoría:</strong> <%= empleado.getCategoria() %></p>
    <p><strong>Años trabajados:</strong> <%= empleado.getAnyos() %></p>

    <hr style="border: 0; border-top: 1px solid #cbd5e1; margin: 20px 0;">

    <% if (nomina != null) { %>
    <p><strong>Sueldo calculado:</strong> <span style="color: #16a34a; font-size: 20px; font-weight: bold;"><%= nomina.getSueldo() %> €</span></p>
    <% } else { %>
    <p style="color: #dc2626;">No se encontró una nómina registrada para este DNI.</p>
    <% } %>

    <%
    } else {
    %>
    <p style="color: #dc2626;">No se ha encontrado ningún empleado con el DNI proporcionado.</p>
    <%
        }
    %>

    <br>
    <a class="volver" href="<%= request.getContextPath() %>/index.jsp">
        Volver al menú
    </a>
</div>
</body>
</html>
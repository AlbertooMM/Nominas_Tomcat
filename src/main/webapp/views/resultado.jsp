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
        }

        .contenedor {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
        }

        h1 { color: #1d4ed8; }

        .mensaje {
            padding: 15px;
            background: #eff6ff;
            border-radius: 8px;
            overflow-wrap: anywhere;
        }

        a { display: inline-block; margin-top: 15px; }
    </style>
</head>

<body>
<div class="contenedor">
    <h1>Resultado de la operación</h1>

    <div class="mensaje">
        <p>${requestScope.mensaje}</p>
    </div>

    <a href="<%= request.getContextPath() %>/index.jsp">
        Volver al menú
    </a>
</div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Recalcular sueldo</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            padding: 40px 20px;
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

        label { display: block; margin: 15px 0 8px; }

        input {
            width: 100%;
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
        }

        button {
            margin-top: 20px;
            padding: 12px 20px;
            border: 0;
            border-radius: 6px;
            background: #2563eb;
            color: white;
            cursor: pointer;
        }

        .volver { display: block; margin-top: 20px; text-decoration: none; color: #2563eb; }
        .volver:hover { text-decoration: underline; }
    </style>
</head>

<body>
<div class="contenedor">
    <h1>Recalcular sueldo</h1>

    <form action="<%= request.getContextPath() %>/nominas" method="post">
        <input type="hidden" name="accion" value="recalcular">

        <label for="dni">DNI del empleado</label>
        <input type="text" id="dni" name="dni" maxlength="20" required>

        <button type="submit">Recalcular sueldo</button>
    </form>

    <a class="volver" href="<%= request.getContextPath() %>/index.jsp">
        Volver al menú
    </a>
</div>
</body>
</html>
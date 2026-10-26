<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modificar empleado</title>

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
        }

        h1 { color: #1d4ed8; }

        label { display: block; margin: 18px 0 8px; }

        input, select {
            width: 100%;
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            background: white;
        }

        button {
            margin-top: 22px;
            padding: 12px 20px;
            border: 0;
            border-radius: 6px;
            background: #2563eb;
            color: white;
            cursor: pointer;
        }

        .volver { display: block; margin-top: 20px; }
    </style>
</head>

<body>
<div class="contenedor">
    <h1>Modificar empleado</h1>

    <form action="${pageContext.request.contextPath}/nominas"
          method="post">

        <input type="hidden" name="accion" value="modificar">

        <label for="dni">DNI del empleado</label>
        <input type="text" id="dni" name="dni"
               maxlength="20" required>

        <label for="campo">Dato que deseas modificar</label>
        <select id="campo" name="campo" required>
            <option value="nombre">Nombre</option>
            <option value="sexo">Sexo</option>
            <option value="categoria">Categoría</option>
            <option value="anyos">Años trabajados</option>
        </select>

        <label for="valor">Nuevo valor</label>
        <input type="text" id="valor" name="valor" required>

        <button type="submit">Guardar cambios</button>
    </form>

    <a class="volver"
       href="${pageContext.request.contextPath}/index.jsp">
        ← Volver al menú
    </a>
</div>
</body>
</html>

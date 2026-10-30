<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modificar empleado</title>
    <link rel="stylesheet" href="./CSS/modificar.css">

</head>

<body>
<div class="contenedor">
    <h1>Modificar empleado</h1>

    <form action="<%= request.getContextPath() %>/nominas" method="post">
        <input type="hidden" name="accion" value="modificar">

        <label for="dni">DNI del empleado</label>
        <input type="text" id="dni" name="dni" maxlength="20" required>

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

    <a class="volver" href="<%= request.getContextPath() %>/index.jsp">
        Volver al menú
    </a>
</div>
</body>
</html>
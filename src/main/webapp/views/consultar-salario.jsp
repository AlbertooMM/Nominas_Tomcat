<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Consultar salario</title>
    <link rel="stylesheet" href="./CSS/consulta.css">

</head>

<body>
<div class="contenedor">
    <h1>Consultar salario</h1>

    <form action="<%= request.getContextPath() %>/nominas" method="post">
        <input type="hidden" name="accion" value="consultarSalario">

        <label for="dni">DNI del empleado</label>
        <input type="text" id="dni" name="dni" maxlength="20" required>

        <button type="submit">Consultar salario</button>
    </form>

    <a class="volver" href="<%= request.getContextPath() %>/index.jsp">
        Volver al menú
    </a>
</div>
</body>
</html>
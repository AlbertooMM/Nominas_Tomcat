<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestión de Nóminas</title>

    <style>
        * { box-sizing: border-box; }

        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            margin: 0;
            padding: 40px 20px;
            color: #1e293b;
        }

        .contenedor {
            max-width: 700px;
            margin: auto;
            background: white;
            padding: 35px;
            border-radius: 14px;
            box-shadow: 0 5px 20px #00000012;
        }

        h1 { text-align: center; color: #1d4ed8; }
        p { color: #64748b; }

        .menu {
            display: grid;
            gap: 12px;
            margin-top: 25px;
        }

        .menu a {
            display: block;
            padding: 16px;
            border-radius: 8px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            transition: background .2s;
        }

        .menu a:hover { background: #1d4ed8; }
        .salir { background: #475569 !important; }
    </style>
</head>

<body>
<div class="contenedor">
    <h1>Gestión de Nóminas</h1>
    <p>Selecciona la operación que deseas realizar.</p>

    <div class="menu">
        <a href="${pageContext.request.contextPath}/nominas?accion=listar">
            1. Mostrar todos los empleados
        </a>

        <a href="${pageContext.request.contextPath}/views/consultar-salario.jsp">
            2. Consultar salario de un empleado
        </a>

        <a href="${pageContext.request.contextPath}/views/modificar-empleado.jsp">
            3. Modificar datos de un empleado
        </a>

        <a href="${pageContext.request.contextPath}/nominas?accion=formularioRecalcular">
            4. Recalcular sueldo de un empleado
        </a>

        <a href="${pageContext.request.contextPath}/nominas?accion=recalcularTodos">
            5. Recalcular sueldos de todos los empleados
        </a>

        <a href="${pageContext.request.contextPath}/nominas?accion=backup">
            6. Realizar copia de seguridad
        </a>
    </div>
</div>
</body>
</html>

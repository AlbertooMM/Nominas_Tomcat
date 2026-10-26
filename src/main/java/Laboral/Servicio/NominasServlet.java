package Laboral.Servicio;

import Laboral.Dao.EmpleadoDao;
import Laboral.Dao.NominasDao;
import Laboral.Modelo.EmpleadoModel;
import Laboral.Modelo.NominaModel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NominasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmpleadoDao empleadoDao;
    private NominasDao nominasDao;

    @Override
    public void init() {
        empleadoDao = new EmpleadoDao();
        nominasDao = new NominasDao();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        try {
            switch (accion) {
                case "listar":
                    listarEmpleados(request, response);
                    break;

                case "consultarSalario":
                    consultarSalario(request, response);
                    break;

                case "modificar":
                    modificarEmpleado(request, response);
                    break;

                case "recalcular":
                    recalcularSueldo(request, response);
                    break;

                case "recalcularTodos":
                    recalcularTodosSueldos(request, response);
                    break;

                case "backup":
                    realizarBackup(request, response);
                    break;

                default:
                    response.sendError(
                            HttpServletResponse.SC_NOT_FOUND,
                            "Acción no encontrada: " + accion
                    );
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error procesando la acción: " + accion, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // --- MÉTODOS DE CONTROL ---

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<EmpleadoModel> empleados = empleadoDao.obtenerTodos();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/views/empleados.jsp").forward(request, response);
    }

    private void consultarSalario(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String dni = request.getParameter("dni");
        EmpleadoModel empleado = empleadoDao.buscarPorDni(dni);
        NominaModel nomina = nominasDao.buscarPorDni(dni);

        request.setAttribute("empleado", empleado);
        request.setAttribute("nomina", nomina);

        request.getRequestDispatcher("/views/resultado.jsp").forward(request, response);
    }

    private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String dni = request.getParameter("dni");
        String campo = request.getParameter("campo");
        String valor = request.getParameter("valor");

        EmpleadoModel empleado = empleadoDao.buscarPorDni(dni);

        if (empleado != null) {
            switch (campo) {
                case "nombre":
                    empleado.setNombre(valor);
                    break;
                case "sexo":
                    if (valor != null && !valor.isEmpty()) {
                        empleado.setSexo(valor.charAt(0));
                    }
                    break;
                case "categoria":
                    empleado.setCategoria(Integer.parseInt(valor));
                    break;
                case "anyos":
                    empleado.setAnyos(Integer.parseInt(valor));
                    break;
            }
            empleadoDao.actualizar(empleado);
            // Opcional: recalcular el sueldo automáticamente al modificar datos del empleado
            nominasDao.recalcularYActualizar(dni);
        }

        response.sendRedirect(request.getContextPath() + "/nominas?accion=listar");
    }

    private void recalcularSueldo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String dni = request.getParameter("dni");
        nominasDao.recalcularYActualizar(dni);
        response.sendRedirect(request.getContextPath() + "/nominas?accion=listar");
    }

    private void recalcularTodosSueldos(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        nominasDao.recalcularTodos(empleadoDao);
        response.sendRedirect(request.getContextPath() + "/nominas?accion=listar");
    }

    private void realizarBackup(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Aquí puedes añadir tu lógica de copia de seguridad o dejar la redirección
        response.sendRedirect(request.getContextPath() + "/nominas?accion=listar");
    }
}
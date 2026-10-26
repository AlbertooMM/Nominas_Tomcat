
package Laboral.Servicio;

import Laboral.Dao.EmpleadoDao;
import Laboral.Modelo.EmpleadoModel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NominasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EmpleadoDao empleadoDao;

    @Override
    public void init() throws ServletException {
        empleadoDao = new EmpleadoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");


        if ("listar".equals(accion)) {
            try {
                List<EmpleadoModel> empleados =
                        empleadoDao.obtenerTodos();

                System.out.println(
                        "Empleados enviados al JSP: " + empleados.size()
                );

                request.setAttribute("empleados", empleados);

                request.getRequestDispatcher(
                        "/views/empleados.jsp"
                ).forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();

                throw new ServletException(
                        "Error al obtener los empleados", e
                );
            }
        }
        else {
            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Acción no encontrada"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}

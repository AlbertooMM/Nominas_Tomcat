package Laboral.Servicio;

import Laboral.Dao.EmpleadoDao;
import Laboral.Dao.NominasDao;
import Laboral.Modelo.EmpleadoModel;
import Laboral.Modelo.NominaModel;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class CalculaNominas {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("empleados.txt"))) {
                bw.write("James Gosling, 32000032G, m, 4, 7");
                bw.newLine(); // añade linea vacia
                bw.write("Ada Lovelace, 32000031R, f,1,0");

            } catch (IOException e) {
                System.out.println("Error writing file.");
            }
            leoArchivo();

            try (FileWriter myWriter = new FileWriter("empleados.txt")) {
                myWriter.write("James Gosling, 32000032G, m, 9, 7\n Ada Lovelace, 32000031R, f,1,2");
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
            leoArchivo();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        menu();
    }

    public static void leoArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("empleados.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        EmpleadoDao empleadoDao = new EmpleadoDao();
        NominasDao nominasDao = new NominasDao();
        int opcion;

        do {
            System.out.println();
            System.out.println("          MENÚ DE NÓMINAS");
            System.out.println("1. Mostrar todos los empleados");
            System.out.println("2. Mostrar salario de un empleado");
            System.out.println("3. Modificar datos de un empleado"); // 5.3
            System.out.println("4. Recalcular y actualizar sueldo de un empleado"); // 5.4
            System.out.println("5. Recalcular y actualizar sueldos de todos"); // 5.5
            System.out.println("6. Realizar copia de seguridad en ficheros"); // 5.6
            System.out.println("0. Salir");
            System.out.println("");
            System.out.print("Introduce una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    mostrarEmpleados(empleadoDao);
                    break;
                case 2:
                    mostrarSalario(empleadoDao, nominasDao, sc);
                    break;
                case 3:
                    modificarEmpleadoMenu(empleadoDao, nominasDao, sc); // 5.3
                    break;
                case 4:
                    recalcularSueldoEmpleado(empleadoDao, nominasDao, sc); // 5.4
                    break;
                case 5:
                    recalcularTodosSueldos(empleadoDao, nominasDao); // 5.5
                    break;
                case 6:
                    realizarCopiaSeguridad(empleadoDao, nominasDao); // 5.6
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    public static void modificarEmpleadoMenu(EmpleadoDao empleadoDao, NominasDao nominasDao, Scanner sc) {
        System.out.println("\n========== MODIFICAR EMPLEADO ==========");
        System.out.print("Introduce el DNI del empleado a modificar: ");
        String dni = sc.nextLine();

        EmpleadoModel emp = empleadoDao.buscarPorDni(dni);
        if (emp == null) {
            System.out.println("No existe un empleado con ese DNI.");
            return;
        }

        System.out.println("Empleado encontrado: " + emp.getNombre());
        System.out.println("1. Modificar Nombre");
        System.out.println("2. Modificar Sexo");
        System.out.println("3. Modificar Categoría (actualiza sueldo automáticamente)");
        System.out.println("4. Modificar Años trabajados (actualiza sueldo automáticamente)");
        System.out.print("Elige qué deseas modificar: ");

        int op = Integer.parseInt(sc.nextLine());
        boolean actualizado = false;

        switch (op) {
            case 1:
                System.out.print("Nuevo nombre: ");
                emp.setNombre(sc.nextLine());
                actualizado = empleadoDao.actualizar(emp);
                break;
            case 2:
                System.out.print("Nuevo sexo (m/f): ");
                emp.setSexo(sc.nextLine().charAt(0));
                actualizado = empleadoDao.actualizar(emp);
                break;
            case 3:
                System.out.print("Nueva categoría: ");
                emp.setCategoria(Integer.parseInt(sc.nextLine()));
                // Actualiza empleado y recalcula su sueldo asociado
                actualizado = empleadoDao.actualizar(emp);
                if (actualizado) nominasDao.recalcularYActualizar(emp.getDni());
                break;
            case 4:
                System.out.print("Nuevos años trabajados: ");
                emp.setAnyos(Integer.parseInt(sc.nextLine()));
                // Actualiza empleado y recalcula su sueldo asociado
                actualizado = empleadoDao.actualizar(emp);
                if (actualizado) nominasDao.recalcularYActualizar(emp.getDni());
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (actualizado) {
            System.out.println("¡Datos modificados correctamente!");
        } else {
            System.out.println("Error al actualizar los datos.");
        }
    }

    // 5.4 Recalcular y actualizar el sueldo de un empleado por DNI
    public static void recalcularSueldoEmpleado(EmpleadoDao empleadoDao, NominasDao nominasDao, Scanner sc) {
        System.out.println("\n========== RECALCULAR SUELDO ==========");
        System.out.print("Introduce el DNI del empleado: ");
        String dni = sc.nextLine();

        EmpleadoModel emp = empleadoDao.buscarPorDni(dni);
        if (emp == null) {
            System.out.println("El empleado no existe.");
            return;
        }

        boolean exito = nominasDao.recalcularYActualizar(dni);
        if (exito) {
            System.out.println("Sueldo recalculado y actualizado con éxito.");
        } else {
            System.out.println("Error al recalcular el sueldo.");
        }
    }

    // 5.5 Recalcular y actualizar los sueldos de todos
    public static void recalcularTodosSueldos(EmpleadoDao empleadoDao, NominasDao nominasDao) {
        boolean exito = nominasDao.recalcularTodos(empleadoDao);
        if (exito) {
            System.out.println("Se han recalculado todos los sueldos correctamente.");
        } else {
            System.out.println("Hubo un error al recalcular los sueldos.");
        }
    }

    // 5.6 Realizar copia de seguridad de la base de datos en ficheros
    public static void realizarCopiaSeguridad(EmpleadoDao empleadoDao, NominasDao nominasDao) {
        List<EmpleadoModel> empleados = empleadoDao.obtenerTodos();

        try (PrintWriter pwEmp = new PrintWriter(new FileWriter("backup_empleados.txt"));
             PrintWriter pwNom = new PrintWriter(new FileWriter("backup_nominas.txt"))) {

            for (EmpleadoModel e : empleados) {
                pwEmp.println(e.getNombre() + ", " + e.getDni() + ", " + e.getSexo() + ", " + e.getCategoria() + ", " + e.getAnyos());

                var nomina = nominasDao.buscarPorDni(e.getDni());
                if (nomina != null) {
                    pwNom.println(nomina.getDni() + ", " + nomina.getSueldo());
                }
            }
            System.out.println("Copia de seguridad realizada con éxito en 'backup_empleados.txt' y 'backup_nominas.txt'.");
        } catch (IOException e) {
            System.out.println("Error al generar la copia de seguridad: " + e.getMessage());
        }
    }
    public static void mostrarEmpleados(EmpleadoDao empleadoDao) {

        System.out.println();
        System.out.println("========== EMPLEADOS ==========");

        List<EmpleadoModel> empleados = empleadoDao.obtenerTodos();

        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en la base de datos.");
            return;
        }

        for (EmpleadoModel empleado : empleados) {

            System.out.println("------------------------------");
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("DNI: " + empleado.getDni());
            System.out.println("Sexo: " + empleado.getSexo());
            System.out.println("Categoría: " + empleado.getCategoria());
            System.out.println("Años trabajados: " + empleado.getAnyos());
        }

        System.out.println("------------------------------");
    }


    public static void mostrarSalario(
            EmpleadoDao empleadoDao,
            NominasDao nominasDao,
            Scanner sc) {

        System.out.println();
        System.out.println("========== CONSULTAR SALARIO ==========");

        System.out.print("Introduce el DNI: ");
        String dni = sc.nextLine();

        EmpleadoModel empleado = empleadoDao.buscarPorDni(dni);

        if (empleado == null) {
            System.out.println("No existe ese empleado.");
            return;
        }

        NominaModel nomina = nominasDao.buscarPorDni(dni);

        if (nomina == null) {
            System.out.println("No existe una nómina para ese empleado.");
            return;
        }

        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("DNI: " + nomina.getDni());
        System.out.println("Sueldo: " + nomina.getSueldo());
    }










}
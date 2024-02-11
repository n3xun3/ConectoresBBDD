package org.conectores.presentacion;

import org.conectores.entidad.Coche;
import org.conectores.entidad.Pasajero;
import org.conectores.negocio.DaoPasajero;
import org.conectores.persistencia.DaoCocheMySql;
import org.conectores.persistencia.DaoPasajeroMySql;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DaoCocheMySql daoCoche = new DaoCocheMySql();
    private static DaoPasajeroMySql daoPasajero = new DaoPasajeroMySql(); // Asumiendo que tienes una clase similar para la gestión de pasajeros

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenuPrincipal();
            int opcionPrincipal = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            switch (opcionPrincipal) {
                case 1:
                    gestionCoche();
                    break;
                case 2:
                    gestionPasajeros();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        System.out.println("¡Hasta luego!");
        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Gestión de Coches");
        System.out.println("2. Gestión de Pasajeros");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }


    private static void mostrarMenuCohes() {
        System.out.println("=== Menú ===");
        System.out.println("1. Agregar coche");
        System.out.println("2. Modificar coche");
        System.out.println("3. Borrar coche");
        System.out.println("4. Consultar coche por ID");
        System.out.println("5. Mostrar listado de coches");
        System.out.println("0. Volver al Mení principal");
        System.out.print("Seleccione una opción: ");
    }

    private static void gestionCoche(){
        boolean continuar = true;
        mostrarMenuCohes();
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        switch (opcion) {
            case 1:
                agregarCoche();
                break;
            case 2:
                modificarCoche();
                break;
            case 3:
                borrarCoche();
                break;
            case 4:
                consultarCoche();
                break;
            case 5:
                mostrarListadoCoches();
                break;
            case 0:
                continuar = false;
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private static void mostrarMenuPasajeros() {
        System.out.println("=== Gestión de Pasajeros ===");
        System.out.println("1. Crear nuevo pasajero");
        System.out.println("2. Modificar pasajero");
        System.out.println("3. Consulta pasajero por id");
        System.out.println("4. Listar todos los pasajeros");
        System.out.println("5. Borrar pasajero");
        System.out.println("6. Añadir pasajero a coche");
        System.out.println("7. Eliminar pasajero de un coche");
        System.out.println("8. Listar todos los pasajeros de un coche");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }
    private static void gestionPasajeros() {
        boolean continuarGestion = true;

        while (continuarGestion) {
            mostrarMenuPasajeros();
            int opcionPasajeros = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            switch (opcionPasajeros) {
                case 1:
                    agregarPasajero();
                    break;
                case 2:
                    modificarPasajero();
                    break;
                case 3:
                    consultarPasajero();
                    break;
                case 4:
                    mostrarListadoPsajeros();
                    break;
                case 5:
                    break;
                case 6:
                    relacionarPasajeroACoche();
                    break;
                case 7:
                    eliminarPasajeroDeCoche();
                    break;
                case 8:
                    obtenerPasajerosPorCoche();
                    break;
                case 0:
                    continuarGestion = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }




    private static void agregarCoche() {
        System.out.println("Ingrese la marca del coche:");
        String marca = scanner.nextLine();

        System.out.println("Ingrese el color del coche:");
        String color = scanner.nextLine();

        System.out.println("Ingrese el año del coche:");
        int año = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Coche coche = new Coche();
        coche.setMarca(marca);
        coche.setColor(color);
        coche.setAño(año);
        boolean altaExitosa = daoCoche.altaCoche(coche);
        if (altaExitosa) {
            System.out.println("Coche agregado correctamente.");
        } else {
            System.out.println("Error al agregar el coche.");
        }
    }

    private static void modificarCoche() {
        System.out.println("Ingrese el ID del coche que desea modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        System.out.println("Ingrese la nueva marca del coche:");
        String marca = scanner.nextLine();

        System.out.println("Ingrese el nuevo color del coche:");
        String color = scanner.nextLine();

        System.out.println("Ingrese el nuevo año del coche:");
        int año = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Coche coche = new Coche();
        coche.setMarca(marca);
        coche.setColor(color);
        coche.setAño(año);
        boolean modificado = daoCoche.modificarCochePorId(id, coche);
        if (modificado) {
            System.out.println("Coche modificado correctamente.");
        } else {
            System.out.println("Error al modificar el coche.");
        }
    }

    private static void consultarCoche() {
        System.out.println("Ingrese el ID del coche que desea consultar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Coche coche = daoCoche.consultarCocheId(id);
        if (coche != null) {
            System.out.println("Información del coche:");
            System.out.println("ID: " + coche.getId());
            System.out.println("Marca: " + coche.getMarca());
            System.out.println("Color: " + coche.getColor());
            System.out.println("Año: " + coche.getAño());
        } else {
            System.out.println("No se encontró ningún coche con el ID proporcionado.");
        }
    }

    private static void borrarCoche() {
        System.out.println("Ingrese el ID del coche que desea borrar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        boolean borrado = daoCoche.borrarCocheId(id);
        if (borrado) {
            System.out.println("Coche borrado correctamente.");
        } else {
            System.out.println("Error al borrar el coche.");
        }
    }

    private static void mostrarListadoCoches() {
        boolean mostrado = daoCoche.mostrarListadoCoches();
        if (!mostrado) {
            System.out.println("Error al mostrar el listado de coches.");
        }
    }

    private static void agregarPasajero() {
        System.out.println("Ingrese el nombre del pasajero:");
        String npmbre = scanner.nextLine();

        System.out.println("Ingrese la edad del pasajero:");
        int edad = scanner.nextInt();

        System.out.println("Ingrese el peso del pasajero:");
        int peso = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(npmbre);
        pasajero.setEdad(edad);
        pasajero.setPeso(peso);
        boolean altaExitosa = daoPasajero.altaPasajero(pasajero);
        if (altaExitosa) {
            System.out.println("Pasajero agregado correctamente.");
        } else {
            System.out.println("Error al agregar el Pasajero.");
        }
    }

    private static void modificarPasajero() {
        System.out.println("Ingrese el ID del pasajero que desea modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        System.out.println("Ingrese el nuevo nombre del pasajero:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la nueva edad del pasajero:");
        int edad = scanner.nextInt();

        System.out.println("Ingrese el nuevo peso del pasajero:");
        int peso = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(nombre);
        pasajero.setEdad(edad);
        pasajero.setPeso(peso);
        boolean modificado = daoPasajero.modificarPasajeroPorId(id, pasajero);
        if (modificado) {
            System.out.println("Pasajero modificado correctamente.");
        } else {
            System.out.println("Error al modificar el pasajero.");
        }
    }

    private static void consultarPasajero() {
        System.out.println("Ingrese el ID del pasajero que desea consultar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Pasajero pasajero = daoPasajero.consultarPasajeroId(id);
        if (pasajero != null) {
            System.out.println("Información del Pasajero:");
            System.out.println("ID: " + pasajero.getId());
            System.out.println("Nombre: " + pasajero.getNombre());
            System.out.println("Edad: " + pasajero.getEdad());
            System.out.println("Peso: " + pasajero.getPeso());
        } else {
            System.out.println("No se encontró ningún Pasajero con el ID proporcionado.");
        }
    }

    private static void mostrarListadoPsajeros() {
        boolean mostrado = daoPasajero.mostrarListadoPasajeros();
        if (!mostrado) {
            System.out.println("Error al mostrar el listado de coches.");
        }
    }

    private static void relacionarPasajeroACoche(){

        System.out.println("Ingrese el ID del pasajero :");
        int idPasajero = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        System.out.println("Ingrese el ID del coche :");
        int idCoche = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        boolean relacion = daoPasajero.relacionarPasajeroACoche(idPasajero,idCoche);
        if(relacion){
            System.out.println("El pasajero se relacionado con coche.");
        } else {
            System.out.println("El pasajero NO se relacionado con coche.");
        }
    }

    private static void obtenerPasajerosPorCoche(){
        System.out.println("Ingrese el ID del coche :");
        int idCoche = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        List<Pasajero> pasajeros = daoPasajero.obtenerPasajerosPorCoche(idCoche);
        if(pasajeros.size() > 0){
            System.out.println("El pasajero se relacionado con coche.");
            System.out.println(pasajeros);
        } else {
            System.out.println("El coche NO tiene ningún pasajero asociado.");
        }
    }

    private static void eliminarPasajeroDeCoche(){

    }


}


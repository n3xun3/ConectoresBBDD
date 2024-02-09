package org.conectores.presentacion;

import org.conectores.entidad.Coche;
import org.conectores.persistencia.DaoCocheMySql;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DaoCocheMySql daoCoche = new DaoCocheMySql();

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
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

        System.out.println("¡Hasta luego!");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== Menú ===");
        System.out.println("1. Agregar coche");
        System.out.println("2. Modificar coche");
        System.out.println("3. Borrar coche");
        System.out.println("4. Consultar coche por ID");
        System.out.println("5. Mostrar listado de coches");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
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
}


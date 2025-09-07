import java.util.Scanner;

 //Clase principal que ejecuta la aplicación.
 
public class Main {
    /**
     * Punto de entrada de la aplicación.
     * Inicializa el gestor de tareas, carga las tareas existentes desde un archivo,
     * muestra un menú de opciones al usuario y procesa su entrada
     * para realizar operaciones como agregar, listar, marcar como completada o eliminar tareas.
     * Finalmente, guarda las tareas en el archivo antes de salir.
     */
    public static void main(String[] args) {
        // Inicializa el Scanner para leer la entrada del usuario desde la consola.
        Scanner scanner = new Scanner(System.in);
        // Crea una instancia del gestor de tareas, que manejará la lógica de las tareas.
        GestorTareas gestion = new GestorTareas();
        // Variable para almacenar la opción seleccionada por el usuario en el menú.
        int opcion = 0;

        // Nombre del archivo donde se guardan y cargan las tareas.
        String archivo = "tarea.txt";
        // Carga las tareas desde el archivo al iniciar la aplicación.
        gestion.cargarArchivo(archivo);

        // Bucle principal del menú. Se repite hasta que el usuario elija la opción 5 (Salir).
        do {
            // Muestra el menú de opciones al usuario.
            System.out.println();
            System.out.println("-----Gestor de Tareas------");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Listar Tareas");
            System.out.println("3. Marcar Tarea(completada)");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Salir");
            System.out.println();

            // Solicita al usuario que ingrese una opción numérica.
            System.out.print("Ingrese un numero: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer del scanner después de leer un número para evitar problemas con la siguiente lectura de texto.
            System.out.println();

            // Evalúa la opción seleccionada por el usuario y ejecuta la acción correspondiente.
            switch (opcion) {
                case 1: // Agregar una nueva tarea
                    System.out.println();
                    System.out.print("Ingrese la descripcion de la tarea: ");
                    String descripcion = scanner.nextLine();
                    gestion.agregarTarea(descripcion);
                    System.out.println();
                    System.out.println("Tarea agregada con exito");
                    System.out.println();
                    break;
                case 2: // Listar todas las tareas
                    System.out.println();
                    System.out.println("Lista de Tareas");
                    gestion.listarTareas();
                    System.out.println();
                    break;
                case 3: // Marcar una tarea como completada
                    System.out.println();
                    gestion.listarTareas();
                    System.out.println();
                    System.out.print("Ingrese el numero de la tarea marcada: ");
                    int indice = scanner.nextInt() - 1; // Se resta 1 porque los usuarios ven la lista desde 1, pero los arreglos/listas empiezan en 0.
                    scanner.nextLine();
                    gestion.marcarTarea(indice);
                    System.out.println();
                    break;
                case 4: // Eliminar una tarea
                    System.out.println();
                    gestion.listarTareas();
                    System.out.println();
                    System.out.print("Ingrese el numero de la tarea que desea eliminar: ");
                    int indiceEliminar = scanner.nextInt() - 1; // Se resta 1 por la misma razón que en el caso 3.
                    scanner.nextLine();
                    gestion.eliminarTarea(indiceEliminar);
                    System.out.println();
                    break;
                case 5: // Salir del programa
                    System.out.println();
                    System.out.println("Saliendo del programa......");
                    System.out.println();
                    break;
                default: // Opción no válida
                    System.out.println();
                    System.out.println("Error ingrese un valor valido");
                    System.out.println();
                    break;
            }
        } while (opcion != 5);
        
        // Antes de cerrar la aplicación, guarda el estado actual de las tareas en el archivo.
        System.out.println();
        gestion.guardarArchivo(archivo);
        System.out.println("Tareas guardadas en: " + archivo);
        
        // Cierra el scanner para liberar los recursos.
        scanner.close();
    }
}
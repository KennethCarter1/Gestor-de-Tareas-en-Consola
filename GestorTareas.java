import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Gestiona la lógica para una lista de tareas.
 * Permite agregar, listar, marcar como completadas, eliminar,
 * y persistir las tareas en un archivo de texto.
 */
public class GestorTareas {
    /** Almacena la lista de objetos Tarea. */
    private ArrayList<Tarea> listaTareas;

    /**
     * Constructor para la clase GestorTareas.
     * Inicializa la lista de tareas como un nuevo ArrayList vacío.
     */
    public GestorTareas() {
        listaTareas = new ArrayList<>();
    }

    //Agrega una nueva tarea a la lista.
    public void agregarTarea(String descripcion) {
        Tarea nuevaTarea = new Tarea(descripcion);
        listaTareas.add(nuevaTarea);
    }

    /**
     * Muestra todas las tareas de la lista en la consola.
     * Si la lista está vacía, muestra un mensaje indicándolo.
     */
    public void listarTareas() {
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
        } else {
            for (int i = 0; i < listaTareas.size(); i++) {
                // Imprime el número de tarea (i+1) y su representación en String.
                System.out.println((i + 1) + ". " + listaTareas.get(i));
            }
        }
    }

    /**
     * Marca una tarea específica como completada.
     */
    public void marcarTarea(int indice) {
        // Valida que el índice esté dentro de los límites de la lista.
        if (indice >= 0 && indice < listaTareas.size()) {
            listaTareas.get(indice).marcaComplete();
        } else {
            System.out.println("Indice no existente");
        }
    }


    //Elimina una tarea de la lista.

    public void eliminarTarea(int indice) {
        // Valida que el índice esté dentro de los límites de la lista.
        if (indice >= 0 && indice < listaTareas.size()) {
            listaTareas.remove(indice);
        } else {
            System.out.println("Indice no existente");
        }
    }

    /**
     * Guarda la lista de tareas en un archivo de texto.
     * Cada línea del archivo representa una tarea en el formato "completada;descripcion".
     */
    public void guardarArchivo(String nombreArchivo){
        try {
            
            FileWriter escritura = new FileWriter(nombreArchivo);
            for(int i = 0; i < listaTareas.size(); i++){
                Tarea tarea = listaTareas.get(i);
                // Escribe cada tarea en una nueva línea con el formato: estado;descripción
                escritura.write(tarea.isCompletada()+ ";" + tarea.getDescripcion() + "\n");
            }

            escritura.close();

        } catch (IOException e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

    /**
     * Carga las tareas desde un archivo de texto.
     * Lee el archivo línea por línea, esperando el formato "completada;descripcion".
     * Si el archivo no existe, no hace nada y la lista de tareas permanece vacía.
     */
    public void cargarArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        // Si el archivo no existe, no hay nada que cargar.
        if (!archivo.exists()) {
            return;
        }

        // Se utiliza un bloque try-catch para asegurar que el Scanner se cierre automáticamente.
        try (Scanner lector = new Scanner(archivo)) {
            listaTareas.clear(); // Limpia la lista actual antes de cargar las nuevas tareas.

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";", 2); // Divide la línea en estado y descripción.
                
                if (partes.length == 2) {
                    boolean completada = Boolean.parseBoolean(partes[0]);
                    String descripcion = partes[1];

                    Tarea tarea = new Tarea(descripcion);
                    if (completada) {
                        tarea.marcaComplete();
                    }
                    listaTareas.add(tarea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las Tareas: " + e.getMessage());
        }
    }
}
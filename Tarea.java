/**
 * Representa una tarea individual en el gestor de tareas.
 * Cada tarea tiene una descripción textual y un estado que indica si ha sido completada o no.
 */
public class Tarea {
    //La descripción textual de la tarea.
    private String descripcion;
    //El estado de la tarea, {@code true} si está completada, {@code false} si está pendiente.
    private boolean completada;

    /**
     * Constructor para crear una nueva Tarea.
     * Por defecto, una nueva tarea se inicializa como "pendiente" (no completada).
     */
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false; // Las tareas nuevas siempre empiezan como pendientes.
    }


    //Marca la tarea como completada, cambiando su estado a {@code true}.

    public void marcaComplete() {
        this.completada = true;
    }


    //Devuelve la descripción de la tarea.
    public String getDescripcion() {
        return descripcion;
    }


    //Devuelve el estado de finalización de la tarea.

    public boolean isCompletada() {
        return completada;
    }

    /**
     * Devuelve una representación en formato de cadena de la tarea,
     * indicando su estado (Completada o Pendiente) junto con su descripción.
     * Este método sobrescribe el método toString() de la clase Object.
     */
    public String toString() {
        if (completada) {
            return "[Completada] " + descripcion;
        } else {
            return "[Pendiente] " + descripcion;
        }
    }
}
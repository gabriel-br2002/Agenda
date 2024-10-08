package proyecto;

import java.time.LocalDate;

/**
 * Clase Tarea
 *
 * Esta clase representa una tarea en un sistema de gestión de tareas.
 * Cada tarea tiene un nombre, una fecha, una prioridad y un estado
 * que indica si ha sido completada o no.
 */
public class Tarea {
    private String nombreTarea; // Almacena el nombre de la tarea
    private LocalDate fechaTarea; // Almacena la fecha de la tarea
    private String prioridadTarea; // Almacena la prioridad de la tarea
    private boolean estadoCompletada; // Indica si la tarea está completada

    /**
     * Constructor de la clase Tarea.
     *
     * Este constructor inicializa una nueva tarea con un nombre,
     * una fecha y una prioridad.
     *
     * @param nombre El nombre de la tarea.
     * @param fecha La fecha en que se debe realizar la tarea.
     * @param prioridad La prioridad de la tarea (por ejemplo, "Alta", "Media", "Baja").
     *
     * Ejemplo:
     * <pre>
     * Tarea tarea1 = new Tarea("Comprar víveres", LocalDate.of(2024, 10, 15), "Alta");
     * </pre>
     */
    public Tarea(String nombre, LocalDate fecha, String prioridad) {
        this.nombreTarea = nombre; // Asigna el nombre de la tarea
        this.fechaTarea = fecha; // Asigna la fecha de la tarea
        this.prioridadTarea = prioridad; // Asigna la prioridad de la tarea
        this.estadoCompletada = false; // Inicializa el estado como no completada
    }

    /**
     * Obtiene el nombre de la tarea.
     *
     * @return El nombre de la tarea.
     *
     * Ejemplo:
     * <pre>
     * String nombre = tarea1.getNombre(); // "Comprar víveres"
     * </pre>
     */
    public String getNombre() {
        return nombreTarea; // Devuelve el nombre de la tarea
    }

    /**
     * Establece el nombre de la tarea.
     *
     * @param nombre El nuevo nombre de la tarea.
     *
     * Ejemplo:
     * <pre>
     * tarea1.setNombre("Comprar frutas");
     * </pre>
     */
    public void setNombre(String nombre) {
        this.nombreTarea = nombre; // Asigna el nuevo nombre de la tarea
    }

    /**
     * Obtiene la fecha de la tarea.
     *
     * @return La fecha de la tarea.
     *
     * Ejemplo:
     * <pre>
     * LocalDate fecha = tarea1.getFecha(); // Obtiene la fecha de la tarea
     * </pre>
     */
    public LocalDate getFecha() {
        return fechaTarea; // Devuelve la fecha de la tarea
    }

    /**
     * Establece la fecha de la tarea.
     *
     * @param fecha La nueva fecha de la tarea.
     *
     * Ejemplo:
     * <pre>
     * tarea1.setFecha(LocalDate.of(2024, 10, 20));
     * </pre>
     */
    public void setFecha(LocalDate fecha) {
        this.fechaTarea = fecha; // Asigna la nueva fecha de la tarea
    }

    /**
     * Obtiene la prioridad de la tarea.
     *
     * @return La prioridad de la tarea.
     *
     * Ejemplo:
     * <pre>
     * String prioridad = tarea1.getPrioridad(); // "Alta"
     * </pre>
     */
    public String getPrioridad() {
        return prioridadTarea; // Devuelve la prioridad de la tarea
    }

    /**
     * Establece la prioridad de la tarea.
     *
     * @param prioridad La nueva prioridad de la tarea.
     *
     * Ejemplo:
     * <pre>
     * tarea1.setPrioridad("Baja");
     * </pre>
     */
    public void setPrioridad(String prioridad) {
        this.prioridadTarea = prioridad; // Asigna la nueva prioridad de la tarea
    }

    /**
     * Verifica si la tarea está completada.
     *
     * @return true si la tarea está completada, false en caso contrario.
     *
     * Ejemplo:
     * <pre>
     * boolean completada = tarea1.isCompletada(); // false
     * </pre>
     */
    public boolean isCompletada() {
        return estadoCompletada; // Devuelve el estado de completado
    }

    /**
     * Establece el estado de completado de la tarea.
     *
     * @param completada true si la tarea se marca como completada, false si no.
     *
     * Ejemplo:
     * <pre>
     * tarea1.setCompletada(true); // Marca la tarea como completada
     * </pre>
     */
    public void setCompletada(boolean completada) {
        this.estadoCompletada = completada; // Asigna el nuevo estado de completado
    }

    /**
     * Devuelve una representación en formato String de la tarea.
     *
     * @return Una cadena que describe la tarea con su nombre, fecha, prioridad y estado.
     *
     * Ejemplo:
     * <pre>
     * System.out.println(tarea1.toString()); // Muestra información de la tarea
     * </pre>
     */
    @Override
    public String toString() {
        return "          # " + nombreTarea + "  " + "   [ FECHA: " + fechaTarea + " ]   " +
                "   [ PRIORIDAD: " + prioridadTarea + "]   " +
                "   [ TAREA: " + (estadoCompletada ? "COMPLETA" : "NO COMPLETA") + "]   ";
    }
}
package proyecto;

import java.time.LocalDate;
/**
 * Representa una tarea con un nombre, fecha de vencimiento, prioridad y estado de
 * completación.
 */
public class Tarea {
    private String nombreTarea;
    private LocalDate fechaTarea;
    private String prioridadTarea;
    private boolean estadoCompletada;
    /**
     * Crea una nueva tarea con los datos proporcionados.
     *
     * @param nombre El nombre de la tarea.
     * @param fecha La fecha de vencimiento de la tarea.
     * @param prioridad La prioridad de la tarea.
     */
    public Tarea(String nombre, LocalDate fecha, String prioridad) {
        this.nombreTarea = nombre;
        this.fechaTarea = fecha;
        this.prioridadTarea = prioridad;
        this.estadoCompletada = false;
    }
    /**
     * Obtiene el nombre de la tarea.
     *
     * @return El nombre de la tarea.
     */
    public String getNombre() {
        return nombreTarea;
    }
    /**
     * Establece el nombre de la tarea.
     *
     * @param nombre El nombre de la tarea.
     */
    public void setNombre(String nombre) {
        this.nombreTarea = nombre;
    }
    /**
     * Obtiene la fecha de vencimiento de la tarea.
     *
     * @return La fecha de vencimiento de la tarea.
     */
    public LocalDate getFecha() {
        return fechaTarea;
    }
    /**
     * Establece la fecha de vencimiento de la tarea.
     *
     * @param fecha La fecha de vencimiento de la tarea.
     */
    public void setFecha(LocalDate fecha) {
        this.fechaTarea = fecha;
    }
    /**
     * Obtiene la prioridad de la tarea.
     *
     * @return La prioridad de la tarea.
     */
    public String getPrioridad() {
        return prioridadTarea;
    }
    /**
     * Establece la prioridad de la tarea.
     *
     * @param prioridad La prioridad de la tarea.
     */
    public void setPrioridad(String prioridad) {
        this.prioridadTarea = prioridad;
    }
    /**
     * Verifica si la tarea está completada.
     *
     * @return true si la tarea está completada, false en caso contrario.
     */
    public boolean isCompletada() {
        return estadoCompletada;
    }
    /**
     * Establece el estado de completación de la tarea.
     *
     * @param completada true si la tarea está completada, false en caso contrario.
     */
    public void setCompletada(boolean completada) {
        this.estadoCompletada = completada;
    }
     /**
     * Devuelve una representación en cadena de la tarea.
     *
     * @return Una cadena que representa la tarea.
     */
    @Override
    public String toString() {
        return "          # " + nombreTarea + "  " + "   [ FECHA: " + fechaTarea + " ]   " + "   [ PRIORIDAD "
                + prioridadTarea + "]   " + "   [ TAREA" + (estadoCompletada ? " COMPLETADA" : " NO COMPLETADA")
                + "]   ";
    }
}
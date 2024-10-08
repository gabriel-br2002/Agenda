package proyecto;

import java.time.LocalDate;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ListaDeTareas
 *
 * Esta clase representa una lista de tareas personales asociadas a un usuario.
 * Permite agregar, eliminar y gestionar las tareas, así como guardar y cargar
 * las tareas desde un archivo.
 */
public class ListaDeTareas {
    private String nombreDeUsuario; // Almacena el nombre del usuario
    private List<Tarea> tareasPersonales; // Lista de tareas personales
    private String nombreArchivoTareas; // Nombre del archivo donde se guardan las tareas

    /**
     * Constructor de la clase ListaDeTareas.
     *
     * Este constructor inicializa la lista de tareas para un usuario específico
     * y carga las tareas desde un archivo, si existen.
     *
     * @param nombreUsuario El nombre del usuario para el cual se crea la lista de tareas.
     *
     * Ejemplo:
     * <pre>
     * ListaDeTareas lista = new ListaDeTareas("Juan");
     * </pre>
     */
    public ListaDeTareas(String nombreUsuario) {
        this.nombreDeUsuario = nombreUsuario;
        this.tareasPersonales = new ArrayList<>(); // Inicializa la lista de tareas
        this.nombreArchivoTareas = nombreUsuario + "_tareas.txt"; // Establece el nombre del archivo
        cargarTareas(); // Carga las tareas desde el archivo
    }

    /**
     * Agrega una tarea a la lista de tareas.
     *
     * Este método añade una tarea a la lista y la guarda en el archivo.
     *
     * @param tarea La tarea que se desea agregar.
     *
     * Ejemplo:
     * <pre>
     * Tarea nuevaTarea = new Tarea("Comprar víveres", LocalDate.of(2024, 10, 15), "Alta");
     * lista.agregarTarea(nuevaTarea);
     * </pre>
     */
    public void agregarTarea(Tarea tarea) {
        tareasPersonales.add(tarea); // Añade la tarea a la lista
        guardarTareas(); // Guarda las tareas en el archivo
    }

    /**
     * Elimina una tarea de la lista de tareas.
     *
     * Este método quita una tarea de la lista y actualiza el archivo.
     *
     * @param tarea La tarea que se desea eliminar.
     *
     * Ejemplo:
     * <pre>
     * lista.eliminarTarea(nuevaTarea); // Elimina la tarea de la lista
     * </pre>
     */
    public void eliminarTarea(Tarea tarea) {
        tareasPersonales.remove(tarea); // Elimina la tarea de la lista
        guardarTareas(); // Guarda las tareas actualizadas en el archivo
    }

    /**
     * Obtiene la lista de tareas.
     *
     * @return Una lista de objetos Tarea que representan las tareas personales.
     *
     * Ejemplo:
     * <pre>
     * List<Tarea> tareas = lista.getTareas(); // Obtiene las tareas de la lista
     * </pre>
     */
    public List<Tarea> getTareas() {
        return tareasPersonales; // Devuelve la lista de tareas
    }

    /**
     * Guarda todas las tareas en un archivo.
     *
     * Este método escribe la lista de tareas en el archivo, cada tarea en una línea.
     *
     * Ejemplo de uso:
     * <pre>
     * lista.guardarTareas(); // Guarda las tareas actuales en el archivo
     * </pre>
     */
    public void guardarTareas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoTareas))) {
            for (Tarea tarea : tareasPersonales) {
                // Escribe el nombre, la fecha, la prioridad y el estado de la tarea en el archivo
                bw.write(tarea.getNombre() + "," + tarea.getFecha() + "," + tarea.getPrioridad() + ","
                        + tarea.isCompletada());
                bw.newLine(); // Nueva línea para la siguiente tarea
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage()); // Muestra un mensaje de error si ocurre una excepción
        }
    }

    /**
     * Carga tareas desde un archivo.
     *
     * Este método lee el archivo de tareas y crea objetos Tarea a partir de su contenido.
     * Si el archivo no existe, no se realiza ninguna acción.
     */
    private void cargarTareas() {
        File archivo = new File(nombreArchivoTareas); // Crea un objeto File con el nombre del archivo
        if (!archivo.exists()) {
            return; // Si el archivo no existe, retorna sin hacer nada
        }

        // Carga las tareas desde el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea; // Variable para almacenar cada línea del archivo
            while ((linea = br.readLine()) != null) { // Lee el archivo línea por línea
                String[] partes = linea.split(","); // Separa la línea en partes
                if (partes.length == 4) { // Verifica que hay cuatro partes
                    String nombre = partes[0]; // Nombre de la tarea
                    LocalDate fecha = LocalDate.parse(partes[1]); // Fecha de la tarea
                    String prioridad = partes[2]; // Prioridad de la tarea
                    boolean completada = Boolean.parseBoolean(partes[3]); // Estado de la tarea
                    Tarea tarea = new Tarea(nombre, fecha, prioridad); // Crea un nuevo objeto Tarea
                    tarea.setCompletada(completada); // Establece el estado de completada
                    tareasPersonales.add(tarea); // Añade la tarea a la lista
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tareas: " + e.getMessage()); // Muestra un mensaje de error si ocurre una excepción
        }
    }
}
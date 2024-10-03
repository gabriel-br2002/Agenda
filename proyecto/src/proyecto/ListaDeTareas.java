package proyecto;

import java.time.LocalDate;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Representa una lista de tareas personales para un usuario específico.
 * Permite agregar, eliminar, guardar y cargar tareas desde un archivo.
 */
public class ListaDeTareas {
    private String nombreDeUsuario;
    private List<Tarea> tareasPersonales;
    private String nombreArchivoTareas;
    /**
     * Crea una nueva lista de tareas para el usuario especificado.
     *
     * @param nombreUsuario El nombre del usuario.
     */
    public ListaDeTareas(String nombreUsuario) {
        this.nombreDeUsuario = nombreUsuario;
        this.tareasPersonales = new ArrayList<>();
        this.nombreArchivoTareas = nombreUsuario + "_tareas.txt";
        cargarTareas();
    }
    /**
     * Agrega una tarea a la lista de tareas personales y guarda los cambios.
     *
     * @param tarea La tarea a agregar.
     */
    public void agregarTarea(Tarea tarea) {
        tareasPersonales.add(tarea);
        guardarTareas();
    }
    /**
     * Elimina una tarea de la lista de tareas personales y guarda los cambios.
     *
     * @param tarea La tarea a eliminar.
     */
    public void eliminarTarea(Tarea tarea) {
        tareasPersonales.remove(tarea);
        guardarTareas();
    }
    /**
     * Obtiene la lista de tareas personales.
     *
     * @return La lista de tareas personales.
     */
    public List<Tarea> getTareas() {
        return tareasPersonales;
    }
    /**
     * Guarda las tareas personales en un archivo.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void guardarTareas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoTareas))) {
            for (Tarea tarea : tareasPersonales) {
                bw.write(tarea.getNombre() + "," + tarea.getFecha() + "," + tarea.getPrioridad() + ","
                        + tarea.isCompletada());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage());
        }
    }
    /**
     * Carga las tareas personales desde un archivo.
     * 
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private void cargarTareas() {
        File archivo = new File(nombreArchivoTareas);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    LocalDate fecha = LocalDate.parse(partes[1]);
                    String prioridad = partes[2];
                    boolean completada = Boolean.parseBoolean(partes[3]);
                    Tarea tarea = new Tarea(nombre, fecha, prioridad);
                    tarea.setCompletada(completada);
                    tareasPersonales.add(tarea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }
    }
}
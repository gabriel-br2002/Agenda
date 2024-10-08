package proyecto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario
 *
 * Esta clase representa a un usuario en un sistema de tareas.
 * Cada usuario tiene un nombre, una contraseña y una lista de tareas.
 * Además, proporciona métodos para guardar y cargar usuarios desde un archivo.
 */
public class Usuario {
    private String nombreUsuario; // Almacena el nombre del usuario
    private String password; // Almacena la contraseña del usuario
    private String archivoUsuarios; // Ruta del archivo que guarda los usuarios
    private ListaDeTareas listaDeTareas; // Lista de tareas del usuario

    /**
     * Constructor de la clase Usuario.
     *
     * Este constructor inicializa un nuevo usuario con un nombre y una contraseña.
     *
     * @param nombre El nombre del usuario.
     * @param contraseña La contraseña del usuario.
     *
     * Ejemplo:
     * <pre>
     * Usuario nuevoUsuario = new Usuario("Juan", "contraseña123");
     * </pre>
     */
    public Usuario(String nombre, String contraseña) {
        this.nombreUsuario = nombre;
        this.password = contraseña;
        this.archivoUsuarios = "usuarios.txt"; // Establece la ruta del archivo de usuarios
        this.listaDeTareas = new ListaDeTareas(nombre); // Crea una nueva lista de tareas para el usuario
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     *
     * Ejemplo:
     * <pre>
     * String nombre = nuevoUsuario.getNombre(); // "Juan"
     * </pre>
     */
    public String getNombre() {
        return nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     *
     * Ejemplo:
     * <pre>
     * String contraseña = nuevoUsuario.getContraseña(); // "contraseña123"
     * </pre>
     */
    public String getContraseña() {
        return password;
    }

    /**
     * Obtiene la lista de tareas del usuario.
     *
     * @return La lista de tareas asociada al usuario.
     *
     * Ejemplo:
     * <pre>
     * ListaDeTareas tareas = nuevoUsuario.getListaDeTareas();
     * </pre>
     */
    public ListaDeTareas getListaDeTareas() {
        return listaDeTareas;
    }

    /**
     * Guarda el usuario en un archivo.
     *
     * Este método agrega el nombre de usuario y la contraseña al archivo "usuarios.txt".
     * Si el archivo no existe, se crea automáticamente.
     *
     * Ejemplo:
     * <pre>
     * nuevoUsuario.guardarUsuarios(); // Guarda al usuario en el archivo
     * </pre>
     */
    public void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            bw.write(nombreUsuario + "," + password); // Guarda el nombre y la contraseña separados por una coma
            bw.newLine(); // Agrega una nueva línea para el siguiente usuario
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage()); // Muestra un mensaje de error si algo falla
        }
    }

    /**
     * Carga usuarios desde un archivo.
     *
     * Este método lee el archivo "usuarios.txt" y crea una lista de objetos Usuario
     * a partir de la información en el archivo.
     *
     * @return Una lista de objetos Usuario.
     *
     * Ejemplo:
     * <pre>
     * List<Usuario> listaUsuarios = Usuario.cargarUsuarios();
     * for (Usuario usuario : listaUsuarios) {
     *     System.out.println("Nombre: " + usuario.getNombre());
     *     System.out.println("Contraseña: " + usuario.getContraseña());
     * }
     * </pre>
     */
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>(); // Crea una lista vacía para almacenar usuarios
        File archivo = new File("usuarios.txt"); // Define el archivo que contiene los usuarios

        if (!archivo.exists()) { // Verifica si el archivo existe
            // Si no existe, se crea un nuevo archivo
            try {
                archivo.createNewFile(); // Crea el archivo
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de usuarios: " + e.getMessage()); // Muestra un mensaje de error
            }
            return usuarios; // Devuelve la lista vacía
        }

        // Carga usuarios desde el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea; // Variable para almacenar cada línea del archivo
            while ((linea = br.readLine()) != null) { // Lee el archivo línea por línea
                String[] partes = linea.split(","); // Separa la línea en nombre y contraseña
                if (partes.length == 2) { // Verifica que haya exactamente dos partes
                    // Crea un nuevo usuario y lo añade a la lista
                    usuarios.add(new Usuario(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage()); // Muestra un mensaje de error
        }
        return usuarios; // Devuelve la lista de usuarios cargados
    }
}
package proyecto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Representa un usuario con un nombre de usuario, contraseña y una lista de tareas.
 * Permite guardar y cargar usuarios desde un archivo.
 */
public class Usuario {
    private String nombreUsuario;
    private String password;
    private String archivoUsuarios;
    private ListaDeTareas listaDeTareas;
    /**
     * Crea un nuevo usuario con el nombre y la contraseña proporcionados.
     *
     * @param nombre El nombre del usuario.
     * @param contraseña La contraseña del usuario.
     */
    public Usuario(String nombre, String contraseña) {
        this.nombreUsuario = nombre;
        this.password = contraseña;
        this.archivoUsuarios = "usuarios.txt";
        this.listaDeTareas = new ListaDeTareas(nombre);
    }
    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombreUsuario;
    }
    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContraseña() {
        return password;
    }
    /**
     * Obtiene la lista de tareas del usuario.
     *
     * @return La lista de tareas del usuario.
     */
    public ListaDeTareas getListaDeTareas() {
        return listaDeTareas;
    }
    /**
     * Guarda el usuario en un archivo.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            bw.write(nombreUsuario + "," + password);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    /**
     * Carga los usuarios desde un archivo.
     *
     * @return Una lista de usuarios cargados desde el archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File("usuarios.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de usuarios: " + e.getMessage());
            }
            return usuarios;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    usuarios.add(new Usuario(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}
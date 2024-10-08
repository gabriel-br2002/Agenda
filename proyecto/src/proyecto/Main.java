package proyecto;

/**
 * Clase Main
 *
 * Esta clase contiene el método principal que se ejecuta al iniciar el programa.
 * Su función principal es crear una instancia de la clase InterfazGrafica,
 * que es responsable de mostrar la interfaz de usuario.
 */
public class Main {
    /**
     * Método principal de la aplicación.
     *
     * Este es el punto de entrada del programa. Cuando se ejecuta, crea una
     * nueva instancia de InterfazGrafica, iniciando así la interfaz de usuario.
     *
     * @param args Argumentos de la línea de comandos. No se utilizan en esta implementación.
     *
     * Ejemplo de uso:
     * <pre>
     * java metodos.Main
     * </pre>
     */
    public static void main(String[] args) {
        new InterfazGrafica(); // Crea una nueva instancia de InterfazGrafica
    }
}
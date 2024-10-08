package proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase InterfazGrafica
 * 
 * Esta clase representa la interfaz gráfica de usuario (GUI) para el sistema de
 * gestión de tareas. Permite a los usuarios iniciar sesión, registrar nuevos
 * usuarios, y gestionar sus tareas (añadir, eliminar, modificar, completar,
 * e imprimir la lista de tareas).
 */
public class InterfazGrafica {
    private JFrame ventanaPrincipal; // Ventana principal de la aplicación
    private JList<Tarea> listaTareas; // Lista visual de tareas
    private DefaultListModel<Tarea> modeloTareas; // Modelo de la lista de tareas
    private ListaDeTareas tareasUsuario; // Lista de tareas del usuario
    private JTextField campoUsuario; // Campo para ingresar el nombre de usuario
    private JTextField campoContraseña; // Campo para ingresar la contraseña

    private int anchoCampoLogin = 9; // Ancho de los campos de inicio de sesión
    private int anchoCampoTarea = 9; // Ancho de los campos para tareas

    /**
     * Constructor de la clase InterfazGrafica.
     * Este constructor inicializa la ventana principal y configura los
     * componentes de la interfaz gráfica, incluyendo los campos de inicio
     * de sesión y botones para acceder al sistema.
     * Ejemplo de uso:
     * <pre>
     * InterfazGrafica interfaz = new InterfazGrafica();
     * </pre>
     */
    public InterfazGrafica() {
        ventanaPrincipal = new JFrame("Lista De Tareas");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(400, 100);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.getContentPane().setBackground(new Color(240, 240, 240));

        modeloTareas = new DefaultListModel<>();
        listaTareas = new JList<>(modeloTareas);
        listaTareas.setBackground(new Color(255, 211, 151));
        listaTareas.setForeground(new Color(80, 80, 80));

        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(255, 150, 80));
        campoUsuario = new JTextField(anchoCampoLogin);
        campoContraseña = new JTextField(anchoCampoLogin);
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrar");

        btnIniciarSesion.setBackground(new Color(102, 200, 100));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnRegistrar.setBackground(new Color(90, 160, 255));
        btnRegistrar.setForeground(Color.WHITE);

        panelLogin.add(new JLabel("Usuario:"));
        panelLogin.add(campoUsuario);
        panelLogin.add(new JLabel("Contraseña:"));
        panelLogin.add(campoContraseña);
        panelLogin.add(btnIniciarSesion);
        panelLogin.add(btnRegistrar);
        ventanaPrincipal.add(panelLogin);

        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        btnRegistrar.addActionListener(e -> registrarUsuario());

        ventanaPrincipal.setVisible(true);
    }

    /**
     * Método para iniciar sesión en la aplicación.
     * Este método verifica las credenciales ingresadas por el usuario. Si son correctas,
     * carga la lista de tareas del usuario y muestra la interfaz de gestión de tareas.
     */
    private void iniciarSesion() {
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();
        List<Usuario> usuarios = Usuario.cargarUsuarios();

        for (Usuario user : usuarios) {
            if (user.getNombre().equals(usuario) && user.getContraseña().equals(contraseña)) {
                tareasUsuario = user.getListaDeTareas();
                mostrarListaDeTareas(); // Muestra la interfaz de tareas
                return;
            }
        }
        JOptionPane.showMessageDialog(ventanaPrincipal, "Usuario o contraseña incorrectos."); // Mensaje de error
    }

    /**
     * Método para registrar un nuevo usuario.
     * Este método permite a los nuevos usuarios crear una cuenta si ingresan un
     * nombre de usuario y una contraseña. Una vez registrado, se carga su lista de tareas.
     */
    private void registrarUsuario() {
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();

        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            Usuario nuevoUsuario = new Usuario(usuario, contraseña);
            nuevoUsuario.guardarUsuarios(); // Guarda el nuevo usuario
            tareasUsuario = nuevoUsuario.getListaDeTareas(); // Carga la lista de tareas
            mostrarListaDeTareas(); // Muestra la interfaz de tareas
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor ingrese usuario y contraseña.");
        }
    }

    /**
     * Método para mostrar la lista de tareas del usuario.
     * Este método cambia la interfaz para mostrar las opciones de gestión de tareas,
     * incluyendo añadir, eliminar y modificar tareas.
     */
    private void mostrarListaDeTareas() {
        ventanaPrincipal.getContentPane().removeAll(); // Limpia la ventana
        ventanaPrincipal.setSize(650, 450); // Ajusta el tamaño de la ventana
        JButton btnAñadir = new JButton("AÑADIR TAREA");
        JButton btnFinalizar = new JButton("FINALIZAR");
        JButton btnCompletar = new JButton("COMPLETAR TAREA");
        JButton btnEliminar = new JButton("ELIMINAR TAREA");
        JButton btnModificar = new JButton("MODIFICAR TAREA");
        JButton btnImprimir = new JButton("IMPRIMIR LISTA");

        // Configuración de colores y acciones de botones
        btnAñadir.setBackground(new Color(110, 220, 80));
        btnAñadir.setForeground(Color.WHITE);
        btnCompletar.setBackground(new Color(110, 220, 80));
        btnCompletar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(240, 200, 140));
        btnEliminar.setForeground(Color.WHITE);
        btnModificar.setBackground(new Color(240, 200, 140));
        btnModificar.setForeground(Color.WHITE);
        btnImprimir.setBackground(new Color(240, 200, 140));
        btnImprimir.setForeground(Color.WHITE);
        btnFinalizar.setBackground(new Color(255, 83, 84));
        btnFinalizar.setForeground(Color.WHITE);

        // Agrega acciones a los botones
        btnAñadir.addActionListener(e -> añadirTarea());
        btnCompletar.addActionListener(e -> completarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());
        btnModificar.addActionListener(e -> modificarTarea());
        btnImprimir.addActionListener(e -> imprimirLista());
        btnFinalizar.addActionListener(e -> finalizarPrograma());

        modeloTareas.clear(); // Limpia la lista de tareas
        for (Tarea tarea : tareasUsuario.getTareas()) {
            modeloTareas.addElement(tarea); // Agrega tareas al modelo
        }

        // Configuración de paneles
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(255, 167, 53));
        panelSuperior.add(btnAñadir);
        panelSuperior.add(btnFinalizar);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(255, 167, 53));
        panelInferior.add(btnCompletar);
        panelInferior.add(btnEliminar);
        panelInferior.add(btnModificar);
        panelInferior.add(btnImprimir);

        // Agrega paneles a la ventana
        ventanaPrincipal.add(panelSuperior, BorderLayout.NORTH);
        ventanaPrincipal.add(panelCentral, BorderLayout.CENTER);
        ventanaPrincipal.add(panelInferior, BorderLayout.SOUTH);

        ventanaPrincipal.revalidate(); // Actualiza la interfaz
        ventanaPrincipal.repaint(); // Redibuja la interfaz
    }

    /**
     * Método para añadir una nueva tarea a la lista.
     * Este método solicita al usuario que ingrese el nombre, la fecha límite y la
     * prioridad de la tarea a través de un cuadro de diálogo.
     */
    private void añadirTarea() {
        JTextField campoNombre = new JTextField(6);
        JTextField campoFecha = new JTextField(6);
        String[] prioridades = { "ALTA", "MEDIA", "BAJA" };
        JComboBox<String> comboPrioridad = new JComboBox<>(prioridades);

        JPanel panel = new JPanel();
        panel.add(new JLabel("NOMBRE DE TAREA:"));
        panel.add(campoNombre);
        panel.add(new JLabel("FECHA LIMITE (AÑO-MES-DIA):"));
        panel.add(campoFecha);
        panel.add(new JLabel("PRIORIDAD:"));
        panel.add(comboPrioridad);

        int option = JOptionPane.showConfirmDialog(ventanaPrincipal, panel, "AÑADIR TAREA",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nombre = campoNombre.getText();
            String fechaString = campoFecha.getText();
            String prioridad = (String) comboPrioridad.getSelectedItem();
            try {
                Tarea tarea = new Tarea(nombre, LocalDate.parse(fechaString), prioridad);
                tareasUsuario.agregarTarea(tarea);
                modeloTareas.addElement(tarea); // Agrega la tarea a la lista
            } catch (Exception e) {
                JOptionPane.showMessageDialog(ventanaPrincipal, "ERROR AL AÑADIR TAREA: " + e.getMessage());
            }
        }
    }

    /**
     * Método para marcar una tarea como completada.
     * Este método marca la tarea seleccionada en la lista como completada y actualiza
     * la interfaz.
     */
    private void completarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            tareaSeleccionada.setCompletada(true);
            modeloTareas.removeElement(tareaSeleccionada);
            modeloTareas.addElement(tareaSeleccionada); // Actualiza la visualización
            tareasUsuario.guardarTareas(); // Guarda los cambios
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "POR FAVOR SELECCIONE UNA TAREA PARA COMPLETAR.");
        }
    }

    /**
     * Método para eliminar una tarea de la lista.
     * Este método elimina la tarea seleccionada en la lista de tareas.
     */
    private void eliminarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            tareasUsuario.eliminarTarea(tareaSeleccionada);
            modeloTareas.removeElement(tareaSeleccionada); // Actualiza la lista visual
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "POR FAVOR SELECCIONE UNA TAREA PARA ELIMINAR");
        }
    }

    /**
     * Método para modificar una tarea existente.
     * Este método permite al usuario modificar el nombre, la fecha y la prioridad de
     * la tarea seleccionada.
     */
    private void modificarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            JTextField campoNombre = new JTextField(anchoCampoTarea);
            campoNombre.setText(tareaSeleccionada.getNombre());
            JTextField campoFecha = new JTextField(anchoCampoTarea);
            campoFecha.setText(tareaSeleccionada.getFecha().toString());
            String[] prioridades = { "ALTA", "MEDIA", "BAJA" };
            JComboBox<String> comboPrioridad = new JComboBox<>(prioridades);
            comboPrioridad.setSelectedItem(tareaSeleccionada.getPrioridad());

            JPanel panel = new JPanel();
            panel.add(new JLabel("NUEVO NOMBRE:"));
            panel.add(campoNombre);
            panel.add(new JLabel("NUEVA FECHA (AÑO-MES-DIA):"));
            panel.add(campoFecha);
            panel.add(new JLabel("NUEVA PRIORIDAD:"));
            panel.add(comboPrioridad);

            int option = JOptionPane.showConfirmDialog(ventanaPrincipal, panel, "MODIFICAR TAREA",
                    JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String nuevoNombre = campoNombre.getText();
                String nuevaFechaString = campoFecha.getText();
                String nuevaPrioridad = (String) comboPrioridad.getSelectedItem();
                try {
                    tareaSeleccionada.setNombre(nuevoNombre);
                    tareaSeleccionada.setFecha(LocalDate.parse(nuevaFechaString));
                    tareaSeleccionada.setPrioridad(nuevaPrioridad);
                    tareasUsuario.guardarTareas(); // Guarda los cambios
                    modeloTareas.set(listaTareas.getSelectedIndex(), tareaSeleccionada); // Actualiza la lista
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "ERROR AL MODIFICAR LA TAREA: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor seleccione una tarea para modificar.");
        }
    }

    /**
     * Método para imprimir la lista de tareas a un archivo de texto.
     * Este método genera un archivo de texto que contiene todas las tareas actuales
     * del usuario.
     */
    private void imprimirLista() {
        String destinoTXT = "LISTA DE TAREAS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinoTXT))) {
            writer.write("LISTA DE TAREAS:");
            writer.newLine();
            for (Tarea tarea : tareasUsuario.getTareas()) {
                writer.write(tarea.toString()); // Escribe cada tarea en el archivo
                writer.newLine();
            }
            JOptionPane.showMessageDialog(ventanaPrincipal, "La lista de tareas ha sido exportada a " + destinoTXT);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al generar el archivo de texto: " + e.getMessage());
        }
    }

    /**
     * Método para finalizar el programa.
     * Este método cierra la ventana y finaliza la ejecución de la aplicación.
     */
    private void finalizarPrograma() {
        ventanaPrincipal.dispose(); // Cierra la ventana
        System.exit(0); // Finaliza la aplicación
    }
}
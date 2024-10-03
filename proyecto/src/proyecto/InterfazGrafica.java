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
 * Clase que representa la interfaz gráfica de una aplicación de lista de tareas.
 * Permite a los usuarios iniciar sesión, registrarse y gestionar sus tareas.
 */
public class InterfazGrafica {
    private JFrame ventanaPrincipal;
    private JList<Tarea> listaTareas;
    private DefaultListModel<Tarea> modeloTareas;
    private ListaDeTareas tareasUsuario;
    private JTextField campoUsuario;
    private JTextField campoContraseña;

    private int anchoCampoLogin = 10;
    private int anchoCampoTarea = 10;
    /**
     * Constructor que inicializa la interfaz gráfica.
     */
    public InterfazGrafica() {
        ventanaPrincipal = new JFrame("LISTA DE TAREAS");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(400, 100);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.getContentPane().setBackground(new Color(240, 240, 240));

        modeloTareas = new DefaultListModel<>();
        listaTareas = new JList<>(modeloTareas);
        listaTareas.setBackground(new Color(255, 255, 255));
        listaTareas.setForeground(new Color(0, 0, 0));

        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(220, 220, 220));
        campoUsuario = new JTextField(anchoCampoLogin);
        campoContraseña = new JTextField(anchoCampoLogin);
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrar");

        btnIniciarSesion.setBackground(new Color(60, 120, 240));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnRegistrar.setBackground(new Color(60, 120, 240));
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
     * Método que maneja el inicio de sesión de un usuario.
     * Verifica las credenciales y carga la lista de tareas del usuario si son correctas.
     */
    private void iniciarSesion() {
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();
        List<Usuario> usuarios = Usuario.cargarUsuarios();

        for (Usuario user : usuarios) {
            if (user.getNombre().equals(usuario) && user.getContraseña().equals(contraseña)) {
                tareasUsuario = user.getListaDeTareas();
                mostrarListaDeTareas();
                return;
            }
        }
        JOptionPane.showMessageDialog(ventanaPrincipal, "Usuario o contraseña incorrectos.");
    }
    /**
     * Método que maneja el registro de un nuevo usuario.
     * Guarda el nuevo usuario y carga su lista de tareas.
     */
    private void registrarUsuario() {
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();

        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            Usuario nuevoUsuario = new Usuario(usuario, contraseña);
            nuevoUsuario.guardarUsuarios();
            tareasUsuario = nuevoUsuario.getListaDeTareas();
            mostrarListaDeTareas();
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor ingrese usuario y contraseña.");
        }
    }
    /**
    * Muestra la lista de tareas del usuario en la interfaz gráfica.
    * Configura los botones y paneles necesarios para gestionar las tareas.
    */
    private void mostrarListaDeTareas() {
        ventanaPrincipal.getContentPane().removeAll();
        ventanaPrincipal.setSize(650, 450);
        JButton btnAñadir = new JButton("AÑADIR TAREA");
        JButton btnFinalizar = new JButton("FINALIZAR");
        JButton btnCompletar = new JButton("COMPLETAR TAREA");
        JButton btnEliminar = new JButton("ELIMINAR TAREA");
        JButton btnModificar = new JButton("MODIFICAR TAREA");
        JButton btnImprimir = new JButton("IMPRIMIR LISTA");

        btnAñadir.setBackground(new Color(60, 120, 240));
        btnAñadir.setForeground(Color.WHITE);
        btnCompletar.setBackground(new Color(60, 120, 240));
        btnCompletar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(60, 120, 240));
        btnEliminar.setForeground(Color.WHITE);
        btnModificar.setBackground(new Color(60, 120, 240));
        btnModificar.setForeground(Color.WHITE);
        btnImprimir.setBackground(new Color(60, 120, 240));
        btnImprimir.setForeground(Color.WHITE);
        btnFinalizar.setBackground(new Color(240, 60, 60));
        btnFinalizar.setForeground(Color.WHITE);

        btnAñadir.addActionListener(e -> añadirTarea());
        btnCompletar.addActionListener(e -> completarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());
        btnModificar.addActionListener(e -> modificarTarea());
        btnImprimir.addActionListener(e -> imprimirLista());
        btnFinalizar.addActionListener(e -> finalizarPrograma());

        modeloTareas.clear();
        for (Tarea tarea : tareasUsuario.getTareas()) {
            modeloTareas.addElement(tarea);
        }

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(220, 220, 220));
        panelSuperior.add(btnAñadir);
        panelSuperior.add(btnFinalizar);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(220, 220, 220));
        panelInferior.add(btnCompletar);
        panelInferior.add(btnEliminar);
        panelInferior.add(btnModificar);
        panelInferior.add(btnImprimir);

        ventanaPrincipal.add(panelSuperior, BorderLayout.NORTH);
        ventanaPrincipal.add(panelCentral, BorderLayout.CENTER);
        ventanaPrincipal.add(panelInferior, BorderLayout.SOUTH);

        ventanaPrincipal.revalidate();
        ventanaPrincipal.repaint();
    }
    /**
    * Añade una nueva tarea a la lista de tareas del usuario.
    * Solicita al usuario que ingrese los detalles de la tarea a través de un cuadro de diálogo.
    * Si los datos son válidos, crea una nueva tarea y la agrega a la lista.
    */
    private void añadirTarea() {
        JTextField campoNombre = new JTextField(10);
        JTextField campoFecha = new JTextField(10);
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
                modeloTareas.addElement(tarea);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(ventanaPrincipal, "ERROR AL AÑADIR TAREA: " + e.getMessage());
            }
        }
    }
    /**
    * Marca la tarea seleccionada como completada.
    * Si no se ha seleccionado ninguna tarea, muestra un mensaje de error.
    */
    private void completarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            tareaSeleccionada.setCompletada(true);
            modeloTareas.removeElement(tareaSeleccionada);
            modeloTareas.addElement(tareaSeleccionada);
            tareasUsuario.guardarTareas();
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "POR FAVOR SELECCIONE UNA TAREA PARA COMPLETAR.");
        }
    }
    /**
    * Elimina la tarea seleccionada de la lista de tareas del usuario.
    * Si no se ha seleccionado ninguna tarea, muestra un mensaje de error.
    */
    private void eliminarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            tareasUsuario.eliminarTarea(tareaSeleccionada);
            modeloTareas.removeElement(tareaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "POR FAVOR SELECCIONE UNA TAREA PARA ELIMINAR");
        }
    }
    /**
    * Modifica los detalles de la tarea seleccionada.
    * Permite al usuario cambiar el nombre, la fecha y la prioridad de la tarea seleccionada.
    * Si no se ha seleccionado ninguna tarea, muestra un mensaje de error.
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
                    tareasUsuario.guardarTareas();
                    modeloTareas.set(listaTareas.getSelectedIndex(), tareaSeleccionada);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "ERROR AL MODIFICAR LA TAREA: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor seleccione una tarea para modificar.");
        }
    }
    /**
    * Imprime la lista de tareas del usuario en un archivo de texto.
    * Guarda la lista de tareas en un archivo llamado "LISTA DE TAREAS.txt".
    * Muestra un mensaje de confirmación si la operación es exitosa, o un mensaje de error si falla.
    */
    private void imprimirLista() {
        String destinoTXT = "LISTA DE TAREAS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinoTXT))) {
            writer.write("LISTA DE TAREAS:");
            writer.newLine();
            for (Tarea tarea : tareasUsuario.getTareas()) {
                writer.write(tarea.toString());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(ventanaPrincipal, "La lista de tareas ha sido exportada a " + destinoTXT);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al generar el archivo de texto: " + e.getMessage());
        }
    }
    /**
     * Finaliza el programa cerrando la ventana principal y terminando la ejecución.
    */
    private void finalizarPrograma() {
        ventanaPrincipal.dispose();
        System.exit(0);
    }
}
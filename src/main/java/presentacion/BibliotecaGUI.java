package presentacion;

import javax.swing.*;
import java.awt.*;
import logica.clases.Bibliotecario;
import logica.clases.Lector;
import logica.clases.Usuario;
import logica.manejadores.UsuarioHandler;

public class BibliotecaGUI extends JFrame {

    private JDesktopPane desktop; // donde van los internal frames

    public BibliotecaGUI() {
        setTitle("Biblioteca Comunitaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Desktop para manejar internal frames
        desktop = new JDesktopPane();
        add(desktop);

        // Menú principal
        JMenuBar menuBar = new JMenuBar();
        JMenu menuUsuarios = new JMenu("Usuarios");
        JMenuItem registrarLector = new JMenuItem("Registrar Lector");

        registrarLector.addActionListener(e -> abrirFormularioRegistro());

        menuUsuarios.add(registrarLector);
        menuBar.add(menuUsuarios);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void abrirFormularioRegistro() {
        JInternalFrame frame = new JInternalFrame("Registro de Usuario", true, true, true, true);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));

        panelForm.add(new JLabel("Tipo de usuario:"));
        JComboBox<String> comboTipo = new JComboBox<>(new String[] { "Lector", "Bibliotecario" });
        panelForm.add(comboTipo);

        panelForm.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField();
        panelForm.add(txtEmail);

        // Campos específicos
        panelForm.add(new JLabel("Dirección:"));
        JTextField txtDireccion = new JTextField();
        panelForm.add(txtDireccion);

        panelForm.add(new JLabel("Zona:"));
        JComboBox<String> comboZona = new JComboBox<>(new String[] {
                "BIBLIOTECA_CENTRAL",
                "SUCURSAL_ESTE",
                "SUCURSAL_OESTE",
                "BIBLIOTECA_INFANTIL",
                "ARCHIVO_GENERAL"
        });
        panelForm.add(comboZona);

        panelForm.add(new JLabel("Estado:"));
        JComboBox<String> comboEstado = new JComboBox<>(new String[] { "ACTIVO", "SUSPENDIDO" });
        panelForm.add(comboEstado);

        panelForm.add(new JLabel("Nro. Empleado:"));
        JTextField txtNumEmp = new JTextField();
        panelForm.add(txtNumEmp);

        // Mostrar/ocultar campos según tipo
        comboTipo.addActionListener(e -> {
            boolean esLector = comboTipo.getSelectedItem().equals("Lector");
            txtDireccion.setEnabled(esLector);
            comboZona.setEnabled(esLector);
            comboEstado.setEnabled(esLector);
            txtNumEmp.setEnabled(!esLector);
        });
        comboTipo.setSelectedIndex(0); // Inicializa

        frame.add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String correo = txtEmail.getText();

                if (comboTipo.getSelectedItem().equals("Lector")) {
                    String direccion = txtDireccion.getText();
                    String zonaStr = (String) comboZona.getSelectedItem();
                    String estadoStr = (String) comboEstado.getSelectedItem();
                    datatypes.Zonas zona = datatypes.Zonas.valueOf(zonaStr);
                    datatypes.EstadosU estado = datatypes.EstadosU.valueOf(estadoStr);

                    Lector lector = new Lector();
                    lector.setNombre(nombre);
                    lector.setCorreo(correo);
                    lector.setDireccion(direccion);
                    lector.setZona(zona);
                    lector.setEstadoUsuario(estado);
                    lector.setFechaIngreso(new java.util.Date());

                    UsuarioHandler.getInstancia().agregarUsuarioH(lector);
                    JOptionPane.showMessageDialog(frame, "Lector registrado correctamente.");
                } else {
                    String numEmp = txtNumEmp.getText();
                    Bibliotecario biblio = new Bibliotecario();
                    biblio.setNombre(nombre);
                    biblio.setCorreo(correo);
                    biblio.setIdEmp(Integer.parseInt(numEmp));
                    UsuarioHandler.getInstancia().agregarUsuarioH(biblio);
                    JOptionPane.showMessageDialog(frame, "Bibliotecario registrado correctamente.");
                }
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al registrar usuario: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> frame.dispose());

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        frame.add(panelBotones, BorderLayout.SOUTH);

        desktop.add(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BibliotecaGUI::new);
    }
}

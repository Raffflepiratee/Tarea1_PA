package presentacion;

import javax.swing.*;
import java.awt.*;

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
        // Crear un internal frame
        JInternalFrame frame = new JInternalFrame("Registro de Lector", true, true, true, true);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Panel central con formulario
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));

        panelForm.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField();
        panelForm.add(txtEmail);

        panelForm.add(new JLabel("Dirección:"));
        JTextField txtDireccion = new JTextField();
        panelForm.add(txtDireccion);

        panelForm.add(new JLabel("Zona:"));
        JComboBox<String> comboZona = new JComboBox<>(new String[] {
                "BIBLIOTECA CENTRAL",
                "SUCURSAL ESTE",
                "SUCURSAL OESTE",
                "BIBLIOTECA INFANTIL",
                "ARCHIVO GENERAL"
        });
        panelForm.add(comboZona);

        panelForm.add(new JLabel("Estado:"));
        JComboBox<String> comboEstado = new JComboBox<>(new String[] { "ACTIVO", "SUSPENDIDO" });
        panelForm.add(comboEstado);

        frame.add(panelForm, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> {
            // Simulación de guardado
            JOptionPane.showMessageDialog(frame,
                    "Lector registrado:\n" +
                            "Nombre: " + txtNombre.getText() + "\n" +
                            "Email: " + txtEmail.getText() + "\n" +
                            "Dirección: " + txtDireccion.getText() + "\n" +
                            "Zona: " + comboZona.getSelectedItem() + "\n" +
                            "Estado: " + comboEstado.getSelectedItem());
            frame.dispose();
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

package presentacion;

import javax.swing.*;
import java.awt.*;

public class BibliotecaGUI extends JFrame {

    private JDesktopPane desktop;

    public BibliotecaGUI() {
        setTitle("Biblioteca Comunitaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el desktop pane
        desktop = new JDesktopPane();
        add(desktop);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuUsuarios = new JMenu("Usuarios");
        JMenuItem registrarUsuario = new JMenuItem("Registrar Usuario");

        registrarUsuario.addActionListener(e -> abrirFormularioRegistro());

        menuUsuarios.add(registrarUsuario);
        menuBar.add(menuUsuarios);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void abrirFormularioRegistro() {
        JInternalFrame frame = new JInternalFrame("Registro de Usuario", true, true, true, true);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        // Nombre
        panelForm.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        JTextField txtNombre = new JTextField();
        panelForm.add(txtNombre, gbc);

        // Correo
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        JTextField txtCorreo = new JTextField();
        panelForm.add(txtCorreo, gbc);

        // Tipo de usuario
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Tipo de usuario:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> comboTipo = new JComboBox<>(new String[] { "Lector", "Bibliotecario" });
        panelForm.add(comboTipo, gbc);

        // Panel para campos de Lector
        JPanel panelLector = new JPanel(new GridLayout(3, 2, 5, 5));
        panelLector.add(new JLabel("Dirección:"));
        JTextField txtDireccion = new JTextField();
        panelLector.add(txtDireccion);

        panelLector.add(new JLabel("Zona:"));
        JComboBox<datatypes.Zonas> comboZona = new JComboBox<>(datatypes.Zonas.values());
        panelLector.add(comboZona);

        panelLector.add(new JLabel("Estado:"));
        JComboBox<String> comboEstado = new JComboBox<>(new String[] { "ACTIVO", "SUSPENDIDO" });
        panelLector.add(comboEstado);

        // Label para mostrar la zona seleccionada
        JLabel lblZonaSeleccionada = new JLabel("Selecciona una zona");
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panelForm.add(lblZonaSeleccionada, gbc);

        // Evento para mostrar el string seleccionado
        comboZona.addActionListener(e -> {
            datatypes.Zonas zonaSeleccionada = (datatypes.Zonas) comboZona.getSelectedItem();
            lblZonaSeleccionada.setText("Zona seleccionada: " + zonaSeleccionada.name());
        });

        // Panel para campos de Bibliotecario
        JPanel panelBiblio = new JPanel(new GridLayout(1, 2, 5, 5));
        panelBiblio.add(new JLabel("ID Empleado:"));
        JTextField txtIdEmp = new JTextField();
        panelBiblio.add(txtIdEmp);

        // Panel dinámico
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        JPanel panelDinamico = new JPanel(new CardLayout());
        panelDinamico.add(panelLector, "Lector");
        panelDinamico.add(panelBiblio, "Bibliotecario");
        panelForm.add(panelDinamico, gbc);

        // Cambia el panel según el tipo seleccionado
        comboTipo.addActionListener(e -> {
            CardLayout cl = (CardLayout) (panelDinamico.getLayout());
            cl.show(panelDinamico, (String) comboTipo.getSelectedItem());
        });
        // Inicializa mostrando Lector
        ((CardLayout) panelDinamico.getLayout()).show(panelDinamico, "Lector");

        frame.add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();

                logica.controladores.UsuarioController controller = new logica.controladores.UsuarioController();

                if (comboTipo.getSelectedItem().equals("Lector")) {
                    String direccion = txtDireccion.getText();
                    datatypes.Zonas zona = (datatypes.Zonas) comboZona.getSelectedItem();
                    String estadoStr = (String) comboEstado.getSelectedItem();
                    datatypes.EstadosU estado = datatypes.EstadosU.valueOf(estadoStr);

                    datatypes.DtLector dtLector = new datatypes.DtLector(
                        nombre,
                        correo,
                        new java.util.Date(), // fechaIngreso
                        estado,
                        zona,
                        direccion
                    );
                    controller.agregarUsuario(dtLector);
                    JOptionPane.showMessageDialog(frame, "Lector registrado correctamente.");
                } else {
                    int idEmp = Integer.parseInt(txtIdEmp.getText());
                    datatypes.DtBibliotecario dtBiblio = new datatypes.DtBibliotecario(
                        nombre,
                        correo,
                        idEmp
                    );
                    controller.agregarUsuario(dtBiblio);
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
package presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import logica.clases.*;
import logica.manejadores.UsuarioHandler;
import logica.controladores.*;
import datatypes.*;

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

        // Menu prestamo
        JMenu menuPrestamo = new JMenu("Préstamo");
        JMenuItem registrarPrestamo = new JMenuItem("Registrar Préstamo");

        registrarPrestamo.addActionListener(e -> abrirFormularioPrestamo());
        menuPrestamo.add(registrarPrestamo);
        menuBar.add(menuPrestamo);

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

    public void abrirFormularioPrestamo() {
        JInternalFrame frame = new JInternalFrame("Registro de Préstamo", true, true, true, true);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 8, 2, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));

        JTextField txtIdMaterial = new JTextField();
        panelCampos.add(new JLabel("ID Material:"));
        panelCampos.add(txtIdMaterial);

        JTextField txtCorreoLector = new JTextField();
        panelCampos.add(new JLabel("Correo Lector:"));
        panelCampos.add(txtCorreoLector);

        JTextField txtCorreoBiblio = new JTextField();
        panelCampos.add(new JLabel("Correo Bibliotecario:"));
        panelCampos.add(txtCorreoBiblio);

        JTextField txtFechaSoli = new JTextField();
        panelCampos.add(new JLabel("Fecha Solicitud (dd/mm/yyyy):"));
        panelCampos.add(txtFechaSoli);

        JTextField txtFechaDev = new JTextField();
        panelCampos.add(new JLabel("Fecha Devolución (dd/mm/yyyy):"));
        panelCampos.add(txtFechaDev);

        JComboBox<String> comboEstado = new JComboBox<>(new String[] { "PENDIENTE", "EN_CURSO", "DEVUELTO" });
        panelCampos.add(new JLabel("Estado:"));
        panelCampos.add(comboEstado);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelForm.add(panelCampos, gbc);

        frame.add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> {
            try {
                int idMaterial = Integer.parseInt(txtIdMaterial.getText());
                String correoLector = txtCorreoLector.getText();
                String correoBiblio = txtCorreoBiblio.getText();
                String fechaSoliStr = txtFechaSoli.getText();
                String fechaDevStr = txtFechaDev.getText();
                String estadoStr = (String) comboEstado.getSelectedItem();
                EstadosP estado = EstadosP.valueOf(estadoStr);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaSoli = sdf.parse(fechaSoliStr);
                Date fechaDev = sdf.parse(fechaDevStr);

                DtPrestamo dtPrestamo = new DtPrestamo(fechaSoli, estado, fechaDev);
                PrestamoController pc = new PrestamoController();
                pc.agregarPrestamo(dtPrestamo, correoLector, correoBiblio, idMaterial);
                JOptionPane.showMessageDialog(frame, "Préstamo registrado correctamente.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al registrar préstamo: " + ex.getMessage());
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

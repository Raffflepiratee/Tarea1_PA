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
        JMenuItem listarUsuarios = new JMenuItem("Listar Usuarios");

        registrarUsuario.addActionListener(e -> abrirFormularioRegistro());
        listarUsuarios.addActionListener(e -> abrirListadoUsuarios());

        menuUsuarios.add(registrarUsuario);
        menuUsuarios.add(listarUsuarios); 
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

//OJO EL PIOJO AQUI
private void abrirListadoUsuarios() {
    JInternalFrame frame = new JInternalFrame("Listado de Usuarios", true, true, true, true);
    frame.setSize(500, 400);
    frame.setLayout(new BorderLayout());

    // Obtener usuarios
    logica.controladores.UsuarioController controller = new logica.controladores.UsuarioController();
    java.util.List<logica.clases.Usuario> usuarios = controller.obtenerUsuarios();

    // Columnas
    String[] columnas = {"Nombre", "Correo", "Opciones"};
    Object[][] data = new Object[usuarios.size()][columnas.length];

    for (int i = 0; i < usuarios.size(); i++) {
        logica.clases.Usuario u = usuarios.get(i);
        data[i][0] = u.getNombre();
        data[i][1] = u.getCorreo();
        data[i][2] = "Opciones";
    }

    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, columnas) {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Solo el botón es editable
            return column == 2;
        }
    };

    JTable table = new JTable(model);

    // Botón para opciones
    table.getColumn("Opciones").setCellRenderer(new ButtonRenderer());
    table.getColumn("Opciones").setCellEditor(new ButtonEditor(new JCheckBox(), (row) -> {
        logica.clases.Usuario u = usuarios.get(row);
        if (u instanceof logica.clases.Lector) {
            // Mostrar opciones para Lector
            Object[] options = {"Cambiar Zona", "Cambiar Estado", "Cancelar"};
            int choice = JOptionPane.showOptionDialog(
                frame,
                "Opciones para el lector " + u.getNombre(),
                "Opciones de Lector",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );
            if (choice == 0) { // Cambiar Zona
                logica.clases.Lector lector = (logica.clases.Lector) u;
                datatypes.Zonas nuevaZona = (datatypes.Zonas) JOptionPane.showInputDialog(
                    frame,
                    "Selecciona nueva zona:",
                    "Cambiar Zona",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    datatypes.Zonas.values(),
                    lector.getZona()
                );
                if (nuevaZona != null) {
                    lector.setZona(nuevaZona);
                    JOptionPane.showMessageDialog(frame, "Zona cambiada a: " + nuevaZona);
                }
            } else if (choice == 1) { // Cambiar Estado
                logica.clases.Lector lector = (logica.clases.Lector) u;
                datatypes.EstadosU nuevoEstado = (datatypes.EstadosU) JOptionPane.showInputDialog(
                    frame,
                    "Selecciona nuevo estado:",
                    "Cambiar Estado",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    datatypes.EstadosU.values(),
                    lector.getEstadoUsuario()
                );
                if (nuevoEstado != null) {
                    lector.setEstadoUsuario(nuevoEstado);
                    JOptionPane.showMessageDialog(frame, "Estado cambiado a: " + nuevoEstado);
                }
            }
        } else {
            // Solo mostrar info para Bibliotecario
            JOptionPane.showMessageDialog(
                frame,
                "Bibliotecario\nNombre: " + u.getNombre() + "\nCorreo: " + u.getCorreo(),
                "Información",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }));

    JScrollPane scroll = new JScrollPane(table);
    frame.add(scroll, BorderLayout.CENTER);

    desktop.add(frame);
    frame.setVisible(true);
}

// Renderizador y editor de botón para JTable
class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRenderer() { setOpaque(true); }
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private java.util.function.IntConsumer onClick;
    private int row;

    public ButtonEditor(JCheckBox checkBox, java.util.function.IntConsumer onClick) {
        super(checkBox);
        this.onClick = onClick;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.row = row;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            onClick.accept(row);
        }
        isPushed = false;
        return label;
    }
}
//HASTA ACA VA LO NUEVO

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BibliotecaGUI::new);
    }
}
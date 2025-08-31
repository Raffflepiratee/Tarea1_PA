package presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.IntConsumer;

/* import java.util.ArrayList; */
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
        JMenuItem listarPrestamos = new JMenuItem("Listar Préstamos");
       

        registrarPrestamo.addActionListener(e -> abrirFormularioPrestamo());
        menuPrestamo.add(registrarPrestamo);

        listarPrestamos.addActionListener(e -> abrirListadoPrestamos());
        menuPrestamo.add(listarPrestamos);

        menuBar.add(menuPrestamo);

        //Menu control y seguimiento
        JMenu menuControl = new JMenu("Control y Seguimiento");
        JMenuItem listarPrestamosBibliotecario = new JMenuItem("Listar préstamos por bibliotecario");
        JMenuItem listarReporteZonal = new JMenuItem("Listar reporte zonal");
        JMenuItem reporteMateriales = new JMenuItem("Materiales con más préstamos pendientes");
        
        listarPrestamosBibliotecario.addActionListener(e -> abrirListadoPrestamosBibliotecario());
        menuControl.add(listarPrestamosBibliotecario);

        listarReporteZonal.addActionListener(e -> abrirListadoReporteZonal());
        menuControl.add(listarReporteZonal);

        reporteMateriales.addActionListener(e -> abrirMaterialesConMasPrestamosPendientes());
        menuControl.add(reporteMateriales);

        menuBar.add(menuControl);

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

                
                PrestamoController pc = new PrestamoController();
                pc.agregarPrestamo(fechaSoli, fechaDev, estado, correoLector, correoBiblio, idMaterial);
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

    private void abrirListadoPrestamos() {
        JInternalFrame frame = new JInternalFrame("Listado de Préstamos", true, true, true, true);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());

        // Obtener préstamos
        PrestamoController pC = new PrestamoController();
        List<DtPrestamo> prestamos = pC.obtenerDtPrestamos();

        // Columnas
        String[] columnas = {"ID", "Fecha Solicitud", "Estado", "Fecha Devolución", "Lector", "Bibliotecario", "Material", "Opciones"};
        Object[][] data = new Object[prestamos.size()][columnas.length];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < prestamos.size(); i++) {
            DtPrestamo p = prestamos.get(i);
            data[i][0] = p.getIdPrestamo();
            data[i][1] = sdf.format(p.getFechaSoli());
            data[i][2] = p.getEstadoPres();
            data[i][3] = sdf.format(p.getFechaDev());
            data[i][4] = p.getLector();
            data[i][5] = p.getBibliotecario();
            data[i][6] = p.getMaterial();
            data[i][7] = "Opciones";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo el botón es editable
                return column == 7;
            }
        };

        JTable table = new JTable(model);

        // Botón para opciones
        table.getColumn("Opciones").setCellRenderer(new ButtonRenderer());
        table.getColumn("Opciones").setCellEditor(new ButtonEditor(new JCheckBox(), (row) -> {
            DtPrestamo p = prestamos.get(row);

            JDialog editDialog = new JDialog(SwingUtilities.getWindowAncestor(desktop), "Editar Préstamo ID: " + p.getIdPrestamo(), Dialog.ModalityType.APPLICATION_MODAL);
            editDialog.setSize(600, 300);
            editDialog.setLayout(new BorderLayout());

            JPanel panelCampos = new JPanel(new GridLayout(6, 2, 10, 10));

            panelCampos.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            // ID Material
            panelCampos.add(new JLabel("ID Material: " + p.getMaterial()));
            JButton btnEditMaterial = new JButton("Editar");
            btnEditMaterial.addActionListener(e -> {
                String nuevoId = JOptionPane.showInputDialog(editDialog, "Nuevo ID Material:", p.getMaterial());
                if (nuevoId != null && !nuevoId.isEmpty()) {
                    pC.cambiarMaterialPrestamo(p, Integer.parseInt(nuevoId));
                }
            });
            panelCampos.add(btnEditMaterial);

            // Correo Lector
            panelCampos.add(new JLabel("Correo Lector: " + p.getLector()));
            JButton btnEditLector = new JButton("Editar");
            btnEditLector.addActionListener(e -> {
                String nuevoCorreo = JOptionPane.showInputDialog(editDialog, "Nuevo correo lector:", p.getLector());
                if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
                    pC.cambiarCorreoLectorPrestamo(p, nuevoCorreo);
                }
            });
            panelCampos.add(btnEditLector);

            // Correo Bibliotecario
            panelCampos.add(new JLabel("Correo Bibliotecario: " + p.getBibliotecario()));
            JButton btnEditBiblio = new JButton("Editar");
            btnEditBiblio.addActionListener(e -> {
                String nuevoCorreo = JOptionPane.showInputDialog(editDialog, "Nuevo correo bibliotecario:", p.getBibliotecario());
                if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
                    pC.cambiarCorreoBibliotecarioPrestamo(p, nuevoCorreo);
                }
            });
            panelCampos.add(btnEditBiblio);

            // Fecha Solicitud
            panelCampos.add(new JLabel("Fecha Solicitud: " + sdf.format(p.getFechaSoli())));
            JButton btnEditFechaSoli = new JButton("Editar");
            btnEditFechaSoli.addActionListener(e -> {
                String nuevaFecha = JOptionPane.showInputDialog(editDialog, "Nueva fecha solicitud (dd/MM/yyyy):", sdf.format(p.getFechaSoli()));
                try {
                    if (nuevaFecha != null && !nuevaFecha.isEmpty()) {
                        Date fecha = sdf.parse(nuevaFecha);
                        pC.cambiarFechaSolicitudPrestamo(p, fecha);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(editDialog, "Formato de fecha inválido.");
                }
            });
            panelCampos.add(btnEditFechaSoli);

            // Fecha Devolución
            panelCampos.add(new JLabel("Fecha Devolución: " + sdf.format(p.getFechaDev())));
            JButton btnEditFechaDev = new JButton("Editar");
            btnEditFechaDev.addActionListener(e -> {
                String nuevaFecha = JOptionPane.showInputDialog(editDialog, "Nueva fecha devolución (dd/MM/yyyy):", sdf.format(p.getFechaDev()));
                try {
                    if (nuevaFecha != null && !nuevaFecha.isEmpty()) {
                        Date fecha = sdf.parse(nuevaFecha);
                        pC.cambiarFechaDevolucionPrestamo(p, fecha);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(editDialog, "Formato de fecha inválido.");
                }
            });
            panelCampos.add(btnEditFechaDev);

            // Estado
            panelCampos.add(new JLabel("Estado: " + p.getEstadoPres()));
            JButton btnEditEstado = new JButton("Editar");
            btnEditEstado.addActionListener(e -> {
                EstadosP nuevoEstado = (EstadosP) JOptionPane.showInputDialog(
                    editDialog,
                    "Selecciona nuevo estado:",
                    "Editar Estado",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    EstadosP.values(),
                    p.getEstadoPres()
                );
                if (nuevoEstado != null) {
                    pC.cambiarEstadoPrestamo(p, nuevoEstado);
                }
            });
            panelCampos.add(btnEditEstado);

            editDialog.add(panelCampos, BorderLayout.CENTER);
            editDialog.setVisible(true);
            desktop.add(editDialog);
        }));

        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll, BorderLayout.CENTER);

        desktop.add(frame);
        frame.setVisible(true);
    }

    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRenderer() { setOpaque(true); }
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
        }
    }

public void abrirListadoPrestamosBibliotecario(){
    JInternalFrame frame = new JInternalFrame("Listado de Préstamos por Bibliotecario", true, true, true, true);
    frame.setSize(800, 600);
    frame.setLayout(new BorderLayout());

    JPanel panel = new JPanel();
    JLabel label = new JLabel("ID Empleado:");
    JTextField textField = new JTextField(10);
    JButton button = new JButton("Buscar");
    panel.add(label);
    panel.add(textField);
    panel.add(button);
    frame.add(panel, BorderLayout.NORTH);

    button.addActionListener(e -> {
        int idEmp = Integer.parseInt(textField.getText());
        PrestamoController pC = new PrestamoController();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<DtPrestamo> prestamos = pC.obtenerDtPrestamoBibliotecario(idEmp);

        String[] columnas = {"ID", "Fecha Solicitud", "Estado", "Fecha Devolución", "Lector", "Bibliotecario", "Material"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        for (DtPrestamo p : prestamos) {
            model.addRow(new Object[]{
                p.getIdPrestamo(),
                sdf.format(p.getFechaSoli()),
                p.getEstadoPres(),
                sdf.format(p.getFechaDev()),
                p.getLector(),
                p.getBibliotecario(),
                p.getMaterial()
            });
        }
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll, BorderLayout.CENTER);
    });

    desktop.add(frame);
    frame.setVisible(true);
}

public void abrirListadoReporteZonal(){
    JInternalFrame frame = new JInternalFrame("Listado de Reporte Zonal", true, true, true, true);
    frame.setSize(800, 600);
    frame.setLayout(new BorderLayout());

    String[] columnas = {"Zona", "Cantidad de Préstamos", "Detalles"};
    DefaultTableModel model = new DefaultTableModel(columnas, 0);

    PrestamoController pC = new PrestamoController();

    for(datatypes.Zonas zona : datatypes.Zonas.values()){
        List<DtPrestamo> prestamos = pC.obtenerDtPrestamosPorZona(zona);
        model.addRow(new Object[]{zona, prestamos.size(), "Detalles"});
    }

    JTable table = new JTable(model);

    table.getColumn("Detalles").setCellRenderer(new ButtonRenderer());
    table.getColumn("Detalles").setCellEditor(new ButtonEditor(new JCheckBox(), (row) -> {
        datatypes.Zonas zona = (datatypes.Zonas) model.getValueAt(row, 0);
        List<DtPrestamo> prestamos = pC.obtenerDtPrestamosPorZona(zona);

        JInternalFrame detallesFrame = new JInternalFrame("Detalles de Préstamos - " + zona, true, true, true, true);
        detallesFrame.setSize(600, 400);
        detallesFrame.setLayout(new BorderLayout());

        String[] columnasDetalles = {"ID", "Fecha Solicitud", "Estado", "Fecha Devolución", "Lector", "Bibliotecario", "Material"};
        DefaultTableModel modelDetalles = new DefaultTableModel(columnasDetalles, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (DtPrestamo p : prestamos) {
            modelDetalles.addRow(new Object[]{
                p.getIdPrestamo(),
                sdf.format(p.getFechaSoli()),
                p.getEstadoPres(),
                sdf.format(p.getFechaDev()),
                p.getLector(),
                p.getBibliotecario(),
                p.getMaterial()
            });
        }
        JTable tableDetalles = new JTable(modelDetalles);
        JScrollPane scrollDetalles = new JScrollPane(tableDetalles);
        detallesFrame.add(scrollDetalles, BorderLayout.CENTER);

        desktop.add(detallesFrame);
        detallesFrame.setVisible(true);
    }));

    JScrollPane scroll = new JScrollPane(table);
    frame.add(scroll, BorderLayout.CENTER);

    desktop.add(frame);
    frame.setVisible(true);
}

public void abrirMaterialesConMasPrestamosPendientes(){
    JInternalFrame frame = new JInternalFrame("Materiales con más préstamos pendientes", true, true, true, true);
    frame.setSize(600, 400);
    frame.setLayout(new BorderLayout());

    String[] columnas = {"Índice", "ID Material", "Cantidad de Préstamos Pendientes"};
    DefaultTableModel model = new DefaultTableModel(columnas, 0);

    PrestamoController pC = new PrestamoController();
    List<DtPrestamo> prestamosPendientes = pC.obtenerDtPrestamosPendientes();

    // Agrupar por idMaterial y contar
    java.util.Map<Integer, Integer> conteoPorMaterial = new java.util.HashMap<>();
    for (DtPrestamo p : prestamosPendientes) {
        int idMaterial = p.getMaterial();
        conteoPorMaterial.put(idMaterial, conteoPorMaterial.getOrDefault(idMaterial, 0) + 1);
    }

    // Ordenar por cantidad descendente
    java.util.List<java.util.Map.Entry<Integer, Integer>> listaOrdenada = new java.util.ArrayList<>(conteoPorMaterial.entrySet());
    listaOrdenada.sort((a, b) -> b.getValue().compareTo(a.getValue()));

    int indice = 1;
    for (java.util.Map.Entry<Integer, Integer> entry : listaOrdenada) {
        model.addRow(new Object[]{indice++, entry.getKey(), entry.getValue()});
    }

    JTable table = new JTable(model);
    JScrollPane scroll = new JScrollPane(table);
    frame.add(scroll, BorderLayout.CENTER);

    desktop.add(frame);
    frame.setVisible(true);
}



class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private IntConsumer onClick;
        private int row;

        public ButtonEditor(JCheckBox checkBox, java.util.function.IntConsumer onClick) {
            super(checkBox);
            this.onClick = onClick;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
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
        

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BibliotecaGUI::new);
    }
}

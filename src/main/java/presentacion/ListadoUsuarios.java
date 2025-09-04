package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.*;

import datatypes.*;
import interfaces.IMaterialController;
import interfaces.IUsuarioController;
import presentacion.BibliotecaGUI.ButtonEditor;
import presentacion.BibliotecaGUI.ButtonRenderer;

public class ListadoUsuarios extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private IUsuarioController usuarioCont;
    private JTable table;
    private DefaultTableModel model;
    private String[] columnas = { "Nombre", "Correo", "Tipo", "Mas Info" };
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ListadoUsuarios(IUsuarioController usuarioCont) {
        this.usuarioCont = usuarioCont;
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Listado de Usuarios");
        setBounds(100, 100, 600, 400);
        setLayout(new BorderLayout());

        Object[][] data = cargarDatosUsuarios();

        model = new DefaultTableModel(data, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        table = new JTable(model);
        // table.setRowHeight(30);
        table.getColumn("Mas Info").setCellRenderer(new ButtonRenderer());
        table.getColumn("Mas Info").setCellEditor(new ButtonEditor(new JCheckBox(), usuarioCont, this));

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> setVisible(false));
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCerrar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private Object[][] cargarDatosUsuarios() {
        List<DtUsuario> usuarios = usuarioCont.obtenerUsuarios();
        Object[][] data = new Object[usuarios.size()][4];

        for (int i = 0; i < usuarios.size(); i++) {
            DtUsuario u = usuarios.get(i);
            data[i][0] = u.getNombre();
            data[i][1] = u.getCorreo();
            if (u instanceof DtLector) {
                data[i][2] = "Lector";
            } else if (u instanceof DtBibliotecario) {
                data[i][2] = "Bibliotecario";
            }
            data[i][3] = "Mas Info";
        }
        return data;
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Mas info");
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private IUsuarioController usuarioCont;
        private JInternalFrame parentFrame;
        private int selectedRow;

        public ButtonEditor(JCheckBox checkBox, IUsuarioController usuarioCont, ListadoUsuarios parentFrame) {
            super(checkBox);
            this.usuarioCont = usuarioCont;
            this.parentFrame = parentFrame;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> masInfo());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            selectedRow = row;
            return button;
        }

        private void masInfo() {
            String correo = (String) model.getValueAt(selectedRow, 0);
            DtUsuario usuario = null;
            for (DtUsuario u : usuarioCont.obtenerUsuarios()) {
                if (u.getCorreo() == correo) {
                    usuario = u;
                    break;
                }
            }

            if (usuario == null) {
                JOptionPane.showMessageDialog(parentFrame, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                fireEditingStopped();
                return;
            }

            if (usuario instanceof DtLector) {
                DtLector lector = (DtLector) usuario;
                JPanel panel = new JPanel(new BorderLayout());
                JTextArea infoArea = new JTextArea(
                        "Nombre: " + lector.getNombre() + "\n" +
                                "Correo: " + lector.getCorreo() + "\n" +
                                "Fecha de Ingreso: " + dateFormat.format(lector.getFechaIngreso()) + "\n" +
                                "Zona: " + lector.getZona() + "\n" +
                                "Dirección: " + lector.getDireccion() + "\n" +
                                "Estado: " + lector.getEstadoUsuario().toString());
                infoArea.setEditable(false);
                panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

                JPanel botonesPanel = new JPanel();
                JButton btnZona = new JButton("Cambiar Zona");
                JButton btnEstado = new JButton("Modificar Estado");

                btnZona.addActionListener(e -> {
                    // ir al archivo CambiarZonaUsuario
                    CambiarZonaUsuario cambiarZona = new CambiarZonaUsuario(lector, usuarioCont);
                    parentFrame.getParent().add(cambiarZona);
                    cambiarZona.setVisible(true);
                });

                btnEstado.addActionListener(e -> {
                    // ir al archivo ModificarEstadoUsuario
                    ModificarEstadoUsuario modificarEstado = new ModificarEstadoUsuario(lector, usuarioCont);
                    parentFrame.getParent().add(modificarEstado);
                    modificarEstado.setVisible(true);
                });

                botonesPanel.add(btnZona);
                botonesPanel.add(btnEstado);
                panel.add(botonesPanel, BorderLayout.SOUTH);

                JOptionPane.showMessageDialog(parentFrame, panel, "Detalle del Lector",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (usuario instanceof DtBibliotecario) {
                DtBibliotecario bibliotecario = (DtBibliotecario) usuario;
                String info = "Nombre: " + bibliotecario.getNombre() + "\n" +
                        "Correo: " + bibliotecario.getCorreo() + "\n" +
                        "Numero de Empleado: " + bibliotecario.getIdEmp();
                JOptionPane.showMessageDialog(parentFrame, info, "Detalle del Bibliotecario",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            fireEditingStopped();
        }
    }
}

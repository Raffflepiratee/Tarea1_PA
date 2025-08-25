package presentacion;

import javax.swing.*;
import java.awt.*;
import logica.clases.*;
import logica.manejadores.UsuarioHandler;
import logica.controladores.*;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        // Menu Materiales
        JMenu menuMateriales = new JMenu("Materiales");
        JMenuItem registrarMaterial = new JMenuItem("Registrar Material");
        JMenuItem listarMateriales = new JMenuItem("Listar Materiales");

        registrarMaterial.addActionListener(e -> abrirFormularioRegistroMaterial());
        listarMateriales.addActionListener(e -> abrirListadoMateriales());

        menuMateriales.add(registrarMaterial);
        menuMateriales.add(listarMateriales);
        menuBar.add(menuMateriales);

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

    private void abrirFormularioRegistroMaterial() {
        JInternalFrame frame = new JInternalFrame("Registro de Material", true, true, true, true);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 8, 2, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(new JLabel("Tipo de material:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> comboTipo = new JComboBox<>(new String[] { "Libro", "Articulo Especial" });
        panelForm.add(comboTipo, gbc);

        // Panel para libros
        JPanel panelLibro = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField txtTitulo = new JTextField();
        panelLibro.add(new JLabel("Título:"));
        panelLibro.add(txtTitulo);

        JTextField txtCantPag = new JTextField();
        panelLibro.add(new JLabel("Cantidad de Páginas:"));
        panelLibro.add(txtCantPag);

        // Panel para articulos
        JPanel panelArticulo = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField txtDescripcion = new JTextField();
        panelArticulo.add(new JLabel("Descripcion:"));
        panelArticulo.add(txtDescripcion);

        JTextField txtPeso = new JTextField();
        panelArticulo.add(new JLabel("Peso (kg):"));
        panelArticulo.add(txtPeso);

        JTextField txtDimensiones = new JTextField();
        panelArticulo.add(new JLabel("Dimensiones Físicas:"));
        panelArticulo.add(txtDimensiones);

        // Panel dinamico
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(2, 8, 2, 8);
        JPanel panelDinamico = new JPanel(new CardLayout());
        panelDinamico.add(panelLibro, "Libro");
        panelDinamico.add(panelArticulo, "Articulo Especial");
        panelForm.add(panelDinamico, gbc);

        comboTipo.addActionListener(e -> {
            CardLayout cl = (CardLayout) (panelDinamico.getLayout());
            cl.show(panelDinamico, (String) comboTipo.getSelectedItem());
        });

        ((CardLayout) panelDinamico.getLayout()).show(panelDinamico, "Libro");

        frame.add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> {
            try {
                Date fechaRegistro = new Date();
                MaterialController mc = new MaterialController();

                if (comboTipo.getSelectedItem().equals("Libro")) {
                    String titulo = txtTitulo.getText();
                    int cantPag = Integer.parseInt(txtCantPag.getText());

                    DtLibro libro = new DtLibro(fechaRegistro, titulo, cantPag);
                    mc.agregarMaterial(libro);
                    JOptionPane.showMessageDialog(frame, "Libro registrado correctamente.");
                } else {
                    String descripcion = txtDescripcion.getText();
                    float peso = Float.parseFloat(txtPeso.getText());
                    float dimensiones = Float.parseFloat(txtDimensiones.getText());

                    DtArticuloEspecial articulo = new DtArticuloEspecial(fechaRegistro, descripcion, peso, dimensiones);
                    mc.agregarMaterial(articulo);
                    JOptionPane.showMessageDialog(frame, "Artículo Especial registrado correctamente.");
                }
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al registrar material: " + ex.getMessage());
            }
        });
        btnCancelar.addActionListener(e -> frame.dispose());
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        frame.add(panelBotones, BorderLayout.SOUTH);
        desktop.add(frame);
        frame.setVisible(true);
    }

    private void abrirListadoMateriales() {
        JInternalFrame frame = new JInternalFrame("Listado de Materiales", true, true, true, true);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());

        // Obtener materiales
        MaterialController mC = new MaterialController();
        List<DtMaterial> materiales = mC.obtenerMateriales();

        System.out.println("Materiales obtenidos: " + materiales.size());

        // Columnas
        String[] columnas = { "ID", "Fecha Registro", "Titulo", "Cantidad Pag", "Descripcion", "Peso", "DimFisica" };
        Object[][] data = new Object[materiales.size()][columnas.length];

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < materiales.size(); i++) {
            DtMaterial m = materiales.get(i);
            data[i][0] = m.getIdMaterial();
            data[i][1] = sdf.format(m.getFechaRegistro());
            if (m instanceof DtLibro) {
                DtLibro libro = (DtLibro) m;
                data[i][2] = libro.getTitulo();
                data[i][3] = libro.getCantPag();
                data[i][4] = "N/A";
                data[i][5] = "N/A";
                data[i][6] = "N/A";
            } else {
                DtArticuloEspecial art = (DtArticuloEspecial) m;
                data[i][2] = "N/A";
                data[i][3] = "N/A";
                data[i][4] = art.getDescripcion();
                data[i][5] = art.getPeso();
                data[i][6] = art.getDimFisica();
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll, BorderLayout.CENTER);

        desktop.add(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BibliotecaGUI::new);
    }
}

package presentacion;

import java.awt.BorderLayout;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import interfaces.IPrestamoController;
import datatypes.DtPrestamo;

public class MaterialesConMasPrestamos extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private IPrestamoController IpreCont;
    private DefaultTableModel model;

    public MaterialesConMasPrestamos(IPrestamoController IpreCont) {
        this.IpreCont = IpreCont;
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Materiales con más Préstamos Pendientes");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        String[] columnas = { "Índice", "ID Material", "Cantidad de Préstamos Pendientes" };
        this.model = new DefaultTableModel(columnas, 0);

        // Llenar el modelo
        buscarPrestamosPendientes(this.model);

        JTable table = new JTable(this.model);
        JScrollPane scroll = new JScrollPane(table);

    JPanel panelBotones = crearPanelBotones();
    // El JScrollPane debe ir al centro del content pane y el panel de botones al sur
    getContentPane().add(scroll, BorderLayout.CENTER);
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    private void buscarPrestamosPendientes(DefaultTableModel model){
        List<DtPrestamo> prestamosPendientes = this.IpreCont.obtenerDtPrestamosPendientes();

        // Agrupar por idMaterial y contar
        Map<Integer, Integer> conteoPorMaterial = new HashMap<>();
        for (DtPrestamo p : prestamosPendientes) {
            int idMaterial = p.getMaterial();
            conteoPorMaterial.put(idMaterial, conteoPorMaterial.getOrDefault(idMaterial, 0) + 1);
        }

        // Ordenar por cantidad descendente
        List<Map.Entry<Integer, Integer>> listaOrdenada = new ArrayList<>(conteoPorMaterial.entrySet());
        listaOrdenada.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        int indice = 1;
        for (Map.Entry<Integer, Integer> entry : listaOrdenada) {
            model.addRow(new Object[] { indice++, entry.getKey(), entry.getValue() });
        }
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        //Dos botones para cancelar y refrescar
        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.addActionListener(e -> {
            cargarPrestamos();
        });
        

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> setVisible(false));
        
        panel.add(btnRefrescar);
        panel.add(btnCerrar);
        
        return panel;

    }
    private void cargarPrestamos() {
        // Limpiar la tabla y volver a cargar los préstamos pendientes agrupados por material
        if (this.model != null) {
            this.model.setRowCount(0);
            buscarPrestamosPendientes(this.model);
        }
    }
}
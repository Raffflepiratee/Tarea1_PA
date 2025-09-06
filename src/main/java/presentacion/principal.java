package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import interfaces.IMaterialController;
import interfaces.IPrestamoController;
import interfaces.IUsuarioController;
import interfaces.Fabrica;

// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;

public class Principal {
    
    private JFrame frame;

    private RegistrarPrestamo registrarPrestamoInternalFrame;
    private ListarPrestamos listarPrestamosInternalFrame;
    private ReporteZonal reporteZonalInternalFrame;
    private PrestamosPorBiblio prestamosPorBiblioInternalFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Principal window = new Principal();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Principal(){
        initialize();

        Fabrica fabrica = Fabrica.getInstancia();
        IMaterialController ImatCont = fabrica.getIControladorMaterial();


        IPrestamoController IprestamoCont = fabrica.getIControladorPrestamo();
        IUsuarioController IusuarioCont = fabrica.getIControladorUsuario();

        Dimension desktopSize = frame.getSize();
		Dimension jInternalFrameSize;

        //Funciones de Usuario

        //Funciones de Material

        //Funciones de Prestamo
        registrarPrestamoInternalFrame = new RegistrarPrestamo(IprestamoCont, IusuarioCont);
        jInternalFrameSize = registrarPrestamoInternalFrame.getSize();
        registrarPrestamoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        registrarPrestamoInternalFrame.setVisible(false);
        frame.getContentPane().add(registrarPrestamoInternalFrame);

        listarPrestamosInternalFrame = new ListarPrestamos(IprestamoCont);
        jInternalFrameSize = listarPrestamosInternalFrame.getSize();
        listarPrestamosInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        listarPrestamosInternalFrame.setVisible(false);
        frame.getContentPane().add(listarPrestamosInternalFrame);

        reporteZonalInternalFrame = new ReporteZonal(IprestamoCont);
        jInternalFrameSize = reporteZonalInternalFrame.getSize();
        reporteZonalInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        reporteZonalInternalFrame.setVisible(false);
        frame.getContentPane().add(reporteZonalInternalFrame);

        prestamosPorBiblioInternalFrame = new PrestamosPorBiblio(IprestamoCont);
        jInternalFrameSize = prestamosPorBiblioInternalFrame.getSize();
        prestamosPorBiblioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        prestamosPorBiblioInternalFrame.setVisible(false);
        frame.getContentPane().add(prestamosPorBiblioInternalFrame);
    }

    private void initialize(){
        int windowWidth = 1000;
        frame = new JFrame();
        frame.setBounds(100, 100, windowWidth, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //Timido mensaje de bienvenida
        JLabel bienvenidaLabel = new JLabel("Bienvenido al Sistema de Gestión de Biblioteca");
        bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bienvenidaLabel.setVerticalAlignment(SwingConstants.CENTER);
        bienvenidaLabel.setBounds(0, 0, frame.getWidth(), 60);
        frame.getContentPane().add(bienvenidaLabel);

        //Barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, windowWidth, 21);
        frame.getContentPane().add(menuBar);

        //Opciones de menu
        JMenu mnPrestamo = new JMenu("Préstamo");
        menuBar.add(mnPrestamo);
        JMenu mnControl = new JMenu("Control y Seguimiento");
        menuBar.add(mnControl);

        //Items de menu 'Prestamo'
        JMenuItem mntmRegistrarPrestamo = new JMenuItem("Registrar Préstamo");
        mntmRegistrarPrestamo.addActionListener(e-> registrarPrestamoInternalFrame.setVisible(true));
        mnPrestamo.add(mntmRegistrarPrestamo);

        JMenuItem mntmListarPrestamos = new JMenuItem("Listar Préstamos");
        mntmListarPrestamos.addActionListener(e-> listarPrestamosInternalFrame.setVisible(true));
        mnPrestamo.add(mntmListarPrestamos);

        //Items de menu 'Control'
        JMenuItem mntmReporteZonal = new JMenuItem("Reporte zonal");
        mntmReporteZonal.addActionListener(e-> reporteZonalInternalFrame.setVisible(true));
        mnControl.add(mntmReporteZonal);

        JMenuItem mntmPrestamosPorBiblio = new JMenuItem("Préstamos por Bibliotecario");
        mntmPrestamosPorBiblio.addActionListener(e-> prestamosPorBiblioInternalFrame.setVisible(true));
        mnControl.add(mntmPrestamosPorBiblio);
    }
}
package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interfaces.IMaterialController;
import interfaces.IPrestamoController;
import interfaces.IUsuarioController;
import interfaces.Fabrica;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {
    
    private JFrame frame;

    private RegistrarPrestamo registrarPrestamoInternalFrame;

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
    }

    private void initialize(){
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 800, 21);
        frame.getContentPane().add(menuBar);

        JMenu mnPrestamo = new JMenu("Préstamo");
        menuBar.add(mnPrestamo);

        JMenuItem mntmRegistrarPrestamo = new JMenuItem("Registrar Préstamo");
        mntmRegistrarPrestamo.addActionListener(e-> registrarPrestamoInternalFrame.setVisible(true));
        mnPrestamo.add(mntmRegistrarPrestamo);
    }
}
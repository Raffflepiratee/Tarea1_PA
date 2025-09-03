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

    private RegistrarUsuario registrarUsuarioInternalFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        initialize();
        Fabrica fabrica = Fabrica.getInstancia();
        IMaterialController matCont = fabrica.getIControladorMaterial();
        IPrestamoController prestamoCont = fabrica.getIControladorPrestamo();
        IUsuarioController usuarioCont = fabrica.getIControladorUsuario();

        Dimension desktopSize = frame.getSize();
        Dimension jInternalFrameSize;

        registrarUsuarioInternalFrame = new RegistrarUsuario(usuarioCont);
        jInternalFrameSize = registrarUsuarioInternalFrame.getSize();
        registrarUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        registrarUsuarioInternalFrame.setVisible(false);
        frame.getContentPane().add(registrarUsuarioInternalFrame);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 800, 21);
        frame.getContentPane().add(menuBar);

        JMenu mnUsuario = new JMenu("Usuarios");
        menuBar.add(mnUsuario);

        JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar Usuario");
        mntmRegistrarUsuario.addActionListener(e -> registrarUsuarioInternalFrame.setVisible(true));
        mnUsuario.add(mntmRegistrarUsuario);
    }
}
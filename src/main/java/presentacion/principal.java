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
    private ListadoUsuarios listarUsuariosInternalFrame;

    private RegistrarMaterial registrarMaterialInternalFrame;
    private ListadoMateriales listadoMaterialesInternalFrame;
    private ListarMaterialPorRango listarMaterialPorRangoInternalFrame;

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
        IMaterialController ImatCont = fabrica.getIControladorMaterial();
        IPrestamoController IprestamoCont = fabrica.getIControladorPrestamo();
        IUsuarioController IusCont = fabrica.getIControladorUsuario();

        Dimension desktopSize = frame.getSize();
        Dimension jInternalFrameSize;

        //Metodos de usuario
        registrarUsuarioInternalFrame = new RegistrarUsuario(IusCont);
        jInternalFrameSize = registrarUsuarioInternalFrame.getSize();
        registrarUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        registrarUsuarioInternalFrame.setVisible(false);
        frame.getContentPane().add(registrarUsuarioInternalFrame);

        listarUsuariosInternalFrame = new ListadoUsuarios(IusCont);
        jInternalFrameSize = listarUsuariosInternalFrame.getSize();
        listarUsuariosInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        listarUsuariosInternalFrame.setVisible(false);
        frame.getContentPane().add(listarUsuariosInternalFrame);

        //Metodos de material
        registrarMaterialInternalFrame = new RegistrarMaterial(ImatCont);
        jInternalFrameSize = registrarMaterialInternalFrame.getSize();
        registrarMaterialInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        registrarMaterialInternalFrame.setVisible(false);
        frame.getContentPane().add(registrarMaterialInternalFrame);

        listadoMaterialesInternalFrame = new ListadoMateriales(ImatCont);
        jInternalFrameSize = listadoMaterialesInternalFrame.getSize();
        listadoMaterialesInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        listadoMaterialesInternalFrame.setVisible(false);
        frame.getContentPane().add(listadoMaterialesInternalFrame);

        listarMaterialPorRangoInternalFrame = new ListarMaterialPorRango(ImatCont);
        jInternalFrameSize = listarMaterialPorRangoInternalFrame.getSize();
        listarMaterialPorRangoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        listarMaterialPorRangoInternalFrame.setVisible(false);
        frame.getContentPane().add(listarMaterialPorRangoInternalFrame);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 800, 21);
        frame.getContentPane().add(menuBar);

        //Menu de Usuario
        JMenu mnUsuario = new JMenu("Usuarios");
        menuBar.add(mnUsuario);

        JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar Usuario");
        mntmRegistrarUsuario.addActionListener(e -> registrarUsuarioInternalFrame.setVisible(true));
        mnUsuario.add(mntmRegistrarUsuario);

        JMenuItem mntmListarUsuarios = new JMenuItem("Listar Usuarios");
        mntmListarUsuarios.addActionListener(e -> listarUsuariosInternalFrame.setVisible(true));
        mnUsuario.add(mntmListarUsuarios);
  
        //Menu de Material
        JMenu mnMaterial = new JMenu("Material");
        menuBar.add(mnMaterial);

        JMenuItem mntmRegistrarMaterial = new JMenuItem("Registrar Material");
        mntmRegistrarMaterial.addActionListener(e -> registrarMaterialInternalFrame.setVisible(true));
        mnMaterial.add(mntmRegistrarMaterial);

        JMenuItem mntmListadoMateriales = new JMenuItem("Listado de Materiales");
        mntmListadoMateriales.addActionListener(e -> listadoMaterialesInternalFrame.setVisible(true));    
        mnMaterial.add(mntmListadoMateriales);

        JMenuItem mntmListarMaterialPor = new JMenuItem("Listar Material por Rango de Fechas");
        mntmListarMaterialPor.addActionListener(e -> listarMaterialPorRangoInternalFrame.setVisible(true));
        mnMaterial.add(mntmListarMaterialPor);
    }
}
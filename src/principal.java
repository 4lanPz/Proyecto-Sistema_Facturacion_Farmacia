import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class principal {

    JPanel jPanel;
    private JComboBox Combo_CA;

    private JTextField btn_usuario;
    private JPasswordField btn_contrasenia;
    private JButton btn_ingresar;
    String usuarioo;
    String contraseniaa;
    static final String DB_URL = "jdbc:mysql://localhost/PROYECTO2023A";
    static final String USER = "root";
    static final String PASS = "root_bas3";

    public principal() {
        btn_usuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioo = btn_usuario.getText();
            }
        });

        btn_contrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contraseniaa = btn_contrasenia.getText();
            }
        });

        btn_ingresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.print(Combo_CA.getSelectedItem());
                if(Combo_CA.getSelectedItem().equals("Cajero")){

                    if(btn_usuario.getText().equals("cajero.Idcaj") && String.valueOf(btn_contrasenia.getPassword()).equals("cajero.Password")){

                           JFrame frame = new JFrame("Menu Principal");
                           frame.setContentPane(new Cajero().CajeroP);
                           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           closeLoginFrame();
                           frame.pack();
                           frame.setLocationRelativeTo(null);
                           frame.setVisible(true);

                           
                   } else{
                        JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña Incorrecto");
                   }

                } else if (Combo_CA.getSelectedItem().equals("Administrador")) {
                    JFrame frame = new JFrame("Administrador");
                    frame.setContentPane(new Admin().admini);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    closeLoginFrame();
                    frame.pack();
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se puede acceder al sistema");
                }

            }
        });

    }
//creamoas la funcion para cerrar otras ventanas y abrir la siguiente
    private void closeLoginFrame() {
        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(jPanel);
        loginFrame.dispose();}
    public static void main(String[] args) {

        JFrame frame = new JFrame("Menu Principal");
        frame.setContentPane(new principal().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
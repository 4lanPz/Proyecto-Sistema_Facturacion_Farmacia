import javax.management.Query;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.util.Queue;

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
    static final String QUERY = "Select * from Cajero";

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


               //conexionC();
               //conexionAD();
                validarUsuario();
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

    void conexionC() {
        boolean usuarioValido = false;
        boolean contraseniaValida = false;
        boolean ususarioVadmin=false;
        boolean contraseniaVadmin = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Cajero WHERE IDcaj = ?");
        ) {
            pstmt.setInt(1, Integer.parseInt(btn_usuario.getText()));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String contraseniaDB = rs.getString("Contraseniacaj");

                if (contraseniaDB.equals(new String(btn_contrasenia.getPassword()))) {
                    contraseniaValida = true;
                }

                usuarioValido = true;
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (usuarioValido && contraseniaValida) {
            JFrame frame = new JFrame("Menu Principal");
            frame.setContentPane(new Cajero().CajeroP);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            closeLoginFrame();
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        else {
            JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña Incorrecto");
        }
    }




    void conexionAD() {
        String usuarioIngresado = btn_usuario.getText();
        char[] contraseniaIngresada = btn_contrasenia.getPassword();

        boolean usuarioValido = false;
        boolean contraseniaValida = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Admin WHERE Nombreadm = ?")) {
            pstmt.setString(1, usuarioIngresado); // Configura el nombre de usuario en la consulta SQL como un string
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String Contraseniaadm = rs.getString("Contraseniaadm");

                if (Contraseniaadm.equals(new String(contraseniaIngresada))) {
                    contraseniaValida = true;
                }

                usuarioValido = true;
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (usuarioValido && contraseniaValida) {
            JFrame frame = new JFrame("Administrador");
            frame.setContentPane(new Admin().admini);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña Incorrecto");
        }
    }

    void validarUsuario() {
        String usuarioIngresado = btn_usuario.getText();
        char[] contraseniaIngresada = btn_contrasenia.getPassword();

        boolean usuarioValidoCajero = false;
        boolean contraseniaValidaCajero = false;
        boolean usuarioValidoAdmin = false;
        boolean contraseniaValidaAdmin = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Validar para Cajero
            PreparedStatement pstmtCajero = conn.prepareStatement("SELECT * FROM Cajero WHERE IDcaj = ?");
            pstmtCajero.setInt(1, Integer.parseInt(usuarioIngresado));
            ResultSet rsCajero = pstmtCajero.executeQuery();

            while (rsCajero.next()) {
                String contraseniaCajero = rsCajero.getString("Contraseniacaj");

                if (contraseniaCajero.equals(new String(contraseniaIngresada))) {
                    contraseniaValidaCajero = true;
                }

                usuarioValidoCajero = true;
            }

            rsCajero.close();

            // Validar para Administrador
            PreparedStatement pstmtAdmin = conn.prepareStatement("SELECT * FROM Admin WHERE Nombreadm = ?");
            pstmtAdmin.setString(1, usuarioIngresado);
            ResultSet rsAdmin = pstmtAdmin.executeQuery();

            while (rsAdmin.next()) {
                String contraseniaAdmin = rsAdmin.getString("Contraseniaadm");

                if (contraseniaAdmin.equals(new String(contraseniaIngresada))) {
                    contraseniaValidaAdmin = true;
                }

                usuarioValidoAdmin = true;
            }

            rsAdmin.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if ((usuarioValidoCajero && contraseniaValidaCajero) || (usuarioValidoAdmin && contraseniaValidaAdmin)) {
            // Si es válido para Cajero o Administrador
            JFrame frame = new JFrame("Menu Principal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña Incorrecto");
        }
    }

}


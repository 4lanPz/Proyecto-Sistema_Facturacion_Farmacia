import javax.management.Query;
import javax.swing.*;
import java.awt.*;
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
    private JLabel Estatus;
    String Tipo_user;
    String usuarioo;
    String contraseniaa;
    int ID_Cajero = 0;
    int ID_Administrador = 0;
    public static int ID_CajeroLogueado = 0;

    String conexion= "jdbc:sqlserver://localhost:1433;" +
            "database=PROYECTO2023A;" +
            "user=root;" +
            "password=root_1;" +
            "trustServerCertificate=true;";
    //static final String DB_URL = "jdbc:mysql://localhost/PROYECTO2023A";
    //static final String USER = "root";
    //static final String PASS = "root_bas3";

    public principal() {
        btn_ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tipo_user = (String) Combo_CA.getSelectedItem();

                //para abrir ventana Cajero
                if (Tipo_user == "Cajero" ){
                    usuarioo = btn_usuario.getText();
                    contraseniaa = btn_contrasenia.getText();
                    //conexion y comprobacion
                    String Query="Select ID,Correo, Contrasenia from Cajero where correo = ? and Contrasenia = ?";
                    try(Connection conn=DriverManager.getConnection(conexion);)
                    {
                        PreparedStatement statement = conn.prepareStatement(Query);
                        statement.setString(1, usuarioo);
                        statement.setString(2, contraseniaa);
                        ResultSet rs = statement.executeQuery();
                        while (rs.next()) {
                            ID_Cajero = Integer.parseInt(rs.getString("ID"));
                            ID_CajeroLogueado = ID_Cajero;
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (ID_Cajero != 0 ){
                        JFrame frame = new JFrame("Cajero");
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int xPos = (screenSize.width - frame.getWidth()) / 2;
                        int yPos = (screenSize.height - frame.getHeight()) / 2;
                        frame.setLocation(xPos, yPos);
                        frame.setContentPane(new Cajero().CajeroP);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setSize(1000,800);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                    else {
                        Estatus.setText("NO SE ENCONTRO USUARIO");
                    }
                    //para abrir ventana administrador
                } else if (Tipo_user == "Administrador") {
                    usuarioo = btn_usuario.getText();
                    contraseniaa = btn_contrasenia.getText();
                    //conexion y comprobacion
                    String Query="Select ID,Correo, Contrasenia from Administrador where correo = ? and Contrasenia = ?";
                    try(Connection conn=DriverManager.getConnection(conexion);)
                    {
                        PreparedStatement statement = conn.prepareStatement(Query);
                        statement.setString(1, usuarioo);
                        statement.setString(2, contraseniaa);
                        ResultSet rs = statement.executeQuery();
                        while (rs.next()) {
                            ID_Administrador = Integer.parseInt(rs.getString("ID"));
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (ID_Administrador != 0 ){
                        JFrame frame = new JFrame("Administrador");
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int xPos = (screenSize.width - frame.getWidth()) / 2;
                        int yPos = (screenSize.height - frame.getHeight()) / 2;
                        frame.setLocation(xPos, yPos);
                        frame.setContentPane(new Admin().admini);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setSize(1000,800);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                    else {
                        Estatus.setText("NO SE ENCONTRO USUARIO");
                    }
            }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Principal");
        //centra la ventana en la mitad de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (screenSize.width - frame.getWidth()) / 2;
        int yPos = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(xPos, yPos);
        frame.setContentPane(new principal().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
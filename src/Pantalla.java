import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Pantalla {
    private JButton agrgarC;
    private JButton revisionF;
    private JTextField codCajero;
    private JTextField nomCajero;
    private JTextField correoCajero;
    private JTextField apellCajero;
    private JTextField passCajero;
    private JTextField cantStock;
    private JTextField preStock;
    private JTextField prodStock;
    private JButton agrgarS;
    private JTextField codcajeroF;
    private JTextField ventaF;
    private JPanel pantalla;
    private JTextField codigoS;

    public Pantalla() {
        agrgarC.addActionListener(new ActionListener() {
            static final String DB_URL = "jdbc:mysql://localhost/PROYECTO2023A";
            //cadena de conexion
            static final String user = "root";
            //usuario
            static final String pass= "root_bas3";
            //paswword
            static final String query = "SELECT * FROM Cajero ";
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigoc = Integer.parseInt(codCajero.getText());
                String nombre = nomCajero.getText();
                String apellido = apellCajero.getText();
                String correo = correoCajero.getText();
                String contrasenia = passCajero.getText();

                try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)){
                    String sql = "INSERT INTO Cajero (ID, Nombre, Apellido, Correo, Contrasenia, ) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, String.valueOf(codigoc)); // Obtener valor desde JTextField
                    pstmt.setString(2, nombre); // Obtener valor desde JTextField
                    pstmt.setString(3, apellido); // Obtener valor desde JTextField
                    pstmt.setString(4, correo); // Obtener valor desde JTextField
                    pstmt.setString(5, contrasenia); // Obtener valor desde JTextField


                    int filasAfectadas = pstmt.executeUpdate();
                    System.out.println("Se han insertado " + filasAfectadas + " filas.");
                    pstmt.close();


                } catch (SQLException e1){
                    e1.printStackTrace();

                };

            }
        });
        agrgarS.addActionListener(new ActionListener() {
            static final String DB_URL = "jdbc:mysql://localhost/PROYECTO2023A";
            //cadena de conexion
            static final String user = "root";
            //usuario
            static final String pass= "root_bas3";
            //paswword
            static final String query = "SELECT * FROM Producto ";
            @Override
            public void actionPerformed(ActionEvent e) {
                int coS = Integer.parseInt(codigoS.getText());
                int cantidadS = Integer.parseInt(cantStock.getText());
                double price = Double.parseDouble(preStock.getText());
                String prod = prodStock.getText();


                try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)){
                    String sql = "INSERT INTO Producto  (Cod, Nom,Precio,Stock ) VALUES (?, ?, ?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, String.valueOf(coS)); // Obtener valor desde JTextField
                    pstmt.setString(2, String.valueOf(cantidadS)); // Obtener valor desde JTextField
                    pstmt.setString(3, String.valueOf(price)); // Obtener valor desde JTextField
                    pstmt.setString(4, prod); // Obtener valor desde JTextField


                    int filasAfectadas = pstmt.executeUpdate();
                    System.out.println("Se han insertado " + filasAfectadas + " filas.");
                    pstmt.close();


                } catch (SQLException e1){
                    e1.printStackTrace();

                };

            }
        });
        revisionF.addActionListener(new ActionListener() {
            static final String DB_URL = "jdbc:mysql://localhost/POO1";
            //cadena de conexion
            static final String user = "root";
            //usuario
            static final String pass= "root_bas3";
            //paswword
            static final String query = "SELECT * FROM  Factura ";
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigoc = Integer.parseInt(codCajero.getText());

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pantalla");
        frame.setContentPane(new Pantalla().pantalla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Admin {
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
    public JPanel admini;
    private JTextField codigoS;
    private JSeparator s;

    public Admin() {
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
                    String sql = "INSERT INTO Cajero (ID, Nombre, Apellido, Correo, Contrasenia) VALUES (?, ?, ?, ?, ?)";
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

                try (Connection conn = DriverManager.getConnection(DB_URL, user, pass)) {
                    String sql = "INSERT INTO Producto (Cod, Nom, Precio, Stock) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, coS);
                    pstmt.setString(2, prod);
                    pstmt.setDouble(3, price);
                    pstmt.setInt(4, cantidadS);

                    int filasAfectadas = pstmt.executeUpdate();
                    System.out.println("Se han insertado " + filasAfectadas + " filas.");
                    pstmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        revisionF.addActionListener(new ActionListener() {
            static final String DB_URL = "jdbc:mysql://localhost/PROYECTO2023A";
            //cadena de conexion
            static final String user = "root";
            //usuario
            static final String pass= "root_bas3";
            //paswword
            static final String query = "SELECT * FROM Factura  ";
            @Override
            public void actionPerformed(ActionEvent e) {
                int codC = Integer.parseInt(codcajeroF.getText());


                try (Connection conn = DriverManager.getConnection(DB_URL, user, pass)) {
                    String sql = "SELECT COUNT(Numfac) FROM Factura  where IDCaj = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, codC);

                    ResultSet resultSet = pstmt.executeQuery();
                    if (resultSet.next()) {
                        int numFacturas = ((ResultSet) resultSet).getInt(1);
                        ventaF.setText(String.valueOf(numFacturas));
                    }

                    resultSet.close();
                    pstmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });
    }
    public void setVisible(boolean b) {

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Administrador");
        frame.setContentPane(new Admin().admini);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

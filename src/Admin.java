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
    public JPanel admini;
    private JTextField codigoS;
    private JSeparator s;
    private JLabel ventas;

    static final String DB_URL = "jdbc:mysql://localhost/PROYECTO2023A";
    static final String user = "root";
    static final String pass= "root_bas3";

    public Admin() {
        agrgarC.addActionListener(new ActionListener() {
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

                    codCajero.setText("");
                    nomCajero.setText("");
                    apellCajero.setText("");
                    correoCajero.setText("");
                    passCajero.setText("");


                } catch (SQLException e1){
                    e1.printStackTrace();

                };

            }
        });
        agrgarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int coS = Integer.parseInt(codigoS.getText());
                int cantidadS = Integer.parseInt(cantStock.getText());
                String Actualizar = "UPDATE Producto SET Stock = Stock + ? WHERE COD = ?";
                try(Connection conn=DriverManager.getConnection(DB_URL,user,pass);)
                {
                    PreparedStatement updateStatement = conn.prepareStatement(Actualizar);
                    updateStatement.setInt(1, cantidadS);
                    updateStatement.setInt(2, coS);
                    updateStatement.executeUpdate();
                } catch (SQLException eX) {
                    throw new RuntimeException(eX);
                }
            }
        });

        revisionF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codC = Integer.parseInt(codcajeroF.getText());
                int numFacturas = 0;
                String sql = "SELECT COUNT(numfac) as total FROM Factura  where IDCaj = ?";
                try (Connection conn = DriverManager.getConnection(DB_URL, user, pass)) {
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, codC);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        numFacturas = rs.getInt("total");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (numFacturas %5 == 0){
                    while (numFacturas >= 5) {
                        numFacturas -= 4;
                        ventas.setText(String.valueOf(numFacturas));
                    }
                }
                else {
                    numFacturas = 0;
                    ventas.setText(String.valueOf(numFacturas));
                }
            }
        });
    }
}

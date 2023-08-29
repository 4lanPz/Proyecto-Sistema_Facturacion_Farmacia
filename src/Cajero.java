import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cajero {
    public JPanel CajeroP;
    private JLabel LCajero;
    private JTextField TNOMBRE;
    private JTextField TAPELLIDO;
    private JTextField TCORREO;
    private JTextField TTELEF;
    private JTextField TCEDULA;
    private JTextField TCOD1;
    private JTextField TCANTIDAD1;
    private JLabel TPROD1;
    private JLabel TPRECIO1;
    private JLabel TSUBT;
    private JLabel TTOTAL;
    private JTextField TCOD2;
    private JTextField TCOD3;
    private JTextField TCOD4;
    private JTextField TCOD5;
    private JLabel TPROD2;
    private JLabel TPROD3;
    private JLabel TPROD4;
    private JLabel TPROD5;
    private JLabel TPRECIO2;
    private JLabel TPRECIO3;
    private JLabel TPRECIO4;
    private JLabel TPRECIO5;
    private JTextField TCANTIDAD2;
    private JTextField TCANTIDAD3;
    private JTextField TCANTIDAD4;
    private JTextField TCANTIDAD5;
    private JButton FACTURAButton;
    //cliente
    String Nom,Apel,Mail;
    int Ced,Telef;
    //productos
    String Nomprod;
    int Codprod, PrecioU,Cantidad;
    //Total
    int Subtotal,Valoriva,Total;
    static String DB_URL="jdbc:mysql://localhost/PruebaAlan";
    static String USER="root";
    static String PASS="root_bas3";

    public int CODIGOPRODUCTO(int Cod){
        String SELECT_QUERY="SELECT * FROM Producto WHERE Cod = ?";
        try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
        {
            PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
            statement.setString(1, SIGNO1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Codigo = Integer.parseInt(rs.getString("CODIGO"));
                Cedula = rs.getString("CEDULA");
                Nombre = rs.getString("NOMBRE");
                Fecha = rs.getString("FECHA_NACIMIENTO");
            }
            TCODIGO.setText(String.valueOf(Codigo));
            TCEDULA.setText(Cedula);
            TNOMBRE.setText(Nombre);
            TFECHA.setText(Fecha);
        }
        catch (SQLException eX){
            throw new RuntimeException(eX);
        }
    return 0;
    }

    public Cajero(){

        FACTURAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cliente
                Nom = TNOMBRE.getText();
                Apel = TAPELLIDO.getText();
                Mail = TCORREO.getText();
                Ced = Integer.parseInt(TCEDULA.getText());
                Telef = Integer.parseInt(TTELEF.getText());
                //Productos

            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cajero");
        frame.setContentPane(new Cajero().CajeroP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

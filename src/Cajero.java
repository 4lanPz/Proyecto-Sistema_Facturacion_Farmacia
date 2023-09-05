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
    int contador = 0;
    //cliente
    String Nom,Apel,Mail,Ced,Telef;
    //productos
    int CodigosP [] = {0,0,0,0,0};
    String NombresP[] = {"","","","",""};
    Double PreciosP[] = {0.0,0.0,0.0,0.0,0.0};
    int CantidadesP[] = {0,0,0,0,0};
    Double Subtotales[] = {0.0,0.0,0.0,0.0,0.0};
    //Total
    double Subtotal, Valoriva= 0.12 ,Total;
    //Conexion
    //static String DB_URL="jdbc:mysql://localhost/PruebaAlan";
    //static String USER="root";
    //static String PASS="root_bas3";
    String conexion = "jdbc:sqlserver://localhost:1433;" +
            "database=CorrecionP2B;" +
            "user=root;" +
            "password=root_1;" +
            "trustServerCertificate=true;";

    public void CODIGOPRODUCTO(String Codigo){
        for (int i = 0; contador <= 4 ; i++){
            String SELECT_QUERY="SELECT * FROM Producto WHERE Cod = ?";
            //try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
            try(Connection conn=DriverManager.getConnection(conexion);)
            {
                PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                statement.setString(1, Codigo);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    NombresP[i] = rs.getString("Nom");
                    PreciosP[i] = Double.valueOf(rs.getString("Precio"));
                    CantidadesP[i] = Integer.parseInt(rs.getString("Stock"));
                }
            }
            catch (SQLException eX){
                throw new RuntimeException(eX);
            }
        }
    }

    public Cajero(){
        FACTURAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cliente
                Nom = TNOMBRE.getText();
                Apel = TAPELLIDO.getText();
                Mail = TCORREO.getText();
                Ced = TCEDULA.getText();
                Telef = TTELEF.getText();
                //Productos
                CodigosP[0] = Integer.parseInt(TCOD1.getText());
                CodigosP[1] = Integer.parseInt(TCOD2.getText());
                CodigosP[2] = Integer.parseInt(TCOD2.getText());
                CodigosP[3] = Integer.parseInt(TCOD3.getText());
                CodigosP[4] = Integer.parseInt(TCOD4.getText());
                //Calculos
                for (int i = 0; i <=0; i++){
                    Subtotales[i]=PreciosP[i]*CantidadesP[i];
                    Subtotal += Subtotales[i];
                }
                Total=Subtotal+(Subtotal*Valoriva);
                TTOTAL.setText(String.valueOf(Total));
            }
        });
    }

    public void setVisible(boolean b) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cajero");
        frame.setContentPane(new Cajero().CajeroP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

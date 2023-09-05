import javax.swing.*;
import java.sql.*;

public class Factura {
    public JPanel PFactura;
    private JLabel NombreF;
    private JLabel CedulaF;
    private JLabel CorreoF;
    private JLabel TelefonoF;
    private JLabel PROD1;
    private JLabel PROD2;
    private JLabel PROD3;
    private JLabel PROD4;
    private JLabel PROD5;
    private JLabel PRES1;
    private JLabel PRES2;
    private JLabel PRES3;
    private JLabel PRES4;
    private JLabel PRES5;
    private JLabel CAN1;
    private JLabel CAN2;
    private JLabel CAN3;
    private JLabel CAN4;
    private JLabel CAN5;
    private JLabel TOT1;
    private JLabel TOT2;
    private JLabel TOT3;
    private JLabel TOT4;
    private JLabel TOT55;
    private JLabel SUBTOTAL;
    private JLabel IVA;
    private JLabel FACTURA;
    private JLabel NOMBREFAC;
    private JLabel ApellidoF;
    int NUMFAC;
    int IDCAJERO;

    //cliente
    String Nom,Apel,Mail,Ced,Telef;

    //productos
    int CodigosP [] = {0,0,0,0,0};
    String NombresP [] = {"","","","",""};
    Double PreciosP [] = {0.0,0.0,0.0,0.0,0.0};
    int CantidadesP[] = {0,0,0,0,0};
    Double Subtotales [] = {0.0,0.0,0.0,0.0,0.0};

    //Total Factura
    double Subtotal, Valoriva= 0.12 ,Total;
    int NumFac,IdCajero;

    //Conexion
    static String DB_URL="jdbc:mysql://localhost/PruebaAlan";
    static String USER="root";
    static String PASS="root_bas3";

    public Factura(int NUMFAC, int IDCAJERO) {
        this.NUMFAC = NUMFAC;
        this.IDCAJERO = IDCAJERO;

    }

    public void datos(){
        for (int i = 0; i <= 4 ; i++){
            String SELECT_QUERY="SELECT * FROM FACTURA WHERE NUMFAC = ?";
            try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
            {
                PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                statement.setString(1, String.valueOf(NUMFAC));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    CantidadesP[i] = rs.getInt("Cantidad");
                    CodigosP[i] = rs.getInt("Codprod");
                }
            }
            catch (SQLException eX){
                throw new RuntimeException(eX);
            }
        }
        //Calculos
        for (int i = 0; i <=4; i++){
            Subtotales[i]=PreciosP[i]*CantidadesP[i];
            Subtotal += Subtotales[i];
        }
        Total=Subtotal+(Subtotal*Valoriva);

        String SELECT_QUERY="SELECT * FROM Cliente WHERE NUMFAC = ?";
        try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
        {
            PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
            statement.setString(1, String.valueOf(NUMFAC));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CantidadesP[i] = rs.getInt("Cantidad");
                CodigosP[i] = rs.getInt("Codprod");
            }
        }
        catch (SQLException eX){
            throw new RuntimeException(eX);
        }
    }

    public static void setVisible(boolean b) {
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cajero");
        frame.setContentPane(new Cajero().CajeroP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
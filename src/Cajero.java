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
    private JTextField TNumFac;
    private JButton BVISTA;

    public Cajero(int numFac, int IDCAJERO) {
        this.NumFac = numFac;
        this.IdCajero = IDCAJERO;
    }
    public int getNumFac() {
        return NumFac;
    }
    public void setNumFac(int numFac) {
        NumFac = numFac;
    }
    public int getIDCAJERO() {
        return IdCajero;
    }
    public void setIDCAJERO(int IDCAJERO) {
        this.IdCajero = IDCAJERO;
    }

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
    static String DB_URL="jdbc:mysql://localhost/PROYECTO2023A";
    static String USER="root";
    static String PASS="root_bas3";

    public void CODIGOPRODUCTO(){
        for (int i = 0; i <= 4 ; i++){
            String SELECT_QUERY="SELECT * FROM Producto WHERE COD = ?";
            //try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
            try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
            {
                PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                statement.setString(1, String.valueOf(CodigosP[i]));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    PreciosP[i] = Double.valueOf(rs.getString("Precio"));
                    NombresP[i] = rs.getString("Nom");
                }
            }
            catch (SQLException eX){
                throw new RuntimeException(eX);
            }
        }
        TPROD1.setText(NombresP[0]);
        TPROD2.setText(NombresP[1]);
        TPROD3.setText(NombresP[2]);
        TPROD4.setText(NombresP[3]);
        TPROD5.setText(NombresP[4]);
        TPRECIO1.setText(String.valueOf(PreciosP[0]));
        TPRECIO2.setText(String.valueOf(PreciosP[1]));
        TPRECIO3.setText(String.valueOf(PreciosP[2]));
        TPRECIO4.setText(String.valueOf(PreciosP[3]));
        TPRECIO5.setText(String.valueOf(PreciosP[4]));
    }

    public void CALCULOS(){
        for (int i = 0; i <= 4 ; i++){
            String SELECT_QUERY="SELECT * FROM Producto WHERE COD = ?";
            try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
            {
                PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                statement.setString(1, String.valueOf(CodigosP[i]));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    PreciosP[i] = Double.valueOf(rs.getString("Precio"));
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
                //Ingresar los valores de las facturas
                for (int i = 0; i <= 4 ; i++){
                    String Ingresar_FAC ="INSERT INTO Factura VALUES(?,?,?,?,?)";
                    try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
                    {
                        PreparedStatement statement = conn.prepareStatement(Ingresar_FAC);
                        statement.setInt(1, NumFac);
                        statement.setInt(2, CantidadesP[i]);
                        statement.setInt(3, 123);
                        statement.setInt(4, 123);
                        statement.setInt(5, CodigosP[i]);
                        statement.executeUpdate();
                    }
                    catch (SQLException eX){
                        throw new RuntimeException(eX);
                    }
                }
                Factura factura = new Factura(NumFac,IdCajero);
                Factura.setVisible(true);
            }
        });
        BVISTA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumFac = Integer.parseInt(TNumFac.getText());
                IdCajero = 123455;
                //cliente
                Ced = TCEDULA.getText();
                Nom = TNOMBRE.getText();
                Apel = TAPELLIDO.getText();
                Mail = TCORREO.getText();
                Telef = TTELEF.getText();
                //Productos
                CodigosP[0] = Integer.parseInt(TCOD1.getText());
                CodigosP[1] = Integer.parseInt(TCOD2.getText());
                CodigosP[2] = Integer.parseInt(TCOD3.getText());
                CodigosP[3] = Integer.parseInt(TCOD4.getText());
                CodigosP[4] = Integer.parseInt(TCOD5.getText());
                CODIGOPRODUCTO();

                //CANTIDADES
                CantidadesP[0] = Integer.parseInt(TCANTIDAD1.getText());
                CantidadesP[1] = Integer.parseInt(TCANTIDAD2.getText());
                CantidadesP[2] = Integer.parseInt(TCANTIDAD3.getText());
                CantidadesP[3] = Integer.parseInt(TCANTIDAD4.getText());
                CantidadesP[4] = Integer.parseInt(TCANTIDAD5.getText());
                CALCULOS();
                //Calculos
                for (int i = 0; i <=4; i++){
                    Subtotales[i]=PreciosP[i]*CantidadesP[i];
                    Subtotal += Subtotales[i];
                }
                TSUBT.setText(String.valueOf(Subtotal));
                Total=Subtotal+(Subtotal*Valoriva);
                TTOTAL.setText(String.valueOf(Total));
                //Ingres valores del cliente
                String Q_Ingresar ="INSERT INTO CLIENTE VALUES(?,?,?,?,?)";
                try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
                {
                    PreparedStatement statement = conn.prepareStatement(Q_Ingresar);
                    statement.setInt(1, Integer.parseInt(Ced));
                    statement.setString(2, Nom);
                    statement.setString(3, Apel);
                    statement.setString(4, Mail);
                    statement.setString(5, Telef);
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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

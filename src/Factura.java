import javax.swing.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;

public class Factura {
    private JPanel PFactura;
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
    static String DB_URL="jdbc:mysql://localhost/PruebaAlan";
    static String USER="root";
    static String PASS="root_bas3";
    public Factura(){
        /*String SELECT_QUERY="SELECT * FROM FACTURA,  WHERE NUMFAC = ? AND CEDULACLI = ";
        try(Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);)
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
        }*/
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Factura");
        frame.setContentPane(new Factura().PFactura);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

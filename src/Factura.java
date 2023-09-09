import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

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
    private JLabel TOT5;
    private JLabel SUBTOTAL;
    private JLabel IVA;
    private JLabel FACTURA;
    private JLabel NOMBREFAC;
    private JLabel ApellidoF;
    private JLabel TOTALPAGO;
    //cliente
    String Nom,Apel,Mail;
    int Ced,Telef;
    //cajero
    String Nombrecaj,Apellidocaj;

    //productos
    int CodigosP [] = {0,0,0,0,0};
    String NombresP [] = {"","","","",""};
    Double PreciosP [] = {0.0,0.0,0.0,0.0,0.0};
    int CantidadesP[] = {0,0,0,0,0};
    Double Subtotales [] = {0.0,0.0,0.0,0.0,0.0};

    //Total Factura
    double Subtotal, Valoriva= 0.12 ,Total;
    int NumFac = Cajero.Numero_Factura,IDCajero = principal.ID_CajeroLogueado;

    //Conexion
    static String DB_URL="jdbc:mysql://localhost/PROYECTO2023A";
    static String USER="root";
    static String PASS="root_bas3";

    public void datos(){
            String SELECT_QUERY="Select Cantidad,Nombre,Apellido,CedulaCli,Codprod,Nombrecli,Apelcli,Correocli,Telef,Nom,Precio " +
                    "From Factura, Cliente, Producto, Cajero " +
                    "where Cedula = CedulaCli and Codprod = Cod and Numfac = ? and IDCaj = ?";
            try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);)
            {
                PreparedStatement statement = conn.prepareStatement(SELECT_QUERY);
                statement.setString(1, String.valueOf(NumFac));
                statement.setString(2, String.valueOf(IDCajero));
                ResultSet rs = statement.executeQuery();
                int i = 0;
                while (rs.next() &&  i < 5) {
                    //productos
                    CantidadesP[i] = rs.getInt("Cantidad");
                    CodigosP[i] = rs.getInt("Codprod");
                    NombresP[i] = rs.getString("Nom");
                    PreciosP[i]= rs.getDouble("Precio");
                    //cliente
                    Ced = rs.getInt("CedulaCLI");
                    Nom = rs.getString("Nombrecli");
                    Apel= rs.getString("Apelcli");
                    Mail = rs.getString("Correocli");
                    Telef = rs.getInt("Telef");
                    //cajero
                    Nombrecaj = rs.getString("Nombre");
                    Apellidocaj = rs.getString("Apellido");
                    i++;
                }
            }
            catch (SQLException eX){
                throw new RuntimeException(eX);
            }

        //Calculos
        for (int i = 0; i <=4; i++){
            Subtotales[i]=PreciosP[i]*CantidadesP[i];
            Subtotal += Subtotales[i];
        }
        Total=Subtotal+(Subtotal*Valoriva);
        //Factura
        FACTURA.setText("FACTURA N° "+NumFac);
        NOMBREFAC.setText(("FACTURA REALIZADA POR: "+Nombrecaj +" "+ Apellidocaj));
        //Datos cliente
        NombreF.setText(Nom);
        ApellidoF.setText(Apel);
        CedulaF.setText(String.valueOf(Ced));
        CorreoF.setText(Mail);
        TelefonoF.setText(String.valueOf(Telef));
        //Productos
        DecimalFormat df = new DecimalFormat("0.00");
        //nombres
        PROD1.setText(NombresP[0]);
        PROD2.setText(NombresP[1]);
        PROD3.setText(NombresP[2]);
        PROD4.setText(NombresP[3]);
        PROD5.setText(NombresP[4]);
        //precio unitario
        PRES1.setText(df.format(PreciosP[0]));
        PRES2.setText(df.format(PreciosP[1]));
        PRES3.setText(df.format(PreciosP[2]));
        PRES4.setText(df.format(PreciosP[3]));
        PRES5.setText(df.format(PreciosP[4]));
        //cantidades
        CAN1.setText(String.valueOf(CantidadesP[0]));
        CAN2.setText(String.valueOf(CantidadesP[1]));
        CAN3.setText(String.valueOf(CantidadesP[2]));
        CAN4.setText(String.valueOf(CantidadesP[3]));
        CAN5.setText(String.valueOf(CantidadesP[4]));
        //Precio calculado
        TOT1.setText(df.format(Subtotales[0]));
        TOT2.setText(df.format(Subtotales[1]));
        TOT3.setText(df.format(Subtotales[2]));
        TOT4.setText(df.format(Subtotales[3]));
        TOT5.setText(df.format(Subtotales[4]));
        //Valores Factura
        SUBTOTAL.setText(df.format(Subtotal));
        TOTALPAGO.setText(df.format(Total));
    }
    public BufferedImage createImage(JPanel panel) {
        int width = panel.getWidth();
        int height = panel.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        panel.paint(g);
        g.dispose();
        return image;
    }
    public void exportarAPDF(JPanel panel) {
        try {
            // Crear un documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Obtener la imagen del JPanel
            BufferedImage panelImage = createImage(panel);

            // Convertir la imagen en un objeto PDImageXObject
            PDImageXObject imageXObject = LosslessFactory.createFromImage(document, panelImage);

            // Configurar el tamaño y la posición de la imagen en la página
            float imageWidth = page.getMediaBox().getWidth() - 40; // ajusta el ancho según sea necesario
            float imageHeight = imageWidth * (panelImage.getHeight() / (float) panelImage.getWidth());
            float x = 20; // ajusta la posición x según sea necesario
            float y = page.getMediaBox().getHeight() - imageHeight - 20; // ajusta la posición y según sea necesario

            // Agregar la imagen a la página del PDF
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.drawImage(imageXObject, x, y, imageWidth, imageHeight);
            }

            // Guardar el documento PDF en un archivo
            String nombreArchivo = "factura.pdf"; // ajusta el nombre del archivo según sea necesario
            document.save(nombreArchivo);
            document.close();

            System.out.println("PDF generado con éxito: " + nombreArchivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Factura(){
        datos();
        SwingUtilities.invokeLater(() -> {
            exportarAPDF(PFactura);
        });
    }
}
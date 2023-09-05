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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Factura");
        frame.setContentPane(new Factura().PFactura);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
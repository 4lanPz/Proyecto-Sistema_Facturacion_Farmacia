import javax.swing.*;

public class Cajero {
    public JPanel CajeroP;
    private JLabel LCajero;
    private JTextField TNOMBRE;
    private JTextField TAPELLIDO;
    private JTextField TCORREO;
    private JTextField TTELEF;
    private JTextField TCEDULA;
    private JTextField TCOD;
    private JTextField TCANTIDAD;
    private JLabel TPROD;
    private JLabel TPRECIO;
    private JLabel TSUBT;
    private JLabel TTOTAL;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cajero");
        frame.setContentPane(new Cajero().CajeroP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

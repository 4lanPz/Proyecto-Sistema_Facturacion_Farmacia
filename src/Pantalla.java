import javax.swing.*;

public class Pantalla {
    private JComboBox CBLogin;
    private JPanel Pantalla;
    private JLabel imageLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pantalla");
        frame.setContentPane(new Pantalla().Pantalla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
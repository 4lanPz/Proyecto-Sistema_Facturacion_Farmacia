import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class principal {

    private JPanel jPanel;
    private JComboBox Combo_CA;
    private JTextField btn_usuario;
    private JPasswordField btn_contraseña;
    private JButton btn_ingresar;
    String usuarioo;
    String contraseñaa;
    static final String DB_URL = "jdbc:mysql://localhost/PROYECTO2023A";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    public principal() {
        btn_usuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioo= btn_usuario.getText();
            }
        });

        /*btn_ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(btn_usuario);
                System.out.println(btn_contraseña);
                //crear un nuevo archivo
                File file = new File("C://Users//POO//Documents//Menuf.txt");
                try {
                    //Escribir datos dentro de un archivo //file archivo que ya guarde arribaa
                    FileWriter fileWriter = new FileWriter(file, true);
                    //crear otra clase escribri datos para guardar en memoria
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(btn_usuario + ":" + btn_contraseña + "\n");
                    //cerrar
                    bufferedWriter.close();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña incorrecta");
                }
            }
        });*/

        btn_contraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contraseñaa= btn_contraseña.getText();
            }
        });
        btn_ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (Combo_CA.getSelectedItem().equals("Cajero")){
                    loginCajero log_caj = new loginCajero();
                    log_caj.setVisible(true);
                }
                if(Combo_CA.getSelectedItem().equals("Administrador")){
                    loginAdmin log_admin = new loginAdmin();
                    log_admin.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se puede acceder al sistema");
                }*/
            }
        });
        btn_ingresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 if (Combo_CA.getSelectedItem().equals("Cajero")){
                    Pantalla log_caj = new Pantalla();
                    log_caj.setVisible(true);
                }
                if(Combo_CA.getSelectedItem().equals("Administrador")){
                    loginAdmin log_admin = new loginAdmin();
                    log_admin.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se puede acceder al sistema");
                }
            }
        });
    }
    public static void main(String[] args) {

        JFrame frame = new JFrame("Menu Principal");
        frame.setContentPane(new principal().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
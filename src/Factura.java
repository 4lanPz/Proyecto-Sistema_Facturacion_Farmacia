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

/*
* DROP DATABASE PROYECTO2023A;
Create database PROYECTO2023A;
use PROYECTO2023A;

Create Table Admin(
Correo varchar(25),
Nombre varchar(15),
Apellido varchar(15),
Contrasenia varchar(30));

Create Table Cajero(
ID int primary key,
Nombre varchar(15),
Apellido varchar(15),
Correo varchar(25),
Contrasenia varchar(30));

Create Table Producto(
Cod int primary key,
Nom varchar(35),
Precio double,
Stock int);

Create Table Cliente(
Cedula int primary key,
Nombrecli varchar(35),
Apelcli varchar(35),
Correocli varchar(35),
Telef int);

Create Table Factura(
numfac int,
cantidad int,
IDCaj int,
CedulaCli int,
Codprod int,
FOREIGN KEY (Codprod) REFERENCES Producto(Cod),
FOREIGN KEY (CedulaCli) REFERENCES Cliente(Cedula),
FOREIGN KEY (IDCaj) REFERENCES Cajero(ID));

insert into cliente
values(12345,'nomcli1','apelcli1','correocli1',123456789);
insert into producto
values(54321,'prod1',1.20,100);
insert into producto
values(1,'prod1',1.20,100),
(2,'prod2',1.30,100),
(3,'prod3',1.50,100),
(4,'prod4',1.80,100),
(5,'prod5',2.90,100);
insert into cajero
values(123,'nomcaj','apelcaj','correocaj','contra');

insert into cajero
values(12345,'nomcaj','apelcaj','correocaj','contra');
Insert into Factura
values(1,123,123,12345,54321);

select * from cajero

SELECT * FROM FACTURA
WHERE IDCAJ = 123 and numfac = 1000

select Nombrecli CLiente from Cliente A, Factura b
where Cedula = CedulaCli and Numfac = 1000
* */
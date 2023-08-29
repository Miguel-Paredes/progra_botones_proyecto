import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cajeros {
    private JPanel Caje;
    private JTextField id;
    private JButton OKButton;
    static String DB_URL = "jdbc:mysql://localhost/medical";
    static String USER = "root";
    static String PASS = "root_bas3";

    public Cajeros() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diferenciarCodCajero(Integer.parseInt(id.getText()));
            }
        });
    }

    public boolean diferenciarCodCajero(int id){
        String diferents = "Select * From Usuario WHERE tipoUsuario = 'cajero'";
        boolean esDiferente = false;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(diferents)) {
            while (rs.next()) {
                int idProducto = rs.getInt("idUsuario");
                if (idProducto == id) {
                    esDiferente = true;
                    break;}}}
        catch (SQLException e) {
            throw new RuntimeException(e);}
        if(esDiferente==true){
            System.out.println("Usuario registrado");}
        else {
            System.out.println("Usario no registrado");}
        return esDiferente;}
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cajeros");
        frame.setContentPane(new Cajeros().Caje);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

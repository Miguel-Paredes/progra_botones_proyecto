import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class conexion {
    private JTextField usuario;
    private JPanel JPanel;
    private JPasswordField contrasenia;
    private JButton verificarButton;
    private JComboBox<String> comboBox1;
    static String DB_URL = "jdbc:mysql://localhost/medical";
    static String USER = "root";
    static String PASS = "root";
    static String QUERY = "SELECT * FROM Usuario";
    static String veriusu;
    static String vericontra;
    static String selec;
    static String usu;
    static String contra;
    static String seleccionar;

    public conexion() {
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarConexion();}});}

    public void verificarConexion(){
        seleccionar = (String) comboBox1.getSelectedItem();
        usu = usuario.getText().trim();
        contra = new String(contrasenia.getPassword()).trim();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            while (rs.next()) {
                if (usu.equals(rs.getString("idUsuario"))) {
                    veriusu = rs.getString("idUsuario");}
                if (contra.equals(rs.getString("contraseña"))) {
                    vericontra = rs.getString("contraseña");}
                if (seleccionar.equals(rs.getString("tipoUsuario"))) {
                    selec = rs.getString("tipoUsuario");}}}
        catch (SQLException ex) {
            ex.printStackTrace();}
        System.out.println("El usuario es: " + veriusu);
        System.out.println("La contraseña es: " + vericontra);
        System.out.println("El cargo es: " + selec);}

    public static void main(String[] args) {
        JFrame frame = new JFrame("conexion");
        frame.setContentPane(new conexion().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

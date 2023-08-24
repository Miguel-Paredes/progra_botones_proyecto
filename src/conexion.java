import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class conexion {
    private JTextField usuario;
    JPanel JPanel;
    private JPasswordField contrasenia;
    private JButton verificarButton;
    private JComboBox<String> comboBox1;
    static String DB_URL = "jdbc:mysql://localhost/medical";
    static String USER = "root";
    static String PASS = "root";
    static String QUERY = "SELECT * FROM Usuario";
    static String veriusu;
    static String vericontra;
    static String selec = "Seleccionar";
    static String usu;
    static String contra;
    static String seleccionar;

    public conexion() {
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarConexion();
                JFrame frame;
                boolean hayInformacionUsuario = !usuario.getText().isEmpty();
                boolean hayInformacionContrasenia = !contrasenia.getText().isEmpty();
                if(hayInformacionUsuario==true || hayInformacionContrasenia == true){
                    if(selec.equals("Administrador") || selec.equals("Cajero")){
                        if(selec.equals("Administrador") && selec.equals(seleccionar) && veriusu.equals(usuario.getText()) && vericontra.equals(new String(contrasenia.getPassword()))){
                            frame = new JFrame("Administrador");
                            frame.setContentPane(new Administrador().admi);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            closeLoginFrame();
                            frame.pack();
                            frame.setSize(400,200);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);}
                        else if(selec.equals("Cajero") && selec.equals(seleccionar) && veriusu.equals(usuario.getText()) && vericontra.equals(new String(contrasenia.getPassword()))){
                            frame = new JFrame("Cajero");
                            frame.setContentPane(new Cajero().caje);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            closeLoginFrame();
                            frame.pack();
                            frame.setSize(400,200);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);}}
                    else if(selec.equals("Seleccionar")){
                        frame = new JFrame("Credenciales");
                        frame.setContentPane(new Credenciales().crede);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.pack();
                        frame.setSize(200, 200);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);}}
                else{
                    frame = new JFrame("Incorrecto");
                    frame.setContentPane(new Incorrecto().inco);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setSize(200, 200);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    hayInformacionUsuario=true;
                    hayInformacionContrasenia=true;}

                if (seleccionar.equals("Seleccione") && hayInformacionUsuario==false && hayInformacionContrasenia==false){
                    frame = new JFrame("Incorrecto");
                    frame.setContentPane(new Incorrecto().inco);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setSize(200, 200);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);}
                veriusu = "";
                vericontra = "";
                selec = "Seleccionar";
            }});}

    private void closeLoginFrame() {
        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(JPanel);
        loginFrame.dispose();}

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
                if (contra.equals(rs.getString("contraseña")) && usu.equals(rs.getString("idUsuario"))) {
                    vericontra = rs.getString("contraseña");}
                if (seleccionar.equals(rs.getString("tipoUsuario")) && contra.equals(rs.getString("contraseña")) && usu.equals(rs.getString("idUsuario"))) {
                    selec = rs.getString("tipoUsuario");}}}
        catch (SQLException ex) {
            ex.printStackTrace();}
        System.out.println("El usuario es: " + veriusu);
        System.out.println("La contraseña es: " + vericontra);
        System.out.println("El cargo es: " + selec);}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new conexion().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

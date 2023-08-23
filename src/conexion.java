import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class conexion {
    private JTextField usuario;
    private JPanel JPanel;
    private JPasswordField contrasenia;
    private JButton verificarButton;
    static String DB_URL = "jdbc:mysql://localhost/medical";
    static String USER = "root";
    static String PASS = "root";
    static String QUERY = "SELECT * FROM Usuario";
    static String veriusu;
    static String vericontra;
    static String usu;
    static String contra;

    public conexion() {
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usu = usuario.getText().trim();
                contra = new String(contrasenia.getPassword()).trim();
                System.out.println(usu);
                System.out.println(contra);

                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(QUERY)) {

                    while (rs.next()) {
                        if (usu.equals(rs.getString("idUsuario"))) {
                            veriusu = rs.getString("idUsuario");
                        }
                        if (contra.equals(rs.getString("contraseña"))) {
                            vericontra = rs.getString("contraseña");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                System.out.println("El usuario es: " + veriusu);
                System.out.println("La contraseña es: " + vericontra);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Conexion");
                frame.setContentPane(new conexion().JPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}

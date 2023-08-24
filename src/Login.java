import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JComboBox<String> comboBox1;
    JPanel JPanel;
    private JTextField usuario;
    private JPasswordField contrasenia;
    private JButton entrarButton;
    private String seleccionar;
    private String admin="admin";
    private String contradmin="admin";
    private String cajero="caje";
    private String contracajero="caje";

    public Login() {
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionar = (String) comboBox1.getSelectedItem();
                JFrame frame;
                boolean hayInformacionUsuario = !usuario.getText().isEmpty();
                boolean hayInformacionContrasenia = !contrasenia.getText().isEmpty();
                if(hayInformacionUsuario==true || hayInformacionContrasenia == true){

                    if (admin.equals(usuario.getText()) && contradmin.equals(new String(contrasenia.getPassword())) || cajero.equals(usuario.getText()) && contracajero.equals(new String(contrasenia.getPassword()))) {
                        if (seleccionar.equals("Administrador") && admin.equals(usuario.getText()) && contradmin.equals(new String(contrasenia.getPassword()))) {
                            frame = new JFrame("Administrador");
                            frame.setContentPane(new Administrador().admi);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            closeLoginFrame();
                            frame.pack();
                            frame.setSize(400,200);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);}

                        else if (seleccionar.equals("Cajero") && cajero.equals(usuario.getText()) && contracajero.equals(new String(contrasenia.getPassword()))) {
                            frame = new JFrame("Cajero");
                            frame.setContentPane(new Cajero().caje);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            closeLoginFrame();
                            frame.pack();
                            frame.setSize(400,200);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);}}

                    else {
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
            }
        });
    }

    private void closeLoginFrame() {
        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(JPanel);
        loginFrame.dispose();}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);}
}

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

    public Login() {
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionar = (String) comboBox1.getSelectedItem();
                JFrame frame;
                if (seleccionar.equals("Administrador")) {
                    frame = new JFrame("Administrador");
                    frame.setContentPane(new Administrador().admi);}
                else if (seleccionar.equals("Cajero")) {
                    frame = new JFrame("Cajero");
                    frame.setContentPane(new Cajero().caje);}
                else {
                    System.out.println("No ha seleccionado ninguna opcion");
                    return;}
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                closeLoginFrame();
                frame.pack();
                frame.setSize(400, 200);
                frame.setVisible(true);
            }
        });
    }

    private void closeLoginFrame() {
        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(JPanel);
        loginFrame.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
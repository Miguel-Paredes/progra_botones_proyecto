import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cajero {
    JPanel caje;
    private JButton regresarButton;

    public Cajero() {
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Login");
                frame.setContentPane(new conexion().JPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(400,200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

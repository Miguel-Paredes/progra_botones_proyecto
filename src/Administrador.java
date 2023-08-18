import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrador {
    JPanel admi;
    private JButton regresarButton;

public Administrador() {
    regresarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(admi);
            currentFrame.dispose();
            JFrame frame = new JFrame("Login");
            frame.setContentPane(new Login().JPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(400, 200);
            frame.setVisible(true);
        }
    });
}
}
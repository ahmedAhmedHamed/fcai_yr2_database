import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JFrame frame;
    JButton login_button;
    JPanel panel;
    JButton register_button;

    public GUI() {
         frame = new JFrame("My First GUI");
         login_button = new JButton("Login");
         register_button = new JButton("Register");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(login_button);
        panel.add(register_button);

        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

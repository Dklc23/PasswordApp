package PassApp;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;



public class PasswordApp {
    private JPanel panel1;
    private JButton loginButton;
    private JPasswordField PasswordField;
    private JTextField UsernameField;
    private JButton Create;

    private PasswordApp() {
        loginButton.addActionListener(e -> {
            String user = UsernameField.getText();
            char[] pass = PasswordField.getPassword();
            JFrame popup = new JFrame();
            JOptionPane.showMessageDialog(popup, "User: " + user + "\n" + "Pass: " + Arrays.toString(pass) + "\n");

        });
        loginButton.addActionListener(e -> {

        });
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Password");
        frame.setContentPane(new PasswordApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

}

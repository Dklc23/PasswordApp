package PassApp;

import javax.swing.*;
import java.util.Arrays;


public class PasswordApp {
    private JPanel panel1;
    private JButton Create;
    private JPasswordField PasswordField;
    private JTextField UsernameField;
    private JButton Login;

    private PasswordApp() {
        final String[] user = new String[1];
        final char[][] pass = new char[1][1];
        Login.addActionListener(e -> {
            user[0] = UsernameField.getText();
            pass[0] = PasswordField.getPassword();
            JFrame popup = new JFrame();
            JOptionPane.showMessageDialog(popup, "User: " + user[0] + "\n" + "Pass: " + Arrays.toString(pass[0]) + "\n");

        });
        Create.addActionListener(e -> {
            user[0] = UsernameField.getText();
            pass[0] = PasswordField.getPassword();
            try {
                Account n = new Account(user[0],pass[0]);
                System.out.println(n.Hash);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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

package PassApp;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


public class PasswordApp {
    private JPanel panel1;
    private JButton Create;

    private JButton Login;
    private JPasswordField PasswordField;
    private JTextField UsernameField;

    private PasswordApp() {
        final JFrame popup = new JFrame("Password");
        Login.addActionListener(e -> {

            String passw = new String(PasswordField.getPassword());
            System.out.println(UsernameField.getText() + "" + passw);
            Account n = Database.getAccMap().get(UsernameField.getText());
            String compHash = "1";

            if (n != null) {
                try {
                    compHash = generateHash(passw, n.Salt);

                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                }

                if (n.Hash.equals(compHash)) {

                    JOptionPane.showMessageDialog(popup, "Successfully Logged in");
                } else {

                    JOptionPane.showMessageDialog(popup, "Failed to Logged in");
                }
            } else System.out.println("no account exists");

        });


        Create.addActionListener(e -> {
            try {

                if (!Database.getAccMap().containsKey(UsernameField.getText())) {
                    Account n = new Account(UsernameField.getText(), PasswordField.getPassword());
                    JOptionPane.showMessageDialog(popup, "Account successfully created\n              Welcome " + UsernameField.getText());
                    Database.getAccMap().put(UsernameField.getText(), n);
                } else {
                    JOptionPane.showMessageDialog(popup, "Account Already Exist");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }


    private static String generateHash(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        String pass = password + salt;
        byte[] hash = digest.digest(pass.getBytes());
        return bytesToHex(hash);


    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Database.readAccToMap();
        final JFrame frame = new JFrame("Password");
        frame.setContentPane(new PasswordApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("In shutdown hook");
                try {
                    Database.writeAcc();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "Shutdown-thread"));
    }
}



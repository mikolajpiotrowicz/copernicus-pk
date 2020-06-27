import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class LoginFrame extends JPanel {
    private JTextField txtIndex;
    private JTextField txtPassword;

    public LoginFrame(final Application application) {
        setBounds(100, 100, 200, 225);
        setLayout(null);

        JLabel lblFirstName = new JLabel("Student index");
        lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFirstName.setBounds(15, 5, 162, 14);
        add(lblFirstName);
        txtIndex = new JTextField();
        txtIndex.setBounds(10, 30, 162, 20);
        txtIndex.setColumns(10);
        add(txtIndex);



        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPassword.setBounds(15, 70, 162, 14);
        add(lblPassword);
        txtPassword = new JTextField();
        txtPassword.setBounds(10, 90, 162, 20);
        txtPassword.setColumns(10);
        add(txtPassword);

        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fname = "";
                String fpassword = "";
                try {
                    fname = txtIndex.getText();
                    fpassword = txtPassword.getText();
                    if (fname.trim().isEmpty() || fpassword.trim().isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    application.http.login(fname, fpassword);
                    application.frame.setSize(new Dimension(500, 400 ));
                    application.libraryView.setLayout(null);
                    application.libraryView.init();
                    application.cl.show(application.panelCont, "3");

                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(null, "Please Enter Student index", "Entry Error", JOptionPane.ERROR_MESSAGE);
                    txtIndex.requestFocus();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        btnLogin.setBounds(10, 120, 160, 23);
        add(btnLogin);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Loading extends JPanel {
    public JLabel imageLabel = new JLabel();

    public Loading() {
        setLayout(null);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(
                "ajax-loader.gif"));
        imageLabel.setIcon(ii);
        imageLabel.setBounds(85, 65, 20, 20);
        add(imageLabel);
    }
}

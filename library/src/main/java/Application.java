import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class Application {
    public Student student = new Student();
    CardLayout cl = new CardLayout();
    JFrame frame = new JFrame("CardLayout demo");
    JPanel panelCont = new JPanel();

    LoginFrame loginPanel = new LoginFrame(this);
    BooksRepository repository = new BooksRepository(this);
    LibraryView libraryView = new LibraryView(this);
    Loading loadingView = new Loading();
    Http http = new Http(this);
    public Application() throws Exception {
        frame.setResizable(false);
        panelCont.setLayout(cl);
        frame.setPreferredSize(new Dimension(200, 200));
        panelCont.add(loginPanel, "1");
        panelCont.add(loadingView, "2");
        panelCont.add(libraryView, "3");
        cl.show(panelCont, "1");


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2) - 100;
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2) - 100;
        frame.setLocation(x, y);

        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Application();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

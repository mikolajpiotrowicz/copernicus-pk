import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

public class LibraryView extends JPanel {
    public String borrowId;
    public String currentId;
    final DefaultListModel availableModel = new DefaultListModel();
    final DefaultListModel currentModel = new DefaultListModel();
    JList availableList;
    JList borrowedList;
    JScrollPane scrollableAvailableWrapper;
    JScrollPane scrollableBorrowedWrapper;
    Application container;
    JButton borrowButton = new JButton("Borrow");
    JButton returnButton = new JButton("Return");


    LibraryView(Application application) throws IOException {
        container = application;

        availableModel.clear();
        System.out.println(application);
        String[] availableBooks = application.repository.getAvailableBookDisplays();
        int repositoryAvailableBooksCount = 0;
        if(availableBooks != null) {
            repositoryAvailableBooksCount = availableBooks.length;
        }
        for (int i = 0, n = repositoryAvailableBooksCount; i < n; i++) {
            availableModel.addElement(availableBooks[i]);
        }
        availableList = new JList(availableModel);


        currentModel.clear();
        String[] currentBooks = application.repository.getAvailableBookDisplays();
        int repositoryCurrentBooksCount = 0;
        if(currentBooks != null) {
            repositoryCurrentBooksCount = availableBooks.length;
        }
        for (int i = 0, n = repositoryCurrentBooksCount; i < n; i++) {
            currentModel.addElement(currentBooks[i]);
        }
        availableList = new JList(availableModel);
        borrowedList = new JList(currentModel);

        availableList.setFixedCellWidth(250);
        borrowedList.setFixedCellWidth(250);

        scrollableAvailableWrapper = new JScrollPane(availableList);
        scrollableBorrowedWrapper = new JScrollPane(borrowedList);

        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    handleBorrowClick();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    handleReturnClick();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

        add(scrollableAvailableWrapper);
        add(scrollableBorrowedWrapper);
        add(borrowButton);
        add(returnButton);

    }

    private void handleReturnClick() throws Exception {
        container.http.returnBook(currentId);
        availableModel.clear();
        String[] availableBooks = container.repository.getAvailableBookDisplays();
        int repositoryBooksCount = availableBooks.length;
        System.out.println("Rerender available books count " + repositoryBooksCount);
        for (int i = 0, n = repositoryBooksCount; i < n; i++) {
            availableModel.addElement(availableBooks[i]);
        }


        currentModel.clear();
        String[] currentBooks = container.repository.getCurrentBookDisplays();
        int repositoryCurrentBooksCount = currentBooks.length;
        System.out.println("Rerender current books count " + repositoryBooksCount);
        for (int i = 0, n = repositoryCurrentBooksCount; i < n; i++) {
            currentModel.addElement(currentBooks[i]);
        }

        container.frame.repaint();
    }

    private void handleBorrowClick() throws Exception {
        container.http.rent(borrowId);
        availableModel.clear();
        String[] availableBooks = container.repository.getAvailableBookDisplays();
        int repositoryBooksCount = 0;
        if(availableBooks != null) {
            repositoryBooksCount = availableBooks.length;
        }
        System.out.println("Rerender available books count " + repositoryBooksCount);
        for (int i = 0, n = repositoryBooksCount; i < n; i++) {
            availableModel.addElement(availableBooks[i]);
        }


        currentModel.clear();
        String[] currentBooks = container.repository.getCurrentBookDisplays();

        int repositoryCurrentBooksCount = 0;
        if(availableBooks != null) {
            repositoryCurrentBooksCount = currentBooks.length;
        }

        System.out.println("Rerender current books count " + repositoryBooksCount);
        for (int i = 0, n = repositoryCurrentBooksCount; i < n; i++) {
            currentModel.addElement(currentBooks[i]);
        }

        container.frame.repaint();
    }
    public void init() throws IOException {
        scrollableAvailableWrapper.setBounds(10, 10, 225, 300);
        scrollableBorrowedWrapper.setBounds(250, 10, 225, 300);
        borrowButton.setBounds(10, 320, 225, 30);
        returnButton.setBounds(250, 320, 225, 30);
        container.repository.fetchAvailableBooks();
        container.repository.fetchCurrentBooks();
        currentModel.clear();
        String[] currentBooks = container.repository.getCurrentBookDisplays();
        int repositoryCurrentBooksCount = 0;
        if(currentBooks != null) {
            repositoryCurrentBooksCount = currentBooks.length;
        }

        for (int i = 0, n = repositoryCurrentBooksCount; i < n; i++) {
            currentModel.addElement(currentBooks[i]);
        }

        availableModel.clear();
        String[] books = container.repository.getAvailableBookDisplays();
        int repositoryBooksCount = 0;
        if(books != null) {
            repositoryBooksCount = books.length;
        }
        for (int i = 0, n = repositoryBooksCount; i < n; i++) {
            availableModel.addElement(books[i]);
        }
        availableList.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String strItem = (String) availableList.getSelectedValue();
                if(strItem == null) {
                    return;
                }
                System.out.println("Item Selected: " + strItem);
                borrowId = strItem.substring(0, strItem.indexOf(":"));
            }
        });
        borrowedList.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String strItem = (String) borrowedList.getSelectedValue();
                if(strItem == null) {
                    return;
                }
                System.out.println("Item Selected: " + strItem);
                currentId = strItem.substring(0, strItem.indexOf(":"));
            }
        });
        System.out.println(Arrays.toString(container.repository.getAvailableBookDisplays()));
        container.frame.repaint();
    }
}

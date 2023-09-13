import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextEditor extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    JToolBar toolBar;

    TextEditor(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("NotePad");
        this.setSize(500, 600);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (textArea.getSelectedText() != null) {
                    showToolBar(e.getX(), e.getY());
                } else {
                    hideToolBar();
                }
            }
        });
        textArea.setFont(new Font("Poppins", Font.PLAIN, 20));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 550));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        toolBar = new JToolBar();
        toolBar.setFloatable(false); // To prevent the toolbar from being dragged
        add(toolBar, BorderLayout.NORTH);
        hideToolBar(); // Initially hide the toolbar

        this.add(scrollPane);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void showToolBar(int x, int y) {
        // Add your toolbar buttons for changing font and font size here
        JButton changeFontButton = new JButton(this.getName());
        JButton changeFontSize = new JButton(Integer.toString(this.getFont().getSize()));
        JButton increaseFontSizeButton = new JButton("A+");
        JButton decreaseFontSizeButton = new JButton("A-");

        String[] fontSizeOptions = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "34", "38", "46", "54", "62", "70", "76"};

        // Action listener to handle font changes
        changeFontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FontChooserDialog.showFontChooser(TextEditor.this, textArea);
            }
        });

        // Action listener to handle font size change
        changeFontSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        toolBar.removeAll(); // Clear existing buttons
        toolBar.add(changeFontButton);
        toolBar.add(changeFontSize);
        toolBar.add(increaseFontSizeButton);
        toolBar.add(decreaseFontSizeButton);

        toolBar.setLocation(x, y);
        toolBar.setVisible(true);
        toolBar.revalidate();
        toolBar.repaint();
    }

    private void hideToolBar() {
        toolBar.setVisible(false);
    }
}

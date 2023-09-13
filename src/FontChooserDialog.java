import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FontChooserDialog extends JDialog {
    private JComboBox<String> fontComboBox;
    private JComboBox<Integer> fontSizeComboBox;
    private JTextArea textArea;

    public FontChooserDialog(JFrame parent, JTextArea textArea) {
        super(parent, "Font Chooser", true);
        this.textArea = textArea;

        setSize(400, 200);
        setLocationRelativeTo(parent);

        fontComboBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontSizeComboBox = new JComboBox<>(new Integer[]{8, 10, 12, 14, 16, 18, 20});
        JButton applyButton = new JButton("Apply");

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedFont = (String) fontComboBox.getSelectedItem();
                int selectedSize = (Integer) fontSizeComboBox.getSelectedItem();
                Font newFont = new Font(selectedFont, Font.PLAIN, selectedSize);
                textArea.setFont(newFont);
                setVisible(false);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Font:"));
        panel.add(fontComboBox);
        panel.add(new JLabel("Font Size:"));
        panel.add(fontSizeComboBox);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(applyButton);

        add(panel);
    }

    public static void showFontChooser(JFrame parent, JTextArea textArea) {
        FontChooserDialog dialog = new FontChooserDialog(parent, textArea);
        dialog.setVisible(true);
    }
}

import components.FileIn;
import components.OSName;
import components.TextFilter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static components.save.save;

public class Main extends JFrame {

    public static JFrame frame = new JFrame();

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new MainFrame(frame);
            latch.countDown();
        });
        latch.await();
    }
}

class MainFrame extends JFrame {
    static TextPane left = new TextPane();
    static MenuBar theJMenuBar = new MenuBar();

    public MainFrame(JFrame frame) {
        frame.setLayout(new BorderLayout(10, 10));
        frame.setJMenuBar(theJMenuBar.menuBar);
        //frame.add(new InputPane().panel, BorderLayout.NORTH);
        frame.add(left.jScrollPane);


        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
        frame.repaint();
    }

}

class MenuBar extends JMenuBar {
    JMenuBar menuBar;
    JMenu menu;
    static JMenuItem m1, m2, m3;
    static JFileChooser chooser = new JFileChooser(String.valueOf(new OSName().getOsName()));
    public FileIn fileIn;



    public MenuBar(){
        chooser.addChoosableFileFilter(new TextFilter());
        chooser.setAcceptAllFileFilterUsed(false);
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        // create menuItems
        m1 = new JMenuItem("Open");
        m2 = new JMenuItem("Save");
        m3 = new JMenuItem("Close");

        m1.addActionListener(ae -> {
            File sourceFile;
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = chooser.showDialog(null, "Open");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                sourceFile = chooser.getSelectedFile();
                String sourceFilePath = sourceFile.getAbsolutePath();
                try {
                    fileIn = new FileIn(MainFrame.left.textPane, sourceFilePath);
                } catch (IOException | BadLocationException e) {
                    e.printStackTrace();
                }
            }
        });

        m2.addActionListener(ae -> {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path=chooser.getSelectedFile().getAbsolutePath();
                save(path, MainFrame.left.textPane.getText());
            }
        });

        m3.addActionListener(ae -> MainFrame.left.textPane.setText(""));


        // add menu items to menu
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);

        // add menu to menu bar
        menuBar.add(menu);
    }
}
class TextPane extends JPanel {
    JScrollPane jScrollPane = new JScrollPane();
    public JTextPane textPane = new JTextPane();
    private final JTextPane lines;
    protected Border border = BorderFactory.createBevelBorder(1);

    public TextPane() {
        textPane.setPreferredSize(new Dimension(1100, 450));
        jScrollPane.setPreferredSize(new Dimension(1100, 500));
        jScrollPane.setBorder(border);

        //---LineNumbers------------
        lines = new JTextPane();
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        // Implements Document Listener in order to add the numbered rows
        // It uses a JTextPane that is not editable
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                // caretPosition describes the position of the cursor in by length of the document (Char position).
                int caretPosition = textPane.getDocument().getLength();
                // Root describes the Branch Element Section For each line from 0 to the actual length.
                Element root = textPane.getDocument().getDefaultRootElement();
                // root.getElementIndex(caretPosition)+1 is the row number plus 1. Because rows start from 0
                StringBuilder text = new StringBuilder("1" + System.getProperty("line.separator"));
                for (int i = 2; i < root.getElementIndex(caretPosition)+2; i++) {
                    text.append(i).append(System.getProperty("line.separator"));
                }
                return text.toString();
            }
            // This is a bunch of superfluous Java code that Java makes you write for some reason.
            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

        });
        jScrollPane.getViewport().add(textPane);
        jScrollPane.setRowHeaderView(lines);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

}
/*
------------------------------------------------------------------------------------------------------------------------
 TextFields and Buttons - I will change this in the future when I implement a Undoable edit listener
------------------------------------------------------------------------------------------------------------------------
*/

/*class InputPane extends JPanel {
    final static boolean shouldFill = true;
    final static boolean RIGHT_TO_LEFT = false;
    public JPanel panel = new JPanel();
    public FileIn fileIn;

    public InputPane() {
        if (RIGHT_TO_LEFT) {
            panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        JButton button;
        JTextField txtField;

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        //____________________________________________________________________________________________________________________________
        txtField = new JTextField("/Users/AGracias/Desktop/CMIS202/Major_Project/src/main/resources/TextFile/menuText/textFile1.txt");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;   //request any extra vertical space
        c.anchor = GridBagConstraints.WEST; //left of space
        //c.insets = new Insets(5,0,5,0);  //top padding
        c.gridx = 0;
        c.gridwidth = 3;   //3 columns wide
        c.gridy = 0;       //third row
        panel.add(txtField, c);
        // clears the txtField when clicked
        JTextField finalTxtField1 = txtField;
        txtField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                finalTxtField1.setText("");
            }
        });

        button = new JButton("Load File");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 6;
        c.gridy = 0;
        // Action Listener that implements the components.FileIn Class
        JTextField finalTxtField = txtField;
        button.addActionListener(ae -> {
            // Enter String
            String stringValue  = finalTxtField.getText().toUpperCase();
            // Removes the File In: text when drag and drop
            stringValue = stringValue.substring(stringValue.indexOf(':')+1);
            try {
                fileIn = new FileIn(MainFrame.left.textPane,stringValue);
            } catch (IOException | BadLocationException e) {
                e.printStackTrace();
            }

        });
        panel.add(button, c);

        button = new JButton("Merge Right");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 10;
        c.gridy = 0;
        panel.add(button, c);
        //----------------------------------------------------------
        // No camera for now
//*
// Camera button
        button = new JButton("Camera 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;//make button bigger
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 14;
        c.gridy = 0;
        panel.add(button, c);

        //-----------------------------------------------------------
    }*/
//}

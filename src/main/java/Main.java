import components.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

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
    static TextPane left;

    static {
        try {
            left = new TextPane();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

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
    JMenu menu, style;
    static JMenuItem m1, m2, m3;
    static JFileChooser chooser = new JFileChooser(String.valueOf(new OSName().getOsName()));
    public FileIn fileIn;




    public MenuBar(){
        chooser.addChoosableFileFilter(new TextFilter());
        chooser.setAcceptAllFileFilterUsed(false);
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        style = new JMenu("Style");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        menuBar.add(style);

        // create menuItems
        m1 = new JMenuItem("Open");
        m2 = new JMenuItem("Save");
        m3 = new JMenuItem("Close");

        Action action = new StyledEditorKit.BoldAction();
        action.putValue(Action.NAME, "Bold");
        style.add(action);
        Action action1 = new StyledEditorKit.ItalicAction();
        action1.putValue(Action.NAME, "Italics");
        style.add(action1);
        Action action2 = new StyledEditorKit.UnderlineAction();
        action2.putValue(Action.NAME, "Underline");
        style.add(action2);
        Action action3 = new StyledEditorKit.FontSizeAction(Action.LARGE_ICON_KEY, 20 );
        action3.putValue(Action.NAME, "Increase size");
        style.add(action3);

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
                try {
                    new save(path, MainFrame.left.textPane.getText());
                } catch (IOException ignored) {

                }
            }
        });

        m3.addActionListener(ae -> MainFrame.left.textPane.setText(""));


        // add menu items to menu
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
    }
}
class TextPane extends JPanel {
    JScrollPane jScrollPane = new JScrollPane();
    public JTextPane textPane = new JTextPane();
    private final JTextPane lines;
    protected Border border = BorderFactory.createBevelBorder(1);
    WordList wordList = new WordList();

    public TextPane() throws BadLocationException {
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
            int x = -1;
            int lineStart = 0;
            int index = 0;
            public String getText() throws BadLocationException {
                int caretPosition = textPane.getDocument().getLength(); //The end of all the text
                //root is row number(considering arrays start at 0 so before there is a line Default Root Element starts at -1
                Element root = textPane.getDocument().getDefaultRootElement();
                StringBuilder text = new StringBuilder("1" + System.getProperty("line.separator"));
                // root.getElementIndex(caretPosition) + 2 uses an off set to find current line number(+2 because default starts at -1
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text.append(i).append(System.getProperty("line.separator"));
                }
                int row = root.getElementIndex(caretPosition)-1;
                if(row != x) {
                    wordList.setV(textPane.getDocument().getText(lineStart, caretPosition-lineStart));
                    wordList.setLines();
                    wordList.setIndex(index);
                    x++;
                    lineStart = caretPosition;
                    index++;
                }

                return text.toString();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                try {
                    lines.setText(getText());
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                try {
                    lines.setText(getText());
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                try {
                    lines.setText(getText());
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        jScrollPane.getViewport().add(textPane);
        jScrollPane.setRowHeaderView(lines);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    public void updateWordMap(){
        System.out.println(wordList);
    }
}


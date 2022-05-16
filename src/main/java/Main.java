import Maps.PositionList;
import Maps.WordList;
import components.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Main extends JFrame {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
            latch.countDown();
        });
        latch.await();
    }


    public static class MainFrame extends JFrame {
        public static JFrame frame = new JFrame();
        static TextPane left;
        static TextPane right;

        static {
            try {
                left = new TextPane();
                right = new TextPane();
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
        }

        static JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left.jScrollPane, right.jScrollPane);

        public double divPos = 0.5;
        public static String focus = "Left";

        public MainFrame() {
            FocusListener fl = new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //System.out.println("focusGained e.getSource().c=" + ((JTextPane) e.getSource()).getName());
                    focus = ((JTextPane) e.getSource()).getName();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    //System.out.println("focusLost e.getSource().c=" + ((JTextPane) e.getSource()).getName());
                }
            };
            left.textPane.setName("Left");
            right.textPane.setName("Right");
            left.textPane.addFocusListener(fl);
            right.textPane.addFocusListener(fl);
            splitPane.setDividerLocation(divPos);
            splitPane.setResizeWeight(0.5);
            Dimension minimumSize = new Dimension(600, 500);
            left.jScrollPane.setMinimumSize(minimumSize);
            right.jScrollPane.setMinimumSize(minimumSize);

            frame.setJMenuBar(MenuBar.menuBar());
            frame.add(splitPane);
            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
            frame.validate();
            frame.repaint();
        }

        public static JTextPane Pane() {
            JTextPane Pane;
            if (MainFrame.focus.matches("Left")) {
                Pane = MainFrame.left.textPane;
            } else {
                Pane = MainFrame.right.textPane;
            }
            return Pane;
        }

    }
/*class Focus{
    public
}*/

    static class MenuBar extends JMenuBar {
        private static final JFileChooser chooser = new JFileChooser(String.valueOf(new OSName().getOsName()));
        public static FileIn fileIn;

        public static JMenuBar menuBar() {
            chooser.addChoosableFileFilter(new TextFilter());
            chooser.setAcceptAllFileFilterUsed(false);
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            JMenu style = new JMenu("Style");
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription(
                    "The only menu in this program that has menu items");
            menuBar.add(menu);
            menuBar.add(style);

            // create menuItems
            JMenuItem m1 = new JMenuItem("Open");
            JMenuItem m2 = new JMenuItem("Save");
            JMenuItem m3 = new JMenuItem("Close");

            Action action = new StyledEditorKit.BoldAction();
            action.putValue(Action.NAME, "Bold");
            style.add(action);
            Action action1 = new StyledEditorKit.ItalicAction();
            action1.putValue(Action.NAME, "Italics");
            style.add(action1);
            Action action2 = new StyledEditorKit.UnderlineAction();
            action2.putValue(Action.NAME, "Underline");
            style.add(action2);
            Action action3 = new StyledEditorKit.FontSizeAction(Action.LARGE_ICON_KEY, 20);
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
                        fileIn = new FileIn(MainFrame.Pane(), sourceFilePath);

                    } catch (IOException | BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            });

            m2.addActionListener(ae -> {
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    try {
                        new save(path, MainFrame.Pane().getText());
                    } catch (IOException ignored) {

                    }
                }
            });

            m3.addActionListener(ae -> MainFrame.Pane().setText(""));


            // add menu items to menu
            menu.add(m1);
            menu.add(m2);
            menu.add(m3);
            return menuBar;
        }
    }

    public static class TextPane extends JPanel {
        public JTextPane textPane = new JTextPane();
        JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        private final JTextPane lines;
        WordList wordList = new WordList();
        PositionList positionList = new PositionList();
        WordList wordList2 = new WordList();


        public TextPane() throws BadLocationException {
            jScrollPane.setMaximumSize(new Dimension(500, 900));
            jScrollPane.setPreferredSize(new Dimension(500, 700));


            //---LineNumbers------------
            lines = new JTextPane();
            lines.setBackground(Color.LIGHT_GRAY);
            lines.setEditable(false);
            textPane.addMouseListener(new MyListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    /*int caretPosition = textPane.getDocument().getLength();
                    System.out.println(caretPosition);*/
                    /** for testing, getting location of caret and highlighting
                     * int offset = textPane.getCaretPosition();
                     *  Rectangle location;
                     *  int x = MainFrame.splitPane.getDividerLocation();
                     *  System.out.println(offset);
                     *  try {
                     *  location = (Rectangle) textPane.modelToView2D(offset);
                     *  } catch (BadLocationException ex) {
                     *  throw new RuntimeException(ex);
                     *  }
                     *  System.out.println(MainFrame.focus);**/

                }
            });
            int[] lineStart = {0};
            int[] x = {0};

            // Implements Document Listener in order to add the numbered rows
            // It uses a JTextPane that is not editable
            textPane.getDocument().addDocumentListener(new DocumentListener() {

                public String getText() {

                    int caretPosition = textPane.getDocument().getLength();
                    Element root = textPane.getDocument().getDefaultRootElement();
                    StringBuilder text = new StringBuilder("1" + System.getProperty("line.separator"));
                    for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                        text.append(i).append(System.getProperty("line.separator"));
                    }
                    return text.toString();
                }

                /** Do Not Delete Text in Document - Backspace is not allowed!
                 *
                 * @throws BadLocationException
                 *
                 * See Notes in test folder.
                 */
                public void wordIndex() throws BadLocationException {
                    int caretPosition = textPane.getDocument().getLength();
                    Element root = textPane.getDocument().getDefaultRootElement();
                    int row = root.getElementIndex(caretPosition);
                    //check if row is always +1 x[0]
                /*System.out.println("before x "+x[0]);
                System.out.println("row "+row);*/
                    if (row > x[0]) {
                        String lines = textPane.getDocument().getText(lineStart[0], caretPosition - lineStart[0]);
                        if (MainFrame.focus.matches("Left")) {
                            wordList.setV(lines);
                            positionList.setV(lines, textPane);
                            wordList.setRelatedSources(MainFrame.focus);
                            //wordList.print();

                            //System.out.println(wordList.toString());
                        } else {
                            wordList2.setV(lines);
                            wordList2.setRelatedSources(MainFrame.focus);
                            //System.out.println(wordList2.toString());
                        }
                        x[0]++;
                        //System.out.println("after x " + x[0]);
                        //System.out.println("after row " + row);
                        lineStart[0] = caretPosition;
                    } else if (row < x[0]) {
                        if (!(wordList.sentences.isEmpty())) {
                            if (MainFrame.focus.matches("Left")) {
                                wordList.resetSentences();
                            } else {
                                wordList2.resetSentences();
                            }
                        }
                        x[0]--;
                        lineStart[0] = caretPosition + lineStart[0];
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent de) {
                    lines.setText(getText());
                    try {
                        wordIndex();
                    } catch (BadLocationException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void insertUpdate(DocumentEvent de) {
                    lines.setText(getText());
                    try {
                        wordIndex();
                    } catch (BadLocationException e) {
                        throw new RuntimeException(e);
                    }

                }

                @Override
                public void removeUpdate(DocumentEvent de) {
                    lines.setText(getText());
                    try {
                        wordIndex();
                    } catch (BadLocationException e) {
                        throw new RuntimeException(e);
                    }
                }


            });
            jScrollPane.getViewport().add(textPane);
            jScrollPane.setRowHeaderView(lines);
        }

    }
}


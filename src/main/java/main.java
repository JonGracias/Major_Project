import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;

public class main {
    public static InputPane inputPanel = new InputPane(Color.DARK_GRAY);
    public static TextPane leftPanel = new TextPane(Color.DARK_GRAY);
    public static TextPane rightPanel = new TextPane( Color.darkGray);
    public static OutputPane outputPane = new OutputPane(Color.darkGray);
    public static JFrame frame = new JFrame();
    public static void main(String[] args) {

        frame.setLayout(new BorderLayout(10, 10));
        frame.add(inputPanel.panel, BorderLayout.NORTH);
        frame.add(leftPanel.panel, BorderLayout.WEST);
        frame.add(rightPanel.panel, BorderLayout.EAST);
        frame.add(outputPane.panel, BorderLayout.SOUTH);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
        frame.repaint();

    }

    // Serialize the JPanel for saving --------------------------------------------------
    public static void writeObject() {
        // file location
        String file = "src/main/resources/TextFile/Saves/";

        try {
            //Saving of object in a file
            FileOutputStream fileName = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileName);

            // Method for serialization of object
            out.writeObject(null);

            out.close();
            fileName.close();

            JOptionPane.showMessageDialog(frame, "Jpanel has been Saved");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "IOException is caught");
        }
    }

    // reads object and sends to addStarsToHistory()
    public static void readObject() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("src/main/resources/TextFile/Saves"));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                String filename = fileChooser.getSelectedFile().getName();

                // Reading the object from a file
                FileInputStream file = new FileInputStream(path);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                JPanel panel = (JPanel) in.readObject();

                in.close();
                file.close();
                //(output).addPanel(filename, panel);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "IOException is caught");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "ClassNotFoundException is caught");
            }
        }
    }
}

class TextPane extends JPanel {
    public JPanel panel = new JPanel();
    public JTextPane textPane = new JTextPane();
    protected Border border = BorderFactory.createBevelBorder(1);

    //Add drag and drop here

    public TextPane(Color c){
        textPane.setPreferredSize(new Dimension(450, 450));
        panel.setPreferredSize(new Dimension(595, 500));
        panel.setLayout(new BorderLayout());
        panel.add(textPane, BorderLayout.CENTER);
        panel.setBackground(c);
        panel.setBorder(border);

    }
}

class InputPane extends JPanel {
    public JPanel panel = new JPanel();
    public JButton cameraButton = new JButton();
    public JButton leftButton = new JButton();
    public JButton rightButton = new JButton();
    public JButton lGoButton = new JButton();
    public JButton rGoButton = new JButton();
    public JTextField leftPanel = new JTextField();
    public JTextField rightPanel = new JTextField();
    protected Border border = BorderFactory.createBevelBorder(1);
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public InputPane(Color b) {
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
        txtField = new JTextField("Left 1");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.WEST; //left of space
        c.insets = new Insets(5,0,5,0);  //top padding
        c.gridx = 0;
        c.gridwidth = 3;   //3 columns wide
        c.gridy = 0;       //third row
        panel.add(txtField, c);

        button = new JButton("LFE 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(button, c);

        button = new JButton("Go 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(button, c);

        button = new JButton("Camera 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 6;
        panel.add(button, c);

        txtField = new JTextField("Right 5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.insets = new Insets(5,0,5,0);  //top padding
        c.gridx = 7;
        c.gridwidth = 3;   //3 columns wide
        c.gridy = 0;       //third row
        panel.add(txtField, c);

        button = new JButton("RFE 6");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 10;
        panel.add(button, c);

        button = new JButton("Go 7");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 11;
        panel.add(button, c);

        /*panel.add(leftPanel);
        panel.add(leftButton);
        panel.add(lGoButton);
        panel.add(cameraButton);
        panel.add(rightPanel);
        panel.add(rightButton);
        panel.add(rGoButton);*/
        panel.setPreferredSize(new Dimension(1200, 60));
        panel.setBackground(b);
        panel.setBorder(border);


    }
}
class OutputPane extends JPanel {
    /* Features - 1.Comparator - Display %
                  2.SpellCheck - Button
    *             3,4.text from URL/file/Picture - JTextField/File Explorer/Button/
                  5,6.Destination Folder - JTextField/Button/File Explorer/
                  7.Auto-highlight - Button on/off
                  8.Take picture from phone - Button*/
    public JPanel panel = new JPanel();
    protected Border border = BorderFactory.createBevelBorder(1);
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public OutputPane(Color b) {
        if (RIGHT_TO_LEFT) {
            panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        JButton button;

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        button = new JButton("Button 1");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(button, c);

        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(button, c);

        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        panel.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(button, c);

        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        panel.add(button, c);


        panel.setPreferredSize(new Dimension(1200, 200));
        panel.setBackground(b);
        panel.setBorder(border);

    }
}

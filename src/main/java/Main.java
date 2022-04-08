import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;

public class Main extends JFrame {
    public static JFrame frame = new JFrame();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            //new TitledPaneSample(); // initializes JavaFX environment
            new MainFrame(frame);
            latch.countDown();
        });
        latch.await();


    }
}

class MainFrame extends JFrame {
    public MainFrame(JFrame frame) {
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(new InputPane(Color.DARK_GRAY).panel, BorderLayout.NORTH);
        frame.add(new TextPane(Color.DARK_GRAY).panel, BorderLayout.WEST);
        frame.add(new TextPane(Color.darkGray).panel, BorderLayout.EAST);
        //frame.add(new jfxGroup().panel, BorderLayout.SOUTH); //Not working
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
        frame.repaint();

    }

}



class SerializeTxt{
    // Serialize the JPanel for saving --------------------------------------------------
    // class SerializePanel{
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

            JOptionPane.showMessageDialog(Main.frame, "Jpanel has been Saved");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Main.frame, "IOException is caught");
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
                JOptionPane.showMessageDialog(Main.frame, "IOException is caught");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(Main.frame, "ClassNotFoundException is caught");
            }
        }
    }
}

class TextPane extends JPanel {
    public JPanel panel = new JPanel();
    public JTextPane textPane = new JTextPane();
    protected Border border = BorderFactory.createBevelBorder(1);

    //Add drag and drop here

    public TextPane(Color c) {
        textPane.setPreferredSize(new Dimension(450, 450));
        panel.setPreferredSize(new Dimension(595, 500));
        panel.setLayout(new BorderLayout());
        panel.add(textPane, BorderLayout.CENTER);
        panel.setBackground(c);
        panel.setBorder(border);

    }
}

class InputPane extends JPanel {
    final static boolean shouldFill = true;
    final static boolean RIGHT_TO_LEFT = false;
    public JPanel panel = new JPanel();
    protected Border border = BorderFactory.createBevelBorder(1);

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
        c.weightx = 0.5;   //request any extra vertical space
        c.anchor = GridBagConstraints.WEST; //left of space
        //c.insets = new Insets(5,0,5,0);  //top padding
        c.gridx = 0;
        c.gridwidth = 3;   //3 columns wide
        c.gridy = 0;       //third row
        panel.add(txtField, c);

        button = new JButton("LFE 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 6;
        c.gridy = 0;
        panel.add(button, c);

        button = new JButton("LGo 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 10;
        c.gridy = 0;
        panel.add(button, c);

        button = new JButton("Camera 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;//make button bigger
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 14;
        c.gridy = 0;
        panel.add(button, c);

        button = new JButton("RGo 5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; //Reset
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 18;
        c.gridy = 0;
        panel.add(button, c);

        button = new JButton("RFE 6");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 22;
        c.gridy = 0;
        panel.add(button, c);

        txtField = new JTextField("Right 7");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;   //request any extra vertical space
        //c.insets = new Insets(5,0,5,0);  //top padding
        c.anchor = GridBagConstraints.EAST; //Right of space
        c.gridx = 26;
        c.gridwidth = 3;   //3 columns wide
        c.gridy = 0;       //third row
        panel.add(txtField, c);

        panel.setPreferredSize(new Dimension(1200, 60));
        panel.setBackground(b);
        panel.setBorder(border);


    }
}

/*
class OutputPane {
    Scene scene;
    StackPane stack;
    JFXPanel panel;

    boolean wait = true;


    public OutputPane() {
        panel = new JFXPanel();

        */
/*gridTitlePane = new TitledPane();
        gridTitlePane2 = new TitledPane();*//*

        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        grid.setVgap(5);
        grid2.setVgap(5);
        grid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        grid.add(new javafx.scene.control.Label("First Name: "), 0, 0);
        grid.add(new javafx.scene.control.TextField(), 1, 0);
        grid.add(new javafx.scene.control.Label("Last Name: "), 0, 1);
        grid.add(new javafx.scene.control.TextField(), 1, 1);
        grid.add(new javafx.scene.control.Label("Email: "), 0, 2);
        grid.add(new javafx.scene.control.TextField(), 1, 2);
        */
/*gridTitlePane.setText("Grid");
        gridTitlePane.setContent(grid);*//*


        grid2.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        grid2.add(new javafx.scene.control.Label("First Name: "), 0, 0);
        grid2.add(new javafx.scene.control.TextField(), 1, 0);
        grid2.add(new javafx.scene.control.Label("Last Name: "), 0, 1);
        grid2.add(new javafx.scene.control.TextField(), 1, 1);
        grid2.add(new javafx.scene.control.Label("Email: "), 0, 2);
        grid2.add(new javafx.scene.control.TextField(), 1, 2);
      */
/*  gridTitlePane2.setText("Grid2");
        gridTitlePane2.setContent(grid2);*//*

        final VBox stackedTitledPanes = new VBox();
        stackedTitledPanes.getChildren().setAll(

                new TitledPane("Pane 1", grid),
                new TitledPane("Pane 2", grid2)
                // new TitledPane("Pane 3",  contentNode3)
        );

        ((TitledPane) stackedTitledPanes.getChildren().get(0)).setExpanded(true);

        stack = new StackPane();
        scene = new Scene(stack, 100, 150);

        panel.setScene(scene);
        stack.getChildren().add(stackedTitledPanes);

    }
}
*/
/*class jfxGroup extends Application {
    JFXPanel panel;
    Scene scene;
    StackPane stack;
    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves"};
    final javafx.scene.image.Image[] images = new javafx.scene.image.Image[imageNames.length];
    final javafx.scene.image.ImageView[] pics = new javafx.scene.image.ImageView[imageNames.length];
    final TitledPane[] tps = new TitledPane[imageNames.length];
    javafx.scene.control.Label label;

    @Override
    public void start(Stage stage) {
        panel = new JFXPanel();
        label = new javafx.scene.control.Label("N/A");
        //stage.setTitle("TitledPane");
        scene = new Scene(new Group(), 800, 250);
        scene.setFill(javafx.scene.paint.Paint.valueOf("Chocolate"));

        // --- GridPane container
        TitledPane gridTitlePane = new TitledPane();
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        grid.add(new javafx.scene.control.Label("To: "), 0, 0);
        grid.add(new javafx.scene.control.TextField(), 1, 0);
        grid.add(new javafx.scene.control.Label("Cc: "), 0, 1);
        grid.add(new javafx.scene.control.TextField(), 1, 1);
        grid.add(new javafx.scene.control.Label("Subject: "), 0, 2);
        grid.add(new javafx.scene.control.TextField(), 1, 2);
        grid.add(new javafx.scene.control.Label("Attachment: "), 0, 3);
        grid.add(label, 1, 3);
        gridTitlePane.setText("Grid");
        gridTitlePane.setContent(grid);

        // --- Accordion
        final Accordion accordion = new Accordion();
        for (int i = 0; i < imageNames.length; i++) {
            images[i] = new
                    javafx.scene.image.Image(getClass().getResourceAsStream(imageNames[i] + ".jpg"));
            pics[i] = new javafx.scene.image.ImageView(images[i]);
            tps[i] = new TitledPane(imageNames[i], pics[i]);
        }
        accordion.getPanes().addAll(tps);
        accordion.expandedPaneProperty().addListener(new
                                                             ChangeListener<TitledPane>() {
                                                                 public void changed(ObservableValue<? extends TitledPane> ov,
                                                                                     TitledPane old_val, TitledPane new_val) {
                                                                     if (new_val != null) {
                                                                         label.setText(accordion.getExpandedPane().getText() +
                                                                                 ".jpg");
                                                                     }
                                                                 }
                                                             });

        HBox hbox = new HBox(10);
        hbox.setPadding(new javafx.geometry.Insets(20, 0, 0, 20));
        hbox.getChildren().setAll(gridTitlePane, accordion);

        javafx.scene.Group root = (Group) scene.getRoot();
        root.getChildren().add(hbox);


       *//* stage.setScene(scene);
        stage.show();*//*
        panel.setScene(scene);
        //stack.getChildren().add(hbox);
    }
}

*/

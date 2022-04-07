import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class main {
    public static LeftPanel leftPanel = new LeftPanel();
    public static RightPanel rightPanel = new RightPanel();
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.add(leftPanel.leftPane);
        frame.add(rightPanel.rightPane);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
        frame.repaint();

    }
}

class LeftPanel extends JPanel {
    JLayeredPane leftPane;
    protected Border border = BorderFactory.createBevelBorder(1);

    public LeftPanel(){
        leftPane = new JLayeredPane();
        leftPane.setBounds(0, 0, 600, 500);
        leftPane.add(getLayeredPaneLabel(), Integer.valueOf(1));
        leftPane.setBorder(border);


    }

    public JLabel getLayeredPaneLabel(){
        JLabel jLabel = new JLabel();
        jLabel.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel.setBounds(0, 0, 600, 500);
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.GREEN);
        return jLabel;
    }

//    public void setLayeredPaneLabel(String text, int multi) {
//        JLabel label = new JLabel();
////        if(text != null) {
////            int x = 5;
////            int y = 5 + (60 * (multi - 1));
////
////            String replacement = "";
////
////            String regex = "[0-9]+\\.+\\s[\\w]+\\s[0-9]+";
////            Pattern p = Pattern.compile(regex);
////            Matcher m = p.matcher(text);
////            while (m.find()) {
////                replacement = m.group();
////            }
////            System.out.println(replacement);
//
//            label.setText(text);
//            label.setHorizontalTextPosition(JLabel.CENTER);
//            label.setVerticalTextPosition(JLabel.CENTER);
//            label.setFont(new Font("Serif", Font.PLAIN, 24));
//            label.setBounds(0, 0, 400, 50);
//            label.setForeground(Color.BLACK);
//            this.leftPane.add(label, Integer.valueOf(1));
//        }

    }

class RightPanel extends JPanel {

    JLayeredPane rightPane;
    protected Border border = BorderFactory.createBevelBorder(1);

    public RightPanel(){
        rightPane = new JLayeredPane();
        rightPane.setBounds(600, 0, 600, 500);
        rightPane.add(getLayeredPaneLabel(), Integer.valueOf(1));
        rightPane.setBorder(border);


    }

    public JLabel getLayeredPaneLabel(){
        JLabel jLabel = new JLabel();
        jLabel.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel.setBounds(600, 0, 600, 500);
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.ORANGE);
        return jLabel;
    }

}

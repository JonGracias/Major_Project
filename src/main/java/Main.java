import GUI.MainFrame;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class Main extends JFrame {
    public static MainFrame frame;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            frame = new MainFrame();
            latch.countDown();
        });
        latch.await();
    }
}



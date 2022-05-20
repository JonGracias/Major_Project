package Listeners;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        //System.out.println("working");
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
}

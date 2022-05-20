package GUI;

import Maps.PositionList;
import Listeners.DocumentListener;
import Listeners.FocusListener;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class Right extends TextPane {
    public static PositionList positionListRight;

    public Right() throws BadLocationException, IOException {
        super();
        positionListRight = new PositionList();
        textPane.setName("Right");
        DocumentListener dr = new DocumentListener(textPane, positionListRight);
        FocusListener fr = new FocusListener();
        textPane.getDocument().addDocumentListener(dr);
        textPane.addFocusListener(fr);
        jScrollPane.getViewport().add(textPane);
        jScrollPane.setRowHeaderView(dr.getLines());
    }
}

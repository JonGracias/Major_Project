package GUI;

import Maps.PositionList;
import Listeners.DocumentListener;
import Listeners.FocusListener;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class Left extends TextPane {
    public static PositionList positionListLeft;

    public Left() throws BadLocationException, IOException {
        super();
        positionListLeft = new PositionList();
        textPane.setName("Left");
        DocumentListener dl = new DocumentListener(textPane, positionListLeft);
        FocusListener fl = new FocusListener();
        textPane.getDocument().addDocumentListener(dl);
        textPane.addFocusListener(fl);
        jScrollPane.getViewport().add(textPane);
        jScrollPane.setRowHeaderView(dl.getLines());
    }


}

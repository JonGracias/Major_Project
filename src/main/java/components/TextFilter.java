package components;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class TextFilter extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            return extension.equals(Utils.txt);
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return ".txt";
    }
}


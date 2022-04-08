import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileIn extends JOptionPane{
    public static StringBuilder read(String filename) throws IOException {
        StringBuilder result = new StringBuilder();
        // Filename for output
        File fileObj = new File(filename);
        if (fileObj.exists()) {
            Scanner fileIn = new Scanner(fileObj);
            if (fileIn.hasNext())
                while (fileIn.hasNext()) {
                    result.append(fileIn.nextLine());
                    result.append("\n");
                }
            fileIn.close();
            return result;
        } else
            showMessageDialog(null, "Error File not found.");
        return result;
    }
}

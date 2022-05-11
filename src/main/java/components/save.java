package components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class save {
    public static void save(String path, String text) {
        //File file = new File(path);
        try {
            // write string to a file
            Files.writeString(Path.of(Path.of(path) + ".txt"), text);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

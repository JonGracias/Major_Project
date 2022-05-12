package components;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class save {
    public save(String path, String text) throws IOException {
        Charset charset = StandardCharsets.US_ASCII;
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path + ".txt"), charset)) {
            writer.write(text, 0, text.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        /*File file = new File(path);
        FileWriter writer = new FileWriter(path);
        writer.write(text);
        writer.flush();
        writer.close();*/
        /*File file = new File(path + ".txt");
        file.createNewFile();
        try {
            // write string to a file
            Files.writeString(Path.of(Path.of(path) + ".txt"), text);

        } catch (IOException ex) {
            ex.printStackTrace();
        }*/

    }
}

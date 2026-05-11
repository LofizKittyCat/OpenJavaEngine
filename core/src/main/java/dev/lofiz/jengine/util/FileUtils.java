package dev.lofiz.jengine.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static String readFile(String file) {

        try {
            byte[] encoded = Files.readAllBytes(Paths.get(file));
            return new String(encoded, Charset.defaultCharset());
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to read file contents: ", e);
        }
    }
}

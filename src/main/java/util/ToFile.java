package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToFile {
    public static void writeToFile(ArrayList<String> writeList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
            for (String writeString : writeList) {
                writer.write(writeString);
                log(writeString);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String log) {
        System.out.println(log);
    }
}

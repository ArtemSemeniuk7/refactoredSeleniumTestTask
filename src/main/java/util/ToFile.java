package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToFile {
    private ArrayList<String> writeList;

    public ToFile(ArrayList<String> writeList) {
        this.writeList = writeList;
    }
    public void writeToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
            for (String writeString : writeList) {
                writer.write(writeString);
                writer.newLine();
            }
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

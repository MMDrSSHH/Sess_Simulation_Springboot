package Sess.Sess.database.bridge;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

abstract public class Bridge {
    abstract public int add(int id1, int id2);

    abstract public ArrayList<Integer> retrieve(@NotNull String key, int id);

    /**
     * <p>
     *     Reads lines in the specified file and returns the lines in an ArrayList
     * </p>
     * @param fileName Name of the file
     * @return An ArrayList of lines from the specified file
     */
    protected @NotNull ArrayList<String> readlines(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try {

            File file = new File("G:\\Sess\\src\\main\\java\\Sess\\Sess\\database\\bridge\\" + fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    /**
     * <p>Gets a line and pareses it into a HashMap String, String</p>
     *
     * @param line String
     * @return HashMap String, String
     */
    protected @NotNull HashMap<String, String> lineParser(@NotNull String line) {
        HashMap<String, String> propertyMap = new HashMap<>();
        for (String property : line.trim().split(",")) {
            String key = property.trim().split(":")[0];
            String value = property.trim().split(":")[1];
            propertyMap.put(key, value);
        }

        return propertyMap;
    }

    /**
     * <p>
     *     Appends line to the end of the specified file
     * </p>
     *
     * @param fileName Name of the file
     * @param line The line to be appended
     *
     * @return An integer for error handling
     */
    protected int appendToFile(String fileName, String line) {
        int status = 0;
        File file = new File("G:\\Sess\\src\\main\\java\\Sess\\Sess\\database\\bridge\\" + fileName);
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer);) {

            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            status = -1;
            throw new RuntimeException(e);
        }

        return status;
    }
}

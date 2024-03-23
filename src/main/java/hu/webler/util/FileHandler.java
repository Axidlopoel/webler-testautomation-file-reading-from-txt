package hu.webler.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String[]> readTxtFile(String filePath, String delimiter) {
        List<String[]> elements = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(delimiter);
                elements.add(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elements;
    }

    public static void printStringArrays(List<String[]> stringArrays) {
        for (String[] elements : stringArrays) {
            for (String element : elements) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private FileHandler() {

    }
}
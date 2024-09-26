package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String fileName = "src/main/java/org/example/profiles.txt";

        ArrayList<String> profiles = readProfilesFromFile(fileName);

        printProfiles(profiles);
    }

    public static ArrayList<String> readProfilesFromFile(String fileName) {
        ArrayList<String> profiles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                profiles.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error :( reading profiles from file: " + fileName);
        }
        return profiles;
    }

    public static void printProfiles(ArrayList<String> profiles) {
        String[] linesArray = profiles.toArray(new String[0]);

        for (String rad : linesArray) {
            System.out.println(rad);
        }
    }
}

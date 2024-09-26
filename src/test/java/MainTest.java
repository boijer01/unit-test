import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void testReadProfilesFromFile() throws IOException {
        Path tempFile = Files.createTempFile("profiles", ".txt");
        Files.write(tempFile, "Simon\nAlexander\nKim\nJoel\nArthur".getBytes());

        ArrayList<String> expectedProfiles = new ArrayList<>();
        expectedProfiles.add("Simon");
        expectedProfiles.add("Alexander");
        expectedProfiles.add("Kim");
        expectedProfiles.add("Joel");
        expectedProfiles.add("Arthur");

        ArrayList<String> actualProfiles = Main.readProfilesFromFile(tempFile.toString());

        assertEquals(expectedProfiles, actualProfiles);

        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testReadProfilesFromFile_InvalidFile() {
        String invalidFileName = "thisshouldnotexist.txt";

        ArrayList<String> profiles = Main.readProfilesFromFile(invalidFileName);

        assertTrue(profiles.isEmpty());
    }

    @Test
    public void testPrintProfiles() {
        ArrayList<String> profiles = new ArrayList<>();
        profiles.add("Simon");
        profiles.add("Alexander");
        profiles.add("Kim");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(outContent));

            Main.printProfiles(profiles);

        } finally {
            System.setOut(originalOut);
        }

        String lineSeparator = System.lineSeparator();
        String expectedOutput = "Simon" + lineSeparator
                + "Alexander" + lineSeparator
                + "Kim" + lineSeparator;
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void testPrintProfiles_EmptyList() {
        ArrayList<String> profiles = new ArrayList<>();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(outContent));

            Main.printProfiles(profiles);

        } finally {
            System.setOut(originalOut);
        }

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReadProfilesFromFile_EmptyFile() throws IOException {
        Path tempFile = Files.createTempFile("empty_profiles", ".txt");

        ArrayList<String> expectedProfiles = new ArrayList<>();

        ArrayList<String> actualProfiles = Main.readProfilesFromFile(tempFile.toString());

        assertEquals(expectedProfiles, actualProfiles);

        Files.deleteIfExists(tempFile);
    }

    //testing the speed
    @Test
    public void testReadProfilesFromFile_Performance() throws IOException {
        Path tempFile = Files.createTempFile("large_profiles", ".txt");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100000; i++) {
            sb.append("Friend").append(i).append("\n");
        }
        Files.write(tempFile, sb.toString().getBytes());

        long startTime = System.currentTimeMillis();

        ArrayList<String> actualProfiles = Main.readProfilesFromFile(tempFile.toString());

        long endTime = System.currentTimeMillis();

        assertEquals(100000, actualProfiles.size());

        long duration = endTime - startTime;

        long maxDuration = 50;

        assertTrue(duration < maxDuration, "tooo slow");

        Files.deleteIfExists(tempFile);
    }
}

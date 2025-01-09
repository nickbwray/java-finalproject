///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           H12 CustomApp testing
// Course:          CS200, Fall, 2023
//
// Author:          Nick

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the test bench class for the H12CustomApp class, specifically
 * designed to test the methods that read from and write to files. It includes
 * methods for creating and reading test data files. the main focus is
 * on verifying the functionality and error handling of H12CustomApp's file handling methods.
 *
 */

public class TestH12CustomApp {

    public static void main(String[] args) {
        testH12CustomApp();
    }

    private static void createTestDataFile(String testDataFilename, String fileContents) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(testDataFilename);
            writer.print(fileContents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
    /**
     * Reads and returns the contents of a specified file. This method is used within test cases
     * to verify the output of file writing operations.
     *
     * @param dataFilename The name of the file to read.
     * @return The contents of the file or an empty string if an error occurs.
     */
    private static String readTestDataFile(String dataFilename) {
        File file = new File(dataFilename);
        Scanner input = null;
        String contents = "";
        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                contents += input.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) input.close();
        }
        return contents;
    }
    /**
     * Runs a series of test cases to validate the functionality of the H12CustomApp's
     * file reading and writing methods. This includes tests for both normal operation and
     * error handling.
     *
     * @return true if all test cases pass, false otherwise.
     */
    public static boolean testH12CustomApp() {
        boolean error = false;

        { // Test reading valid temperature data
            String fileToRead = "testReadTemperature.txt";
            String expectedContents = "23.5\n25.0\n22.8\n";
            createTestDataFile(fileToRead, expectedContents);

            ArrayList<Double> actualContents = H12CustomApp.readTemperatureData(fileToRead);
            ArrayList<Double> expected = new ArrayList<>();
            expected.add(23.5);
            expected.add(25.0);
            expected.add(22.8);

            if (!actualContents.equals(expected)) {
                error = true;
                System.out.println("readTemperatureData 1) expected: " + expected + " actual: " + actualContents);
            } else {
                System.out.println("readTemperatureData 1) success");
                new File(fileToRead).delete();
            }
        }

        { // Test reading from a non-existent file
            String fileToRead = "nonExistentFile.txt";
            try {
                H12CustomApp.readTemperatureData(fileToRead);
                error = true;
                System.out.println("readTemperatureData 2) expected FileNotFoundException");
            } catch (FileNotFoundException e) {
                System.out.println("readTemperatureData 2) success");
            }
        }

        { // Test writing temperature summary with valid data
            String fileNameToWrite = "testWriteTemperature.txt";
            ArrayList<Double> temperatures = new ArrayList<>();
            temperatures.add(23.5);
            temperatures.add(25.0);
            temperatures.add(22.8);

            H12CustomApp.writeTemperatureSummary(fileNameToWrite, temperatures);
            String actualContents = readTestDataFile(fileNameToWrite);
            String expectedContents = "Temperature Summary Report\nAverage Temperature: 23.77 degrees\n";

            if (!actualContents.trim().equals(expectedContents.trim())) {
                error = true;
                System.out.println("writeTemperatureSummary 3) expected: " + expectedContents + " actual: " + actualContents);
            } else {
                System.out.println("writeTemperatureSummary 3) success");
                new File(fileNameToWrite).delete();
            }
        }

        { // Test writing temperature summary with empty data
            String fileNameToWrite = "testWriteEmptyTemperature.txt";
            ArrayList<Double> temperatures = new ArrayList<>();

            boolean result = H12CustomApp.writeTemperatureSummary(fileNameToWrite, temperatures);
            if (result) {
                error = true;
                System.out.println("writeTemperatureSummary 4) expected failure with empty data");
            } else {
                System.out.println("writeTemperatureSummary 4) success");
            }
        }

        if (error) {
            System.out.println("\nTestH12CustomApp failed");
            return false;
        } else {
            System.out.println("\nTestH12CustomApp passed");
            return true;
        }
    }
}

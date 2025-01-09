///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Temp data analyzer
// Course:          CS200, Fall, 2023
//
// Author:          Nicholas Wray
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. This program is designed to process data from a file, then calculate the average temperature and generate
//    a summary report.it addresses the need for analuzing a set of temperature readings, or other contexts like
//    climate studies.It could even fare in far simpler things, such as if you have an interest in
//    logging temperature data for personal use.
// 2. the readTemperatureData method uses an arraylist<double, as its return type to store and return a list of temps
//    read from the file. I chose thsi type for its flexibility in handling a unknown amount of temp readings. the
//    takes a single parameter, String filename , allowing it to read from any file. a fileNotFoundException is includ-
//    -ed in the throws clause to handle times where the specified file DNE, alerting the user or
//    caller of any potential issues.
// 3. the writeTemperatureSummary methods return type is boolean to show the failure/success of the file writing op.
//     the binary feedback allows the calling method to obtain the outcome of the write op. the method accepts two
//     parameters: String filename for specifying the output file, and arraylist<doube> temperatures for the data
//     to be written. this design allows the method to be flexible and reusable with different sets of data and
//     output destinations.
// 4. data validation was by far my biggest challenge. implementing checks to ensure that the data read from the file
//    is accurate(ex, making sure that each line contains a readble double value) was a challenge. invalid or corrupt
//    data lead to runtime errors as well as innacurate calulations, so working that out was crucial.
// 5. i learned how to work through that challenge as mentioned in question 4. i also gained a deeper understanding
//    of how to proces and manipualte data in Java.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class is a temperature data analyzer that reads temp data from a file,
 * calculates the average, and then writes a summary report to another file.
 *
 * @author Nick
 */
public class H12CustomApp {

    /**
     * This method reads temperature data from a file and returns it as a list of Double values.
     * Each line in the file contains one temperature reading.
     * This method will analyze each line of the file, assuming that each line contains a
     * single temperature value that can be converted to a Double. It collects all temperature
     * readings into an ArrayList.
     *
     * @param filename The path and name of the file to read from. The file should exist and be
     *                 accessible in the file system.
     * @return An ArrayList of Double values, each representing a temperature reading from the file.
     *         Returns an empty list if the file is empty. Returns null if the file is not found or
     *         an error occurs during reading.
     * @throws FileNotFoundException If the specified file does not exist or cannot be opened.
     */

    public static ArrayList<Double> readTemperatureData(String filename) {
        File file = new File(filename);
        Scanner input = null;
        ArrayList<Double> temperatures = new ArrayList<>();
        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (!line.isEmpty()) {
                    temperatures.add(Double.parseDouble(line.trim()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("readTemperatureData FileNotFoundException: " + e.getMessage());
            return null;
        } finally {
            if (input != null) input.close();
        }
        return temperatures;
    }

    public static boolean writeTemperatureSummary(String filename, ArrayList<Double> temperatures) {
        if (temperatures == null || temperatures.isEmpty()) {
            return false;
        }

        double sum = 0;
        for (double temp : temperatures) {
            sum += temp;
        }
        double average = sum / temperatures.size();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename);
            writer.println("Temperature Summary Report");
            writer.printf("Average Temperature: %.2f degrees\n", average);
        } catch (FileNotFoundException e) {
            System.out.println("writeTemperatureSummary FileNotFoundException: " + e.getMessage());
            return false;
        } finally {
            if (writer != null) writer.close();
        }
        return true;
    }

    /**
     * This is the main method for the data analyzer. It reads temperature data from a file,
     * calculates the average temperature, and writes a summary report.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String inputFilePath = "temperatures.txt";
        String outputFilePath = "temperature_summary.txt";

        ArrayList<Double> temperatures = readTemperatureData(inputFilePath);
        if (writeTemperatureSummary(outputFilePath, temperatures)) {
            System.out.println("Summary report generated successfully.");
        } else {
            System.out.println("Failed to generate summary report.");
        }
    }
}

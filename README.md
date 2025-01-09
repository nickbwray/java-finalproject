H12 CustomApp and Test Suite
Overview
This repository contains two Java classes:

H12CustomApp

A console application that reads temperature data from a specified file, calculates average temperature, and writes a summary report to an output file.
Demonstrates basic file input/output operations, data validation, and exception handling (e.g., dealing with invalid or missing files).
TestH12CustomApp

A test suite designed to validate the methods in H12CustomApp.
Uses temporary files to simulate various scenarios (e.g., valid data, empty data, nonexistent files) and compares the expected outcomes to actual results.
Reports successes or failures in the console.
Project Structure
bash
Copy code
.
├── H12CustomApp.java
├── TestH12CustomApp.java
├── temperatures.txt          # Example input file (optional)
├── temperature_summary.txt   # Example output file (created at runtime)
└── README.md                 # This file
(File names and exact layout may vary based on your setup.)

Requirements
Java 8+ (or any recent Java version).
A console or IDE that can compile and run Java source code.
Usage
1. Compile
Compile both H12CustomApp.java and TestH12CustomApp.java:

bash
Copy code
javac H12CustomApp.java TestH12CustomApp.java
2. Running H12CustomApp
Prepare an input file with temperature readings (one reading per line).

Run the program:

bash
Copy code
java H12CustomApp
By default, it attempts to read from temperatures.txt and write to temperature_summary.txt.
Check the source code (main method) to confirm these filenames or modify them as needed.
Check output in temperature_summary.txt:

The summary should list a short header and the average temperature.
3. Running TestH12CustomApp
Execute:

bash
Copy code
java TestH12CustomApp
The test suite will:

Create temporary test files with known contents.
Call methods from H12CustomApp (e.g., readTemperatureData, writeTemperatureSummary).
Compare actual results against expected outcomes.
Print pass/fail messages to the console.
4. Cleaning Up
The test suite deletes most temporary files upon successful assertions.
Any remaining files can be manually removed or ignored as needed.
Key Methods & Features
H12CustomApp
readTemperatureData(String filename)

Reads temperature values (one per line) from a file.
Returns an ArrayList<Double> of these readings or null if a FileNotFoundException occurs.
writeTemperatureSummary(String filename, ArrayList<Double> temperatures)

Writes a basic report (title + average temperature) to a given file.
Returns true if successful, false if input data is empty or if writing fails.
main(String[] args)

Example usage: reads from "temperatures.txt", writes to "temperature_summary.txt".
TestH12CustomApp
testH12CustomApp()
Creates test files with various scenarios (valid data, nonexistent file, empty data).
Calls H12CustomApp methods and checks correctness.
Prints pass/fail output and returns a boolean status.
Error Handling
Attempts to handle scenarios where files are missing, incorrectly formatted, or have empty data.
Logs exceptions (e.g., FileNotFoundException) to the console.
Contributing
Feel free to open issues or submit pull requests if you’d like to:

Refine file I/O error handling
Extend the test suite with additional scenarios (e.g., invalid numeric strings)
Enhance output formatting for the summary report

package cmsc256;  // do not remove or comment out this statement

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *  CMSC 256
 *  Project 1
 *  Tran, Hoang
 */
// place any import statements here
public class Project1 {
    public static void main(String[] args) {
        // Test your program thoroughly before submitting.
        // For example,
        // Display appropriately labeled information for the following:
        // What is tallest height?
        // Which row has the lowest weight?
        // Calculate average height of 20-30 year age range in the data.
    }


    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public String checkArgs(String[] argv) {
        if (argv.length == 0) {
            return promptForFileName();
        }
        return argv[0];
    }


    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */

    public String promptForFileName() {
        System.out.println("Enter File Name");
        Scanner input = new Scanner(System.in);
        String result = input.nextLine();
        return result;
    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @return File object
     * @throws FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {
        File inputFileName = new File(fileName);
        if (fileName == null) {
            throw new FileNotFoundException("File doesn't exist");
        }
        return inputFileName;
    }


    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     *
     * @param file       The File object
     * @param numRecords The number of values to read from the input file
     * @return 2D array of data from the File
     * @throws IOException if any lines are missing data
     */

    public String[][] readFile(File file, int numRecords) throws IOException {
        int rows = numRecords;
        //make String array and a String able to hold 500 rows
        String[][] result = new String[rows][];

        //input the file
        Scanner scan = new Scanner(file);

        //loop x amount of times, x being the numRecords
        //scans each row and get the number and stores it
        int i = 0;
        while(i < numRecords){
            String line = scan.nextLine();
            if(Character.isDigit(line.charAt(0))){
            String[] number = line.split(","); //gets a row with 3 boxes

            result[i] = new String[number.length]; //make room for columns according to number's length


            //with enough space, stores each array in every row
            for(int j = 0; j < number.length; j++){
                result[i][j] = number[j];
            }
            i++;
            }
        }


        return result;
    }


    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public int findTallest(String[][] db) {
        int result = 0;
        //loop for number of rows in given 2D array (note:db[i][1] is to get 2 column of each row)
        for(int i = 0; i< db.length; i++){

            //converting to int to do a comparison
            int dbInt = Integer.parseInt(db[i][1]);

            //comparing the 2 heights and updates result each loop
            if(dbInt > result){
                result = dbInt;
            }
        }
        return result;
    }



    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
        int index = 0;
        String[] result = new String[0];
        int lowestWeight = Integer.MAX_VALUE; //get the max value possible for an int
        int tempWeight = 0;
        for(int i = 0; i < db.length; i++){
            tempWeight = Integer.parseInt(db[i][2]);
            if(tempWeight < lowestWeight){
                lowestWeight = tempWeight;
                index = i; //i changes when weight is lower than original
            }
            result = db[index];  //position on row that i is assigned
        }
        return result;
    }

    /**
     * Calculates the average height for all records with the given age range.
     *
     * @param db         2D array of data containing [age] [height] [weight]
     * @param lowerBound youngest age to include in the average
     * @param upperBound oldest age to include in the average
     * @return The average height for the given range or 0 if no
     * records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int upperBound) {
        double result = 0.0;
        double total = 0.0;
        int count = 0;
        for(int i = 0; i<db.length; i++){ //loops 500 times
            int dbInt = Integer.parseInt(db[i][0]);
            if(dbInt >= lowerBound && dbInt <= upperBound){
                total += Integer.parseInt(db[i][1]);
                count++; //record the instances where the number is in range
            }
            result = total/count;
        }
        if(count == 0){ //in case none is in range
            return 0.0;
        }
    return result;
    }




}//end of class



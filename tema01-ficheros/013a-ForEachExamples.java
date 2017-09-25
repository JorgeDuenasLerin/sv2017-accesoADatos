/*
 * Miguel Moya Ortega
 * Examples using for, foreach, and .forEach();
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class ForEachExamples1 {

    public static void main(String[] args) {
        // Creating the array (LinkedList).
        List<String> linesToWrite = new LinkedList<>();
        
        linesToWrite.add("Line 1 to add");
        linesToWrite.add("Line 2 to add");
        linesToWrite.add("Line 3 to add");
        
        System.out.println("Writing...");
        
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(
                    new FileWriter("outputFile.txt", true)));
            
            // Writing with for
            for (int i = 0; i < linesToWrite.size(); i++)
                writer.write(linesToWrite.get(i));
                
            // Writing with foreach
            for (String line : linesToWrite)
                writer.write(line);
            
            // Writing with .forEach()
            linesToWrite.forEach(line -> writer.write(line));
            
            writer.close();
            System.out.println("Done!");
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");
        } catch (IOException e) {
            System.out.println( "Error! Unknown error.");
        }
    }
}

// Miguel Moya

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InvertTxt3 {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    new File("algo.txt")));

            PrintWriter writer = new PrintWriter(new BufferedWriter(
                    new FileWriter(String.format("algo.reverse.txt"), true)));

            System.out.println("Reading...");

            String line = reader.readLine();
            List<String> listToReverse = new LinkedList<>();
           
            while (line != null) {
                listToReverse.add(line);
                line = reader.readLine();
            }
            reader.close();

            System.out.println("Writing...");
            
            Collections.reverse(listToReverse);
            listToReverse.forEach(lines -> writer.println(lines));
            writer.close();
            
            System.out.println("Done! :D");
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found :(");
        } catch (IOException e) {
            System.err.println("Error file :(");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

//import com.opencsv.CSVReader;
//import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 *
 * @author enuj
 */
public class Utils {
//    public static void main(String args[]) {
//        Object[][] d = GetData("10001");
//        System.out.println(d);
//    }
    public static String[] open_text(String empid)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("details.csv"));
            String line = "";

            while((line = br.readLine()) != null) {
                
                String employee[] = line.split(",");
//                System.out.println(employee);
                
//                System.out.println("employee[1]:"+employee[1]);
//                System.out.println("employee[0]:"+employee[0]);
//                System.out.println("empid:"+empid);
                
                if(employee[0].equals(empid)) {
                    System.out.println("Found it!");
                    br.close();

                    return employee;
                }
            }
            
            br.close();
        } catch (IOException e) {
            // TODO: handle exception
            System.out.print("File not found!");
        }

        return null;
    }
    
    public static boolean write_line(){
        try {
            FileWriter pw = new FileWriter("details.csv",true);
            pw.write("10004,Marc Darell,March 15 1996,42975,1500,800,800,21488,255.8");
            pw.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static String[][] GetData() {
        
//10001,Crisostomo Jose,February 14 1988,62670,1500,1000,1000,31335,373.04
//10002,Mata Christian,October 21 1987,42975,1500,800,800,21488,255.8
//10003,San Jose Brad ,March 15 1996,42975,1500,800,800,21488,255.8

//        String csvFilePath = "example.csv";
        
        // Read the CSV file using OpenCSV
        try (CSVReader reader = new CSVReader(new FileReader("employee-details.csv"))) {
            List<String[]> rows = reader.readAll();
            
            // Create a 2D string array to store the data from the CSV file
            String[][] data = new String[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                data[i] = rows.get(i);
            }
            
            
            
            // Print the contents of the 2D string array
//            for (String[] row : data) {
//                for (String cell : row) {
//                    System.out.print(cell + "\t");
//                }
//                System.out.println();
//            }
            
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

//        String data[][] = {
//            {"10001","Crisostomo", "Jose","S123","Phl123","T123","Pag123"},
//            {"10002","Mata", "Christian","S321","Phl321","T321","Pag321"},
//            {"10003","San Jose", "Brad","S321","Phl321","T321","Pag321"}
//        };
        
        return null;
    }
    
    public static List<String[]> readAllLines() throws Exception {
//        try (Reader reader = Files.newBufferedReader(filePath)) {
//            try (CSVReader csvReader = new CSVReader(reader)) {
//                return csvReader.readAll();
//            }
//        }
        try (CSVReader reader = new CSVReaderBuilder(new FileReader("details.csv")).build();) {
            List<String[]> myEntries = reader.readAll();
            return myEntries;
        }
    }
    
    public static CSVReader readAllCSV() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("details.csv")).build();
            return reader;
        } catch(FileNotFoundException e){
            System.out.print(e);
        }
        
//        String [] nextLine;
//        while ((nextLine = reader.readNext()) != null) {
//           // nextLine[] is an array of values from the line
//           System.out.println(nextLine[0] + nextLine[1] + "etc...");
//        }
        return null;
    }
    
}

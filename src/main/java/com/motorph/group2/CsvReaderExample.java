/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enuj
 */
public class CsvReaderExample {
    public static void main(String[] args) {
        String csvFilePath = "example.csv";
        
        // Read the CSV file using OpenCSV
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> rows = reader.readAll();
            
            // Create a 2D array to store the data from the CSV file
            String[][] data = new String[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                data[i] = rows.get(i);
            }
            
            // Create a JTable with the data from the CSV file
            JTable table = new JTable(data, data[0]);
            JScrollPane scrollPane = new JScrollPane(table);
            
            // Create a JFrame to display the JTable
            JFrame frame = new JFrame("CSV Reader Example");
            frame.add(scrollPane);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException ex) {
            Logger.getLogger(CsvReaderExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

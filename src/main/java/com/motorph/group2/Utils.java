/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author enuj
 */
public class Utils {
//    public static void main(String args[]) {
//        Object[][] d = GetData("10001");
//        System.out.println(d);
//    }
    public static String[] open_text(String id)
    {
        try {
            // create br variable to hold csv file data
            // open our csv file with FileReader giving our path with csv_file variable
            BufferedReader br = new BufferedReader(new FileReader("employee-details.txt"));
            String line;

            // create a while loop and assign our csv file to line variable
            // until the end of the file {null}
            while((line = br.readLine()) != null) {
                // create an array of string variable that will hold each line
                // each array element is separated by comma
                // this will result to: employee = {"10001","Juan","Dela Cruz","etc"}
                // to access each element we have to declare: employee[0] for 10001, employee[1] for Juan and etc
                String[] employee = line.split(",");

//                System.out.println(employee[1]);
                
                if(employee[0].equals(id)) {
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
    
    public static Object[][] GetData() {
        //            BufferedReader br = new BufferedReader(new FileReader("employee-details.txt"));
        //            String line;
//10001,Crisostomo Jose,February 14 1988,62670,1500,1000,1000,31335,373.04
//10002,Mata Christian,October 21 1987,42975,1500,800,800,21488,255.8
//10003,San Jose Brad ,March 15 1996,42975,1500,800,800,21488,255.8
        Object[][] data = {
            {"10001","Crisostomo", "Jose","S123","Phl123","T123","Pag123"},
            {"10002","Mata", "Christian","S321","Phl321","T321","Pag321"},
            {"10003","San Jose", "Brad","S321","Phl321","T321","Pag321"}
        };
        //            int counter = 0;
        //            while((line = br.readLine()) != null) {
        //                data[0][counter] = line.split(",");
        //                System.out.println(data[0][counter]);
        //                counter += 1;
        //            }

        return data;
    }
}

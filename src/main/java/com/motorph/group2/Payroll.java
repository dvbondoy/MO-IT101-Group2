/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author enuj
 */
public class Payroll {
    static String emp_number = "";
    static String name = "";
    static String bday = "";
    static double basic_salary = 0;
    static double rice_subsidy = 0;
    static double phone_allowance = 0;
    static double clothing_allowance = 0;
    static double hourly_rate = 0d;
    static double gross = 0;
    
    static Scanner input = new Scanner(System.in);
    
    public static void main( String[] args ) {
        open_text("10001");
    }
    
    public Payroll() {
        
    }
    
    public static String[] open_text(String username)
    {
        try {
            // create br variable to hold csv file data
            // open our csv file with FileReader giving our path with csv_file variable
            BufferedReader br = new BufferedReader(new FileReader("employee-details.txt"));
            String line = "";

            // create a while loop and assign our csv file to line variable
            // until the end of the file {null}
            while((line = br.readLine()) != null) {
                // create an array of string variable that will hold each line
                // each array element is separated by comma
                // this will result to: employee = {"10001","Juan","Dela Cruz","etc"}
                // to access each element we have to declare: employee[0] for 10001, employee[1] for Juan and etc
                String[] employee = line.split(",");

//                System.out.println(employee[1]);
                
                if(employee[0].equals(username)) {
                    br.close();
                    return employee;
                }
            }
            
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.print("File not found!");
        }
        
        return null;

    }
}

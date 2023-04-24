/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

//import java.util.Formatter;

import java.util.Scanner;
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintStream;

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
    static double perks = 0;
    static double sss = 0;
    static double pagibig = 0;
    static double phealth = 0;
    static double withholding = 0;
    static double deductions = 0;
    static double net = 0;


    
    static Scanner input = new Scanner(System.in);
    
    public static void main( String[] args ) {
//        open_text("10001");
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
                    
                    emp_number = employee[0];
                    name = employee[1];
                    bday = employee[2];
                    basic_salary = Double.parseDouble(employee[3]);
                    rice_subsidy = Double.parseDouble(employee[4]);
                    phone_allowance = Double.parseDouble(employee[5]);
                    clothing_allowance = Double.parseDouble(employee[6]);
                    hourly_rate = Double.parseDouble(employee[8]);
                    
                    gross = total_hours() * hourly_rate;
                    sss = compute_sss();
                    pagibig = compute_pagibig();
                    phealth = compute_philhealth();
                    withholding = compute_withholding();

                    perks = (rice_subsidy+phone_allowance+clothing_allowance)/4;
                    deductions = sss+pagibig+phealth+withholding;
                    net = gross - deductions;
                    
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
    
    public static int total_hours()
    {
        // sept 11, 2022 - sept 19, 2022
        int total = 0;
        for(int i = 0; i <6; i++)
        {
            int out = 17;
            int in = 8;
            int breaktime = 1;
            total += (out - in) - breaktime;
        }
               
        return total;
    }
    
    public static double compute_sss()
    {
        // check the beginning and return the rate
        if(basic_salary < 3250){
            return 135;
        }

        // not in the beginning, check the end and return rate
        if(basic_salary >= 24751){
            return 1125;
        }

        double rate = 157.5;
        double rrate = 0;
        // not in the beginning and end, it must be inside
        // iterate every 500 then increase rate by 22.5 for every iteration
        for(double i = 3250; i < 24751; i += 500){
            // System.out.println(String.format("%s - %s = %s",i, i+500, rate));
            // for every iteration check salary range
            if(basic_salary >= i && basic_salary < i+500){
                // we're inside that means we satisfy the salary range
                // save the rateso we can return it, then exit the loop 
                rrate = rate;
                break;
            }
            rate += 22.5;
        }
        // no explanation needed
        return rrate;
    }
    
    public static double compute_pagibig()
    {
        if(basic_salary <= 1500){
            return basic_salary * 0.01;
        } else {
            return basic_salary * 0.02;
        }
    }
    
    public static double compute_philhealth()
    {
        double base = (basic_salary * 0.03) / 2;
        if(base <= 300){
            return 300;
        }else if(base >= 1800){
            return 1800;
        }else{
            return base;
        }
    }
    
    public static double compute_withholding()
    {
        double total_deductions = compute_pagibig()+compute_philhealth()+compute_sss();
        double taxable_income = basic_salary - total_deductions;
        double tax = 0;

        if(taxable_income <= 20832){
            return 0;
        }else if(taxable_income > 20832 && taxable_income < 33333){
            tax =  (taxable_income - 20833) * .02;
        }else if(taxable_income >= 33333 && taxable_income < 66667){
            tax =  (taxable_income - 33333) * .25 + 2500;
        }else if(taxable_income >= 66667 && taxable_income < 166667){
            tax = (taxable_income - 66667) * .3 + 10883;
        }else if(taxable_income >= 166667 && taxable_income < 666667){
            tax = (taxable_income - 166667) * .32 + 40833.33;
        }else{
            tax = (taxable_income - 666667) * .35 + 200833.33;
        }

        return tax / 4;
    }
    
}

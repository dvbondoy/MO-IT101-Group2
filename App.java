package com.mppi;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    // variables for employee details
    static String emp_number = "10001";
    static String name = "Jose Crisostomo";
    static String bday = "February 14, 1988";
    static int basic_salary = 62670;
    // static int basic_salary = 24750;
    static double rice_subsidy = 1500;
    static double phone_allowance = 1000;
    static double clothing_allowance = 1000;
    static double total_perks = (phone_allowance + rice_subsidy + clothing_allowance);
    static double hourly_rate = 373.04d;
    static double gross = 0;
    static Scanner input = new Scanner(System.in);

    public static void main( String[] args )
    {
        if(!login())
        {
            System.out.println("Incorrect credentials!");
            System.exit(0);
        }

        gross = total_hours() * hourly_rate;

        clear_screen();
        main_menu();
    }

    public static boolean login()
    {
        System.out.println(">> Login <<");
        System.out.printf("+------------------+\n");
        System.out.printf("Enter username: ");
        String username = input.nextLine();
        System.out.printf("Enter password:");
        String password = input.nextLine();

        if(username.equals("10001") && password.equals("letmein"))
        {
            return true;
        }

        return false;
    }

    public static void main_menu()
    {
        System.out.println("");
        System.out.printf("+---------------------------------------------+\n");
        System.out.printf("| %-13s > %-12s < %-12s |%n","", "MPPI System","");
        System.out.printf("+---------------------------------------------+\n");
        System.out.println("[ 1 ] : Payroll");
        System.out.println("[ 2 ] : Inventory");
        System.out.println("[ 3 ] : Exit\n");
        
        System.out.printf("Enter choice: ");
        String choice = input.nextLine();

        if(choice.equals("1"))
        {
            clear_screen();
            print_details();
        }
        else if(choice.equals("2"))
        {
            clear_screen();
            System.out.println("\nInventory under construction!");
            main_menu();
        }
        else if(choice.equals("3"))
        {
            clear_screen();
            input.close();
            System.out.println("\nGoodbye!");
            System.exit(0);
        }else{
            clear_screen();
            System.out.println("Invalid entry");
            main_menu();
        }
    }

    public static void print_details()
    {
        double sss = compute_sss();
        double pagibig = compute_pagibig();
        double phealth = compute_philhealth();
        double withholding = compute_withholding();

        double deductions = sss+pagibig+phealth+withholding;
        double net = gross - deductions;

        // Print details
        System.out.println("");
        System.out.printf("+---------------------------------------------+\n");
        System.out.printf("| %-13s > %-12s < %-12s |%n","", "Payroll","");
        System.out.printf("+---------------------------------------------+\n");
        
        System.out.printf("\n");
        System.out.printf(" >> Employee Details%n");
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20s |%n", "Employee No:", emp_number);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20s |%n", "Name:", name);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20s |%n", "Birthday:", bday);
        System.out.printf("-----------------------------------------------%n");

        System.out.printf("\n");
        System.out.printf(" >> Earnings%n");
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20s |%n", "Hours work:", total_hours());
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "Gross income:", gross);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "Total perks:", total_perks/4);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "Net Income:", net);
        System.out.printf("-----------------------------------------------%n");

        System.out.printf("\n");
        System.out.printf(" >> Deductions%n");
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "SSS:", sss);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "Pagibig:", pagibig);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "Philhealth:", phealth);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "Witholding Tax:", withholding);
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("| %-20s | %-20.2f |%n", "Total Deductions:", deductions);
        System.out.printf("-----------------------------------------------%n");

        main_menu();
    }

    public static void clear_screen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    // calculate and return total hours work in a week
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
        // if(basic_salary <= 10000){
        //     if(base <= 300){
        //         return 300;
        //     }else{
        //         return base;
        //     }
        // }else if(basic_salary >=10000.01 && basic_salary <= 59999.99){
        //     return base;
        // }else{
        //     if(base >= 1800){
        //         return 1800;
        //     }else{
        //         return base;
        //     }
        // }
    }

    public static double compute_withholding()
    {
        double total_deductions = compute_pagibig()+compute_philhealth()+compute_philhealth();
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

    public static int sum(int x, int y)
    {
        return x + y;
    }
}
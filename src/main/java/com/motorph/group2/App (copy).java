import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class App
{
    // variables for employee details
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

    public static void main( String[] args )
    {
        int tries = 1;
        while(tries <=3){
            if(!login()){
                clear_screen();
                System.out.println("Invalid credentials. "+tries+" of 3\n");
            }else{
                break;
            }
            tries += 1;
        }

        if(tries > 3){
            clear_screen();
            System.out.println("Maximum login tries reach. Goodbye!\n");
            System.exit(0);
        }

        clear_screen();
        main_menu();
    }

    /**
     * Prompt user to login
     * search empoyee number by calling open_text()
     * if valid login, assign data from open_text() to global variables
     * default password is "letmein"
     * @return true/false
     */
    public static boolean login()
    {
        System.out.println(">> Login <<");
        System.out.println("===========");
        System.out.printf("Username:$ ");
        String username = input.nextLine();
        System.out.printf("Password:$ ");
        String password = input.nextLine();

        // find employee number in the file
        String[] details = open_text(username);

        // check if details is not null and password is correct
        if(details != null && password.equals("letmein")){
            // put all details in the variables
            emp_number = details[0];
            name = details[1];
            bday = details[2];
            basic_salary = Double.parseDouble(details[3]);
            rice_subsidy = Double.parseDouble(details[4]);
            phone_allowance = Double.parseDouble(details[5]);
            clothing_allowance = Double.parseDouble(details[6]);
            hourly_rate = Double.parseDouble(details[8]);

            // login succes so return true
            return true;
        }

        // login failed, return false
        return false;
    }

    /**
     * Used by login() to search for employee number in employee_details.txt
     * @param username
     * @return null/string array
     */
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

    /**
     * Write employee details in out.txt
     * caller: main_menu()
     */
    public static void out_text()
    {
        try {
            // save standard output first to we can go back later
            PrintStream stdout = System.out;
            // create a printstream object and fileoutput stream
            FileOutputStream fout = new FileOutputStream("out.txt");
            PrintStream out = new PrintStream(fout);
            // set output to print stream
            System.setOut(out);

            print_details();

            // close file and print stream
            fout.close();
            out.close();

            // reset output to 
            // console
            System.setOut(stdout);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Application main screen
     * Provide input for user to select action to perform
     */
    public static void main_menu()
    {
        System.out.println("");
        System.out.printf("+---------------------------------------------+\n");
        System.out.printf("| %-13s > %-12s < %-12s |%n","", "MPPI System","");
        System.out.printf("+---------------------------------------------+\n");
        System.out.println("[ 1 ] : Payroll");
        System.out.println("[ 2 ] : Inventory");
        System.out.println("[ 3 ] : Print");
        System.out.println("[ 4 ] : Exit\n");
        
        System.out.printf("Enter choice:$ ");
        String choice = input.nextLine();

        switch(choice){
            case "1":
                clear_screen();
                print_details();
                main_menu();
                break;
            case "2":
                clear_screen();
                System.out.println("\nInventory under construction!");
                main_menu();
                break;
            case "3":
                clear_screen();
                out_text();
                System.out.println("Details saved! (out.txt)");
                main_menu();
                break;
            case "4":
                clear_screen();
                input.close();
                System.out.println("\nGoodbye!");
                System.exit(0);
                break;
            default:
                clear_screen();
                System.out.println("Invalid entry");
                main_menu();
        }
    }

    /**
     * Display details, earnings and deductions of currently logged user
     * caller: main_menu()
     */
    public static void print_details()
    {
        gross = total_hours() * hourly_rate;
        double sss = compute_sss();
        double pagibig = compute_pagibig();
        double phealth = compute_philhealth();
        double withholding = compute_withholding();

        double perks = (rice_subsidy+phone_allowance+clothing_allowance)/4;
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
        System.out.printf("| %-20s | %-20.2f |%n", "Total perks:", perks);
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
    }

    /**
     * clear console
     */
    public static void clear_screen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    /**
     * calculate total hours work in a week
     * @return int total
     */
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

    /**
     * compute SSS deduction
     * @return double rrate
     */
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

    /**
     * computer PAG-IBIG deduction
     * @return double
     */
    public static double compute_pagibig()
    {
        if(basic_salary <= 1500){
            return basic_salary * 0.01;
        } else {
            return basic_salary * 0.02;
        }
    }

    /**
     * compute PHILHEALTH deduction
     * @return double
     */
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

    /**
     * compute withholding tax
     * @return double
     */
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

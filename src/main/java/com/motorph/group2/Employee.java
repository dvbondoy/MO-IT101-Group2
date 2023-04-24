/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;

/**
 *
 * @author enuj
 */
public class Employee {
    
    String emp_number;
    String name;
    String bday;
    Double basic_salary;
    Double rice_subsidy;
    Double phone_allowance;
    Double clothing_allowance;
    Double hourly_rate;
    
    public Employee(String[] emp) {
        this.emp_number = emp[0];
        this.name = emp[1];
        this.bday = emp[2];
        this.basic_salary = Double.parseDouble(emp[3]);
        this.rice_subsidy = Double.parseDouble(emp[4]);
        this.phone_allowance = Double.parseDouble(emp[5]);
        this.clothing_allowance = Double.parseDouble(emp[6]);
        this.hourly_rate = Double.parseDouble(emp[8]);
    }
    
    public Double hoursWOrk(){
        // sept 11, 2022 - sept 19, 2022
        Double total = 0.0;
        for(Double i = 0.0; i <6.0; i++){
            Double out = 17.0;
            Double in = 8.0;
            Double breaktime = 1.0;
            total += (out - in) - breaktime;
        }
        return total;
    }
    
    public Double getGross() {
        return hoursWOrk() * this.hourly_rate;
    }
    
    public Double getSss() {
        // check the beginning and return the rate
        if(basic_salary < 3250){
            return 135.00;
        }

        // not in the beginning, check the end and return rate
        if(basic_salary >= 24751){
            return 1125.00;
        }

        Double rate = 157.5;
        Double rrate = 0.0;
        // not in the beginning and end, it must be inside
        // iterate every 500 then increase rate by 22.5 for every iteration
        for(Double i = 3250.0; i < 24751; i += 500){
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
    
    public Double getPagibig() {
        if(basic_salary <= 1500){
            return basic_salary * 0.01;
        } else {
            return basic_salary * 0.02;
        }
    }
    
    public Double getPhilhealth() {
        Double base = (basic_salary * 0.03) / 2;
        if(base <= 300){
            return 300.0;
        }else if(base >= 1800){
            return 1800.0;
        }else{
            return base;
        }
    }
    
    public Double getWithholding() {
        Double total_deductions = getPagibig() + getPhilhealth() + getSss();
        Double taxable_income = this.basic_salary - total_deductions;
        Double tax = 0.0;

        if(taxable_income <= 20832){
            return 0.0;
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
    
    public Double getTotalDeductions() {
        return getPagibig() + getSss() + getWithholding();
    }
    
    public Double getPerks() {
        Double p = this.clothing_allowance+this.rice_subsidy+this.phone_allowance;
        
        return p;
    }
    
//    public Double getNet() {
//        
//    }
}
    
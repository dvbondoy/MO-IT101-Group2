/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.group2;

/**
 *
 * @author enuj
 */
public class PayrollUtils {
    private String[] employee;
    private double totalHours = 48.0;
    private double basic;
    
    public PayrollUtils(String[] details){
        employee = details;
        
        basic = Double.parseDouble(employee[13].replaceAll(",", ""));
    }
    
    public double computePagibig(){
        if(basic <= 1500){
            return (basic * 0.01)/4;
        } else {
            return (basic * 0.02)/4;
        }
    }
    
    public double computeSss(){
        // check the beginning and return the rate
        if(basic < 3250){
            return 135/4;
        }

        // not in the beginning, check the end and return rate
        if(basic >= 24751){
            return 1125/4;
        }

        double rate = 157.5;
        double charge = 0;
        // not in the beginning and end, it must be inside
        // iterate every 500 then increase rate by 22.5 for every iteration
        for(double i = 3250; i < 24751; i += 500){
            // System.out.println(String.format("%s - %s = %s",i, i+500, rate));
            // for every iteration check salary range
            if(basic >= i && basic < i+500){
                // we're inside that means we satisfy the salary range
                // save the rateso we can return it, then exit the loop 
                charge = rate;
                break;
            }
            rate += 22.5;
        }
        // no explanation needed
        return charge/4;
    }
    
    public double computePhilhealth(){
        double base = (basic * 0.03) / 2;
        if(base <= 300){
            return 300/4;
        }else if(base >= 1800){
            return 1800/4;
        }else{
            return base/4;
        }
    }
    
    public double computeTax(){
        double pagibig = computePagibig();
        double phealth = computePhilhealth();
        double sss = computeSss();
        
        double deductions = (pagibig*4)+(phealth*4)+(sss*4);
        double taxable = basic - deductions;
        double tax = 0;

        if(taxable <= 20832){
            return 0;
        }else if(taxable > 20832 && taxable < 33333){
            tax =  (taxable - 20833) * .02;
        }else if(taxable >= 33333 && taxable < 66667){
            tax =  (taxable - 33333) * .25 + 2500;
        }else if(taxable >= 66667 && taxable < 166667){
            tax = (taxable - 66667) * .3 + 10883;
        }else if(taxable >= 166667 && taxable < 666667){
            tax = (taxable - 166667) * .32 + 40833.33;
        }else{
            tax = (taxable - 666667) * .35 + 200833.33;
        }

        return tax / 4;
    }
    
    public double computePerks(){
        String rice = employee[14].replaceAll(",", "");
        String phone = employee[15].replaceAll(",", "");
        String clothing = employee[16].replaceAll(",", "");
        
        double total = Double.parseDouble(rice) +
                Double.parseDouble(phone) +
                Double.parseDouble(clothing);
        
        return total / 4;
    }
    
    public double computeGross(){
//        String rate = employee[18].replaceAll(",", "");
        return totalHours * Double.parseDouble(employee[18].replaceAll(",", ""));
    }
    
    public double computeTotalDeductions(){
        return computePagibig()+computePhilhealth()+computeSss()+computeTax();
    }
    
    public double computeNet(){
        return computeGross() - computeTotalDeductions();
    }
    
}

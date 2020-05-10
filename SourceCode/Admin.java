package SourceCode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin implements java.io.Serializable{
    private String Password = "1234";
    private List<Employee> Employees = new ArrayList<>();
    public void AddEmployee()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("ENTER EMPLOYEE DETAILS !!");
        System.out.println("ENTER EMPLOYEE NAME !!");
        String name = in.next();
        System.out.println("ENTER EMPLOYEE ID !!");
        String Id  = in.next();
        System.out.println("ENTER EMPLOYEE TYPE OF THE EMPLOYEE \n1(FOR HOURLY PAID)\n 2(FOR MONTHLY PAID)");
        String type = in.next();
        
        if(type.equals("1")){
                System.out.println("ENTER EMPLOYEE HOURLY PAY RATE FOR EMPLOYEE !!");
                Double PayPerHour = 0.0;
                try
                {
                    PayPerHour = in.nextDouble();
                }catch(Exception e){
                    System.out.println("Please enter valid format of double value");
                }
                Employee emp = new HourlyPaidEmployee(name, Id, PayPerHour);
                Employees.add(emp);
            }
        else{
                System.out.println("ENTER EMPLOYEE SALARY OF EMPLOYEE !!");
                Double Salary = 0.0;
                try
                {
                    Salary = in.nextDouble();
                }catch(Exception e){
                    System.out.println("Please enter valid format of double value");
                }
                Employee emp2 = new MonthlyPaidEmployee(name, Id, Salary);
                Employees.add(emp2);
            
        }
        try{ 
            FileOutputStream fos = new FileOutputStream("SourceCode/Admin.ser"); 
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Serialzation Done!!");
         }catch(IOException ioe){
            System.out.println(ioe);
          }
        in.close();

    }
    public static Boolean VerifyPassword(String Password)
    {
        return Password.equals(Password);
    }
    @Override
    public String toString() {
        String data = "";
        for (Employee emp : Employees)
        {
            if(emp instanceof HourlyPaidEmployee)
            {
                HourlyPaidEmployee tmp = (HourlyPaidEmployee)emp;
                data += tmp.toString()+"\n";
            }
            else if (emp instanceof MonthlyPaidEmployee){
                MonthlyPaidEmployee tmp = (MonthlyPaidEmployee)emp;
                data += tmp.toString()+"\n";
            }
        }
        return data;
    }
}
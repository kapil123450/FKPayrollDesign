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
    private List<EmployeeUnion> UnionMembers = new ArrayList<>();
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
        Employee emp = null;
        if(type.equals("1")){
                System.out.println("ENTER EMPLOYEE HOURLY PAY RATE FOR EMPLOYEE !!");
                Double PayPerHour = 0.0;
                try
                {
                    PayPerHour = in.nextDouble();
                }catch(Exception e){
                    System.out.println("Please enter valid format of double value");
                }
                emp = new HourlyPaidEmployee(name, Id, PayPerHour);
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
                emp = new MonthlyPaidEmployee(name, Id, Salary);
                Employees.add(emp);
            
        }
        System.out.println("Want to be part of EmployeeUnion ?Y/N");
        type = in.next();
        if(type.equals("Y")||type.equals("y")){
            System.out.println("Please enter Weekly Dues Rate");
            Double DuesRate = in.nextDouble();
            if (emp != null){
                EmployeeUnion tmp = new EmployeeUnion(emp,DuesRate);
                UnionMembers.add(tmp);
            }
            else {
                System.out.println("Error occured");
            }

        }
        try{ 
            FileOutputStream fos = new FileOutputStream("SourceCode/Admin.ser"); 
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Added succesfully!!");
         }catch(IOException ioe){
            System.out.println(ioe);
          }
        in.close();

    }
    public void DeleteEmployee()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("ENTER EMPLOYEE ID !!");
        String Id  = in.next();
        Employee tmp = null;
        for (Employee emp : Employees)
        {
            if(emp.getId().equals(Id))
            {
                tmp = emp;
                break;
            }
        }
        Employees.remove(tmp);
        EmployeeUnion toRemove = null;
        for(EmployeeUnion tmp1 : UnionMembers)
        {
            if(tmp1.getMemberId().equals(Id))
            {
                toRemove = tmp1;
                break;
            }
        }
        if(toRemove!=null)  
            UnionMembers.remove(toRemove);
        try{ 
            FileOutputStream fos = new FileOutputStream("SourceCode/Admin.ser"); 
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Employee with employee id : "+Id+" deleted succesfully!!");
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
        data += "Following are the emplyeess registered\n";
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
            for (EmployeeUnion tmp : UnionMembers){
                if(emp.getId().equals(tmp.getMemberId()))
                {
                    data += tmp.toString();
                }
            }
        }
        return data;
    }
}
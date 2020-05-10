package SourceCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
    Admin admin=new Admin();
    try{
      FileInputStream fis = new FileInputStream("SourceCode/Admin.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      admin = (Admin)ois.readObject();
      ois.close();
      fis.close();
    }
    catch(IOException ioe)
    {
        System.out.println("Could not find any saved object in the file \nPlease continue !!");
       
    }catch(ClassNotFoundException cnfe)
    {
      System.out.println("Admin Class is not found.|nPlease continue !!");
    }
    
    Scanner in = new Scanner(System.in);
    System.out.println("Press \n1 for Adding a new employee");
    System.out.println("2 for deleting a employee");
    System.out.println("3 for Posting Time Card");
    String type = in.next();
    if (type.equals("1")){
            System.out.println("Enter password for admin");
            String password = in.next();
            if(Admin.VerifyPassword(password))
            {
                admin.AddEmployee();
            }
            else System.out.println("Wrong password");
        }
    else if(type.equals("2")){
        System.out.println("Enter password for admin");
        String password = in.next();
        if(Admin.VerifyPassword(password))
        {
            admin.DeleteEmployee();
        }
        else System.out.println("Wrong password");
    }
    else if(type.equals("3")){
        System.out.println("Enter employee id .");
        String Id = in.next();
        System.out.println("Enter date .");
        String date = in.next();
        System.out.println("Enter time in hours");
        Double time = in.nextDouble();
        admin.PostTimeCard(Id,date,time);
    }
    System.out.println(admin);
    admin.PrintTimeCards();

        
    }
    
}
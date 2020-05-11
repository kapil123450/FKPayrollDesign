package SourceCode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.text.SimpleDateFormat; 

public class Admin implements java.io.Serializable{
    private String Password = "1234";
    private List<Employee> Employees = new ArrayList<>();
    private List<EmployeeUnion> UnionMembers = new ArrayList<>();
    private List<TimeCard> EmployeeTimeCards = new ArrayList<>();
    private List<SalesReciept> EmployeeReciepts = new ArrayList<>();
    Admin(){
        Employees = new ArrayList<>();
        UnionMembers = new ArrayList<>();
        EmployeeTimeCards = new ArrayList<>();
        EmployeeReciepts = new ArrayList<>();
    }
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
                Double CommisionRate = 0.0;
                try
                {
                    Salary = in.nextDouble();
                    System.out.println("Enter Commision rate of employee");
                    CommisionRate = in.nextDouble();
                }catch(Exception e){
                    System.out.println("Please enter valid format of double value");
                }
                emp = new MonthlyPaidEmployee(name, Id, Salary,CommisionRate);
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
            DoSerialize();
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
            DoSerialize();
            System.out.println("Employee with employee id : "+Id+" deleted succesfully!!");
        }catch(Exception IOException){
            System.out.println("Error occured");
        }
        in.close();

    }
    //time in hours
    public void PostTimeCard(String EmployeeId , String date , Double time ){
        TimeCard tmp = null;
        Employee emp = null;
        for(Employee tmp1:Employees)
        {
            if(tmp1.getId().equals(EmployeeId)){
                emp = tmp1;
                break;
            }
        }
        if(emp==null)
        {
            System.out.println("Invalid Id ");
            return ;
        }
        for (TimeCard tmp1:EmployeeTimeCards)
        {
            if(tmp1.getMemberId().equals(emp.getId()))
            {
                tmp = tmp1;
                break;
            }
        }
        if (tmp!=null)
        {
            tmp.PostTimeCard(date , time);
        }
        else {
            tmp = new TimeCard(emp);
            tmp.PostTimeCard(date , time );
            EmployeeTimeCards.add(tmp);
        }
        try{
            DoSerialize();
            System.out.println("Time card posted successfully !!");
        }catch(Exception IOException){
            System.out.println("Error occured while writing object to the file");
        }
    }


    public void PostReceipt(String EmployeeId , String date , Double amount){
        SalesReciept tmp = null;
        Employee emp = null;
        for(Employee tmp1:Employees)
        {
            if(tmp1.getId().equals(EmployeeId)){
                emp = tmp1;
                break;
            }
        }
        if(emp==null)
        {
            System.out.println("Invalid Id ");
            return ;
        }
        for (SalesReciept tmp1:EmployeeReciepts)
        {
            if(tmp1.getMemberId().equals(emp.getId()))
            {
                tmp = tmp1;
                break;
            }
        }
        if (tmp!=null)
        {
            tmp.PostReceipt(date , amount);
        }
        else {
            tmp = new SalesReciept(emp);
            tmp.PostReceipt(date , amount );
            EmployeeReciepts.add(tmp);
        }
        try{
            DoSerialize();
            System.out.println("Sales reciept posted successfully !!");
        }catch(Exception IOException){
            System.out.println("Error occured while writing object to the file");
        }
    }



    public void PostUnionCharges(String EmployeeId , Double MembershipFees , Double FestivalFees){
        Scanner in = new Scanner(System.in);

        Employee emp = null;

        for(Employee tmp1:Employees)
        {
            if(tmp1.getId().equals(EmployeeId)){
                emp = tmp1;
                break;
            }
        }
        if(emp==null)
        {
            System.out.println("Invalid Id");
            return;
        }
        EmployeeUnion member = null;
        for (EmployeeUnion tmp1:UnionMembers)
        {
            if(tmp1.getMemberId().equals(emp.getId()))
            {
                member = tmp1;
                break;
            }
        }
        if (member!=null)
        {
            member.PostUnionCharge(MembershipFees , FestivalFees);
        }
        else {
            System.out.println("Enetr dues rate .");
            Double DuesRate = in.nextDouble();
            member = new EmployeeUnion(emp,DuesRate);
            member.PostUnionCharge(MembershipFees , FestivalFees );
            UnionMembers.add(member);
        }
        try{
            DoSerialize();
            System.out.println("Time card posted successfully !!");
        }catch(Exception IOException){
            System.out.println("Error occured while writing object to the file");
        }
        in.close();
    }






    public void ChangeEmployeeDetails(String EmployeeId)
    {
        Scanner in = new Scanner(System.in);

        Employee emp = null;

        for(Employee tmp1:Employees)
        {
            if(tmp1.getId().equals(EmployeeId)){
                emp = tmp1;
                break;
            }
        }
        if(emp==null)
        {
            System.out.println("Invalid Id");
            return;
        }
        EmployeeUnion member = null;
        for (EmployeeUnion tmp1:UnionMembers)
        {
            if(tmp1.getMemberId().equals(emp.getId()))
            {
                member = tmp1;
                break;
            }
        }
        System.out.println("Insert the new values for the following fields if no change or not applicable insert null In order below");
        if (emp  instanceof MonthlyPaidEmployee)
        {
            System.out.println("1 Salary\n2 New Commision Rate");
            String Salary = in.next();
            String CommisionRate = in.next();
            MonthlyPaidEmployee tmp1 = (MonthlyPaidEmployee)emp;
            tmp1.ChangeEmployeeDetails(CommisionRate,Salary);
            
        }
        else if (emp instanceof HourlyPaidEmployee)
        {
            System.out.println("1 New pay per Hour");
            String NPayperHour = in.next();
            HourlyPaidEmployee tmp2 = (HourlyPaidEmployee)emp;
            tmp2.ChangeEmployeeDetails(NPayperHour);
        }
        if(member !=null){
            System.out.println("\n3 New Due rate\n4 New Membership fees\n5 New festival fees");
            String duerate = in.next();
            String MembershipFees= in.next();
            String FestivalFees = in.next();
            member.ChangeEmployeeDetails(duerate,MembershipFees,FestivalFees);
        }
        try{
            DoSerialize();
        }catch(Exception e){
            System.out.println(e);
        }
        in.close();
    }







    public void RunPayRoll(String TillDate)
    {
        Calendar cal = Calendar.getInstance();
        String RecordPayments = "";
        boolean friday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
        boolean lastDayOfMonth = cal.get(Calendar.DATE)==cal.getActualMaximum(Calendar.DATE);
        if (friday)
        {
            for (TimeCard card : EmployeeTimeCards){
            try{
                RecordPayments += card.MakePayment(TillDate,UnionMembers)+"\n";
            }catch(Exception ParseException){
                System.out.println("exceptionparsing");
            }
            for(SalesReciept receipt: EmployeeReciepts)
            try{
                RecordPayments += receipt.MakePayment(TillDate,UnionMembers)+"\n";
            }catch(Exception ParseException){
                System.out.println("exceptionparsing");
            }
        }
        if(lastDayOfMonth)
        {
            for(Employee emp : Employees)
            {
                if (emp instanceof MonthlyPaidEmployee)
                {
                    MonthlyPaidEmployee tmp = (MonthlyPaidEmployee)emp;
                    RecordPayments += tmp.MakePayment(UnionMembers);
                }
            }
        }
        }
        try{
            DoSerialize();
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println(RecordPayments);
    }






    public void DoSerialize() throws IOException{
        
            FileOutputStream fos = new FileOutputStream("SourceCode/Admin.ser"); 
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
    }
    public static Boolean VerifyPassword(String Password)
    {
        return Password.equals(Password);
    }



    public void PrintTimeCards(){
        TimeCard tmp = null;
        for(TimeCard tmp1:EmployeeTimeCards)
        {
            System.out.println(tmp1);
        }
    }


    public void PrintReciepts(){
        
        for(SalesReciept tmp1:EmployeeReciepts)
        {
            System.out.println(tmp1);
        }
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
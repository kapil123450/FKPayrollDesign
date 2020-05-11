package SourceCode;

import java.util.*;
import java.text.SimpleDateFormat; 


class Employee implements java.io.Serializable{
    final private String employeeId;
    final private String NameOfEmployee;
    

    final public String getId(){
        return employeeId;
    }
    final public String getName(){
        return NameOfEmployee;
    }
    public String getLastPaid(){
        return "ok";
    }
    public void ChangeEmployeeDetails(){
        System.out.println("Nothing to change");
    }
    public Employee(final String Id, final String NameOfEmployee) {
        this.employeeId = Id;
        this.NameOfEmployee = NameOfEmployee;
    }
}

class HourlyPaidEmployee extends Employee {
    private Double PayPerHour ;
    private Double BonusPay = 1.5;
    private Double defaultWorkingHours = 8.0;
    private String LastPaidDate ;
    private Double BankBalance ;

    HourlyPaidEmployee(String name , String Id, Double PayPerHour){
        super(Id ,name );
        this.PayPerHour = PayPerHour;
        BankBalance = 0.0;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        LastPaidDate = format.format(date);
    }


    public Double generatePay(Double time)
    {
        if(time>defaultWorkingHours)
        {
            return PayPerHour*defaultWorkingHours+PayPerHour*BonusPay*(time-defaultWorkingHours);
        }
        else return PayPerHour*defaultWorkingHours;
    }

    public void ChangeEmployeeDetails(String NewPaypHour ){
        if(!NewPaypHour.equals("null"))
            this.PayPerHour = Double.parseDouble(NewPaypHour);
        
    }
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("");
        data.append("Employee Id is : "+this.getId()+"\n");
        data.append("Employee Name is : "+this.getName()+"\n");
        data.append("Employee Hourly Pay rate is : "+this.PayPerHour+"\n");

        return data.toString();
    }

    @Override
    public String getLastPaid(){
        return LastPaidDate;
    }

    public void setLastPaidDate(String date){
        System.out.println("set lastdate :"+date);
        this.LastPaidDate = date;
    }
}

class MonthlyPaidEmployee extends Employee {
    private Double Salary;
    private String LastPaidDate ;
    private Double CommisionRate ;
    private Double BankBalance;
    public MonthlyPaidEmployee(final String name ,final String Id ,final Double Salary , final Double CommisionRate){
        super(Id, name);
        this.Salary = Salary;
        this.CommisionRate = CommisionRate;
        BankBalance =0.0;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        LastPaidDate = format.format(date);
    }

    public Double getCommisionRate(){
        return CommisionRate;
    }
    
    public void ChangeEmployeeDetails(String NewCommisionRate , String NewSalary ){
        if(!NewCommisionRate.equals("null"))
            this.CommisionRate = Double.parseDouble(NewCommisionRate);
        if(!NewSalary.equals("null"))
            this.Salary = Double.parseDouble(NewSalary);
    }
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("");
        data.append("Employee Id is : "+this.getId()+"\n");
        data.append("Employee Name is : "+this.getName()+"\n");
        data.append("Employee Salary is : "+this.Salary+"\n");

        return data.toString();
    }

    @Override
    public String getLastPaid(){
        System.out.println(LastPaidDate);
        return LastPaidDate;
    }

    public void setLastPaidDate(String date){
        this.LastPaidDate = date;
    }
    public Double generatePay(Double amount){
        return CommisionRate*amount;
    }

    public String MakePayment(List<EmployeeUnion> UnionMembers){
        StringBuilder data = new StringBuilder("");
        data.append("Paying Salary To employee Name  : "+this.getName()+" employee Id : "+this.getId());
        Double payment = 0.0;
        payment = Salary;
        data.append("Paid amount : "+payment);

        EmployeeUnion member = null;
        for (EmployeeUnion tmp1:UnionMembers)
        {
            if(tmp1.getMemberId().equals(this.getId()))
            {
                member = tmp1;
                break;
            }
        }
        if (member!=null)
        {
            payment -= member.deduct();
            data.append("Deducted union charges : "+member.deduct());
            member.reset();
        }
        return data.toString();
    

    }
}







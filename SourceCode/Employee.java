package SourceCode;

import java.sql.Date;


class Employee implements java.io.Serializable{
    final private String employeeId;
    final private String NameOfEmployee;
    

    final public String getId(){
        return employeeId;
    }
    final public String getName(){
        return NameOfEmployee;
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
    private Date LastPaidDate ;

    HourlyPaidEmployee(String name , String Id, Double PayPerHour){
        super(Id ,name );
        this.PayPerHour = PayPerHour;
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
}

class MonthlyPaidEmployee extends Employee {
    private Double Salary;
    private String LastPaidDate ;
    private Double CommisionRate ;
    public MonthlyPaidEmployee(final String name ,final String Id ,final Double Salary , final Double CommisionRate){
        super(Id, name);
        this.Salary = Salary;
        this.CommisionRate = CommisionRate;
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
}







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
    public Employee(final String Id, final String NameOfEmployee) {
        this.employeeId = Id;
        this.NameOfEmployee = NameOfEmployee;
    }
}
interface ExtraWorkingHour{
    public Double GetExtraHour(Double defaultWorkingHours , Double WorkedHour);
    public Double GetBonusPay(Double defaultWorkingHours , Double WorkedHour , Double BonusPayRate);
}

interface SalesCommision{
    public Double GetCommision(Double Sales , Double CommisionRate );
}

class HourlyPaidEmployee extends Employee implements ExtraWorkingHour{
    private Double PayPerHour ;
    private Double BonusPay = 1.5;
    private Double defaultWorkingHours = 8.0;
    private Date LastPaidDate ;

    HourlyPaidEmployee(String name , String Id, Double PayPerHour){
        super(Id ,name );
        this.PayPerHour = PayPerHour;
    }

    public Double GetExtraHour(Double defaultWorkingHours , Double WorkedHour){
        if (defaultWorkingHours<WorkedHour)
            return WorkedHour - defaultWorkingHours;
        else return 0.0;
    }

    public Double GetBonusPay(Double defaultWorkingHours , Double WorkedHour , Double BonusPayRate){
        if (defaultWorkingHours<WorkedHour)
        {
            Double amount ;
            amount =  (WorkedHour - defaultWorkingHours)*BonusPayRate;
            return amount;
        }
        else return 0.0;
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

class MonthlyPaidEmployee extends Employee implements SalesCommision{
    private Double Salary;
    private Date LastPaidDate ;
    public MonthlyPaidEmployee(final String name ,final String Id ,final Double Salary){
        super(Id, name);
        this.Salary = Salary;
    }

    public Double GetCommision(Double sales , Double CommisionRate){
        Double amount = sales*CommisionRate;
        return amount;
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







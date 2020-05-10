package SourceCode;


public class EmployeeUnion implements java.io.Serializable{
    private Employee employee;
    private Double DuesRate ;
    private Double OtherDues;
    private Double MembershipFees;
    private Double FestivalFees;

    public void setMembershipFees(Double MembershipFees){
        this.MembershipFees = MembershipFees;
    }
    EmployeeUnion(Employee employee , Double DuesRate){
        this.employee = employee;
        this.DuesRate = DuesRate;
    }

    public String getMemberId(){
        return this.employee.getId();
    }
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("");
        data.append("Employee DueRate is : "+this.DuesRate+"\n");
        return data.toString();
    }
}
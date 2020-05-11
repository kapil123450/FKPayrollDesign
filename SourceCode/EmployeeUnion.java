package SourceCode;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


public class EmployeeUnion implements java.io.Serializable{
    private Employee employee;
    private Double DuesRate ;
    private Double MembershipFees;
    private Double FestivalFees;
    
    public void setMembershipFees(Double MembershipFees){
        this.MembershipFees = MembershipFees;
    }


    EmployeeUnion(Employee employee , Double DuesRate ){
        this.employee = employee;
        this.DuesRate = DuesRate;
        this.MembershipFees = 0.0;
        this.FestivalFees = 0.0;
        
    }


    public void ChangeEmployeeDetails(String NDuesRate ,String NMembershipFees , String NFestivalFees){
        if(!NDuesRate.equals("null"))
            this.DuesRate = Double.parseDouble(NDuesRate);
        if(!NMembershipFees.equals("null"))
            this.MembershipFees = Double.parseDouble(NMembershipFees);
        if(!NFestivalFees.equals("null"))
            this.FestivalFees =  Double.parseDouble(NFestivalFees);
    }



    public void PostUnionCharge(Double MembershipFees ,Double FestivalFees){
        this.MembershipFees = MembershipFees;
        this.FestivalFees = FestivalFees;
    }



    public String getMemberId(){
        return this.employee.getId();
    }


    public double deduct(){
        Double Dues = MembershipFees+FestivalFees;
        Dues = Dues*DuesRate;
        return Dues;
    }

    public void reset(){
        MembershipFees =0.0;
        FestivalFees = 0.0;
    }


    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("");
        data.append("Employee DueRate is : "+this.DuesRate+"\n");
        data.append("Employee MembershipFees is : "+this.MembershipFees+"\n");
        data.append("Employee FestivalFees is : "+this.FestivalFees+"\n");
        return data.toString();
    }
}
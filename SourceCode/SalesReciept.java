package SourceCode;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


public class SalesReciept implements java.io.Serializable{
    private Employee employee;
    private Map<String , Double> Receipts = new HashMap<String , Double>();
    SalesReciept(Employee emp){
        this.employee = emp;
    }
    public void PostReceipt(String date , Double amount){
        Receipts.put(date , amount);
    }
    public String getMemberId(){
        return employee.getId();
    }
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("");
        data.append("Employee : "+employee+"\n");
        for(Map.Entry< String,Double> card : Receipts.entrySet())
        {
            data.append("Date : "+card.getKey()+" Amount sold "+card.getValue()+"\n");
        }
        return data.toString();
    }    
}
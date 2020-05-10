package SourceCode;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


public class TimeCard implements java.io.Serializable{
    private Employee employee;
    private Map<String , Double> Cards = new HashMap<String , Double>();
    TimeCard(Employee emp){
        this.employee = emp;
    }
    public void PostTimeCard(String date , Double time){
        Cards.put(date , time);
    }
    public String getMemberId(){
        return employee.getId();
    }
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder("");
        data.append("Employee : "+employee+"\n");
        for(Map.Entry< String,Double> card : Cards.entrySet())
        {
            data.append("Date : "+card.getKey()+" Hour Worked "+card.getValue()+"\n");
        }
        return data.toString();
    }
}
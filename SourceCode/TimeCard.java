package SourceCode;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.text.SimpleDateFormat; 
import java.text.ParseException;  

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


    public String MakePayment(String TillDate , List<EmployeeUnion> UnionMembers) throws ParseException
    {
        StringBuilder data = new StringBuilder("");
        HourlyPaidEmployee emp = (HourlyPaidEmployee)employee;
        String LastPaidDate = employee.getLastPaid();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        c.setTime(format.parse(LastPaidDate));
        Date date1 = format.parse(LastPaidDate);
        Date date2 = format.parse(TillDate);
        System.out.println(date1);
        System.out.println(date2);
        Double payment = 0.0;
        data.append("Paying Hours rate To employee Name  : "+emp.getName()+" employee Id : "+emp.getId());
        while (date1.compareTo(date2)<=0)
        {
            String strDate = format.format(date1);
            Double time = Cards.get(strDate);
            if(time!=null){
                System.out.println(strDate);
                System.out.println(time);
                

                payment += emp.generatePay(time);
                //Cards.remove(strDate);
                data.append("Paid on date : "+strDate+" Amount : "+emp.generatePay(time));
            }
            c.add(Calendar.DATE,1);
            date1 = c.getTime();
        }
        data.append("Total Paid Amount : "+payment);

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
            payment -= member.deduct();
            data.append("Deducted union charges : "+member.deduct());
            member.reset();
        }

        data.append("Total Paid Amount : "+payment);
        emp.setLastPaidDate(TillDate);
        return data.toString();

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
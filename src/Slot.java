
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAHIL
 */
public class Slot {
    private ArrayList<String> div;
    private ArrayList<ArrayList<Integer>> subject;
    private ArrayList<HashMap<Integer,Integer>> allocationSubjectTeacher;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    HashMap<Integer,HashMap<String,Integer>> timeSlot;
    ArrayList<ArrayList<Integer>> noOfLecOnThatDayForThatClass;
    private Random generator = new Random();
    ArrayList<ArrayList<Integer>> labHourInsert= new ArrayList<ArrayList<Integer>>(); 
    
    
    
    
    public Slot(ArrayList<String> div, ArrayList<ArrayList<Integer>> subject, ArrayList<HashMap<Integer,Integer>> allocationSubjectTeacher){
        this.div = div;
        this.subject = subject;
        this.allocationSubjectTeacher = allocationSubjectTeacher;
        this.timeSlot = new HashMap<Integer,HashMap<String,Integer>>();
        this.noOfLecOnThatDayForThatClass = new ArrayList<ArrayList<Integer>>();
        this.conn = MySQLConnect.connectDB();
    }
    
    public void createSlot(){
        String sql = "SELECT * FROM teacher";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
//                System.out.println(rs.getString("teacher_name"));
                HashMap<String,Integer> teacherSlot = new HashMap<String,Integer>();
                if((rs.getInt("teacher_shift"))==1){
                    teacherSlot.put("8:10",0);
                    teacherSlot.put("9:10",0);
                    teacherSlot.put("10:25",0);
                    teacherSlot.put("11:25",0);
                    teacherSlot.put("12:45",0);
                    teacherSlot.put("1:45",0);
                    
                    timeSlot.put(rs.getInt("teacher_id"),teacherSlot);
                }
                
                if((rs.getInt("teacher_shift"))==2){
                    teacherSlot.put("10:25",0);
                    teacherSlot.put("11:25",0);
                    teacherSlot.put("12:45",0);
                    teacherSlot.put("1:45",0);
                    teacherSlot.put("3:00",0);
                    teacherSlot.put("4:00",0);
                    
                    timeSlot.put(rs.getInt("teacher_id"),teacherSlot);
                }
                
                if((rs.getInt("teacher_shift"))==3){
                    teacherSlot.put("11:25",0);
                    teacherSlot.put("12:45",0);
                    teacherSlot.put("1:45",0);
                    teacherSlot.put("3:00",0);
                    teacherSlot.put("4:00",0);
                    teacherSlot.put("5:00",0);
                    
                    timeSlot.put(rs.getInt("teacher_id"),teacherSlot);
                }
                
                
            }
            
            
            for(Integer key: timeSlot.keySet()){
//    		System.out.println(key + ": " + timeSlot.get(key));
                
                
    	}
            System.out.println("");
//            System.out.println(timeSlot.get(5));
//            System.out.println(timeSlot.get(5).get("8:10"));
//            
//            System.out.println(timeSlot.get(5).get("5:00"));
            noOfLecturesPerDay();
            
        } catch (SQLException ex) {
            Logger.getLogger(Slot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void noOfLecturesPerDay(){
        for(int i=0;i<subject.size();i++)
        {
            int totalHours=0;
            for(int j=0;j<subject.get(i).size();j++)
            {
                int subjId = subject.get(i).get(j);
//                System.out.println(subjId);
                String sql = "Select * from subject where subject_id="+subjId;
//                System.out.println(sql);
                
                try {
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery();
                    while(rs.next()){
                    int lecHours=rs.getInt("hours_per_week");
//                    System.out.println(lecHours);
                    int labHours = rs.getInt("lab_hours_per_week");
                    totalHours=totalHours+lecHours+labHours;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Slot.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
            System.out.println("total :"+totalHours);
            
            int fullWeekHours = 0;
            int[] arr = {4,5,6};
            while(fullWeekHours!=totalHours){
                fullWeekHours = 0;
                
                    int randomIndex = generator.nextInt(arr.length);
                    int hours_per_day1 = arr[randomIndex];
                    
                    randomIndex = generator.nextInt(arr.length);
                    int hours_per_day2 = arr[randomIndex];
                    
                    randomIndex = generator.nextInt(arr.length);
                    int hours_per_day3 = arr[randomIndex];
                    
                    randomIndex = generator.nextInt(arr.length);
                    int hours_per_day4 = arr[randomIndex];
                    
                    randomIndex = generator.nextInt(arr.length);
                    int hours_per_day5 = arr[randomIndex];
                    
                    randomIndex = generator.nextInt(arr.length);
                    int hours_per_day6 = arr[randomIndex];
                    
                    fullWeekHours+=hours_per_day1+hours_per_day2+hours_per_day3+hours_per_day4+hours_per_day5+hours_per_day6;
                    if(fullWeekHours == totalHours)
                    {
                        ArrayList<Integer> lecCount = new ArrayList<Integer>();
                        System.out.println("perfect");
                        lecCount.add(hours_per_day1);
                        lecCount.add(hours_per_day2);
                        lecCount.add(hours_per_day3);
                        lecCount.add(hours_per_day4);
                        lecCount.add(hours_per_day5);
                        lecCount.add(hours_per_day6);
                        noOfLecOnThatDayForThatClass.add(lecCount);
                        
                        break;
                    }
                
            
               
            }
            
             System.out.println("full week hours "+fullWeekHours);
        }
        for(int p=0;p<noOfLecOnThatDayForThatClass.size();p++){
            for(int q=0;q<noOfLecOnThatDayForThatClass.get(p).size();q++){
                System.out.print(noOfLecOnThatDayForThatClass.get(p).get(q)+", ");
            }
            System.out.println("");
        }
        labHours();
    }
    
    
    public void labHours(){
        for(int i=0;i<subject.size();i++)
        {
            int totalLabHours=0;
            ArrayList<Integer> classWiseLabDay = new ArrayList<Integer>();
            for(int j=0;j<subject.get(i).size();j++)
            {
                int subjId = subject.get(i).get(j);
//                System.out.println(subjId);
                String sql = "Select * from subject where subject_id="+subjId;
//                System.out.println(sql);
                
                try {
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery();
                    int labHours = 0;
                    while(rs.next()){
                        labHours = rs.getInt("lab_hours_per_week");
                        totalLabHours=totalLabHours+labHours;
                    }
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Slot.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }
                    int countMon=0;
                    int countTues=0;
                    int countWed=0;
                    int countThurs=0;
                    int countFri=0;
                    int countSat=0;
                    int[] myArr = {1,2,3,4,5,6};
//            System.out.println("SUNDAY");
                        int count=0;
                        while(count<(totalLabHours/2)){
                            int randomIndex = generator.nextInt(myArr.length);
                            int dayValue = myArr[randomIndex];
                            if(dayValue == 1 ){
                                if(countMon<2){
                                    countMon++;
                                    count++;
                                }
                            }
                            if(dayValue == 2){
                                if(countTues<2){
                                    countTues++;
                                    count++;
                                }
                            }
                            if(dayValue == 3){
                                if(countWed<2){
                                    countWed++;
                                    count++;
                                }
                                
                            }
                            if(dayValue == 4){
                                if(countThurs<2){
                                    countThurs++;
                                    count++;
                                }
                                
                            }
                            if(dayValue == 5){
                                if(countFri<2){
                                    countFri++;
                                    count++;
                                }
                                
                            }
                            if(dayValue == 6){
                                if(countSat<2){
                                    countSat++;
                                    count++;
                                }
                                
                            }
                            
                        }
//                        System.out.println("mon "+countMon);
//                            System.out.println("tues "+countTues);
//                            System.out.println("wed "+countWed);
//                            System.out.println("thurs "+countThurs);
//                            System.out.println("fri "+countFri);
//                            System.out.println("sat "+countSat);

                        
                        
                        
                        
                        classWiseLabDay.add(countMon);
                        classWiseLabDay.add(countTues);
                        classWiseLabDay.add(countWed);
                        classWiseLabDay.add(countThurs);
                        classWiseLabDay.add(countFri);
                        classWiseLabDay.add(countSat);
                        
                        labHourInsert.add(classWiseLabDay);
                        
//                        for(int g = 0;g<labHourInsert.size();g++){
//                            for(int h=0;h<labHourInsert.get(g).size();h++){
//                                System.out.println(h+" : "+labHourInsert.get(g).get(h));
//                            }
//                        }
                        
                        
                        
        }
        for(int g = 0;g<labHourInsert.size();g++){
                            for(int h=0;h<labHourInsert.get(g).size();h++){
                                System.out.println(h+" : "+labHourInsert.get(g).get(h));
                            }
                            System.out.println("SUNDAY");
                        }
        
        
        new WeekTimeTable(div,subject,allocationSubjectTeacher,timeSlot,noOfLecOnThatDayForThatClass,labHourInsert); 
       
        
        
        
    }
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author SAHIL
 */
public class WeekTimeTable {
    private ArrayList<String> div;
    private ArrayList<ArrayList<Integer>> subject;
    private ArrayList<HashMap<Integer,Integer>> allocationSubjectTeacher;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    HashMap<Integer,HashMap<String,Integer>> timeSlot;
    ArrayList<ArrayList<Integer>> noOfLecOnThatDayForThatClass;
    private Random generator = new Random();
    ArrayList<ArrayList<Integer>> labHourInsert;
    ArrayList<HashMap<String,Integer>> classSlot;
    ArrayList<Integer> lecSub;
    int todaysLecCountRemainig;
    int onlyTodaysLabCount;
//    int todaysTotalCollegeDayCount;
    
    WeekTimeTable(ArrayList<String> div, ArrayList<ArrayList<Integer>> subject, ArrayList<HashMap<Integer,Integer>> allocationSubjectTeacher, HashMap<Integer,HashMap<String,Integer>> timeSlot, ArrayList<ArrayList<Integer>> noOfLecOnThatDayForThatClass, ArrayList<ArrayList<Integer>> labHourInsert){
        this.div = div;
        this.subject = subject;
        this.allocationSubjectTeacher = allocationSubjectTeacher;
        this.timeSlot = timeSlot;
        this.noOfLecOnThatDayForThatClass = noOfLecOnThatDayForThatClass;
        this.labHourInsert = labHourInsert;
        this.classSlot = new ArrayList<HashMap<String,Integer>>();
        this.conn = MySQLConnect.connectDB();
        this.lecSub = new ArrayList<Integer>();
        todaysLecCountRemainig = 0;
        onlyTodaysLabCount = 0;
        
//        todaysTotalCollegeDayCount = 0;
//        System.out.println("hey");
        necessaryDetails();
    }
    
    private void necessaryDetails(){
//        System.out.println("in");
        String sql = "";
        int divShift = 0;
        for(int i=9;i<37;i++){  //divisions
//            int i=17;
           sql = "SELECT * FROM division WHERE division_name = '"+div.get(i)+"'";
            System.out.println(sql);
               System.out.println("----------------"+div.get(i)+"----------STARTS HERE----------------");
           try{
               ps = conn.prepareStatement(sql);
               rs = ps.executeQuery();
               while(rs.next()){
                   divShift = rs.getInt("shift");
                   System.out.println(divShift);
                    
               }
               
               
               int len = allocationSubjectTeacher.get(i).size();
               int[] subjects = new int[len];
               int j=0;
            for (Integer key : allocationSubjectTeacher.get(i).keySet() ) {
                System.out.println(key);
                subjects[j] = key;
                j++;
            }
            
               
               try{
               for(int val : subjects){
                        sql="Select hours_per_week from subject where subject_id="+val;
                        System.out.println(sql);
                        ps=conn.prepareStatement(sql);
                        rs=ps.executeQuery();
                        int originalHrs = 0;
                        while(rs.next()){
                           originalHrs =rs.getInt("hours_per_week");
                        }
                        
                        
                        if(originalHrs>0){
                            sql="Select lecture_hour from timetable where subject_id="+val+" and division_name='"+div.get(i)+"'";
                            System.out.println(sql);
                            ps=conn.prepareStatement(sql);
                            rs=ps.executeQuery();
                            int allocateHrs = 0;
                            while(rs.next()){
                                allocateHrs += rs.getInt("lecture_hour");
                            }
                            if(allocateHrs<originalHrs){
                                lecSub.add(val);
                                System.out.println(val);
                            }
                        
                        }
                      }
               }catch(SQLException e){}
               
               
               
               
               
               gettingClassLec(i,divShift,subjects,lecSub);
               
               System.out.println("-----------------"+div.get(i)+"----------ENDS HERE----------------");
               lecSub.clear();
               
           }catch(SQLException e){
               
           }catch(Exception e){
               
           }
        }
      
        
    }
    
    
     void gettingClassLec(int i, int divShift,int[] subjects, ArrayList<Integer> lecSub){
        
        
         if(divShift == 1){
             todaysLecCountRemainig = noOfLecOnThatDayForThatClass.get(i).get(0);
             onlyTodaysLabCount = 0;
             
             String result1a = slot1(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
           
            if(result1a.equalsIgnoreCase("lec")){
                String result1b = slot2(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                String result1c = slot3(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                if(result1c.equalsIgnoreCase("lec")){
                    String result1d = slot4(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    String result1e = slot5(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result1e.equalsIgnoreCase("lec")){
                        String result1f = slot6(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }else if(result1e.equalsIgnoreCase("lab")){
                        
                    }
                }else if(result1c.equalsIgnoreCase("lab")){
                    String result1e = slot5(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                }
                
            }else if(result1a.equalsIgnoreCase("lab")){
                String result1c = slot3(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                if(result1c.equalsIgnoreCase("lec")){
                    String result1d = slot4(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    String result1e = slot5(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result1e.equalsIgnoreCase("lec")){
                        String result1f = slot6(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }else if(result1e.equalsIgnoreCase("lab")){
                        
                    }
                    
                }else if(result1c.equalsIgnoreCase("lab")){
                    String result1e = slot5(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result1e.equalsIgnoreCase("lec")){
                        String result1f = slot6(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }else if(result1e.equalsIgnoreCase("lab")){
                        
                    }
                }
            } 
             
             
             
             
             
             
//            boolean result1 = slot1(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0));
         }
         if(divShift == 2){
             todaysLecCountRemainig = noOfLecOnThatDayForThatClass.get(i).get(0);         //get(acctoday)
             onlyTodaysLabCount = 0;
            String result2a = slot3(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
           
            if(result2a.equalsIgnoreCase("lec")){
                String result2b = slot4(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                String result2c = slot5(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                if(result2c.equalsIgnoreCase("lec")){
                    String result2d = slot6(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    String result2e = slot7(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result2e.equalsIgnoreCase("lec")){
                        String result2f = slot8(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }else if(result2e.equalsIgnoreCase("lab")){
                        
                    }
                }else if(result2c.equalsIgnoreCase("lab")){
                    String result2e = slot7(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                }
                
            }else if(result2a.equalsIgnoreCase("lab")){
                String result2c = slot5(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                if(result2c.equalsIgnoreCase("lec")){
                    String result2d = slot6(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    String result2e = slot7(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result2e.equalsIgnoreCase("lec")){
                        String result2f = slot8(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }else if(result2e.equalsIgnoreCase("lab")){
                        
                    }
                    
                }else if(result2c.equalsIgnoreCase("lab")){
                    String result2e = slot7(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result2e.equalsIgnoreCase("lec")){
                        String result2f = slot8(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }else if(result2e.equalsIgnoreCase("lab")){
                        
                    }
                }
            } 
               
         }
         if(divShift == 3){
            todaysLecCountRemainig = noOfLecOnThatDayForThatClass.get(i).get(0);
            onlyTodaysLabCount = 0;
            String result3a = slot4(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
            String result3b = slot5(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
            
            if(result3b.equalsIgnoreCase("lec")){
                String result3c = slot6(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                String result3d = slot7(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                if(result3d.equalsIgnoreCase("lec")){
                    String result3e = slot8(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result3e.equalsIgnoreCase("lec")){
                        String result3f = slot9(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }
                }else{
                    String result3f = slot9(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                }
                
            }else{
                String result3d = slot7(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                if(result3d.equalsIgnoreCase("lec")){
                    String result3e = slot8(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    if(result3e.equalsIgnoreCase("lec")){
                        String result3f = slot9(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                    }
                }else{
                    String result3f = slot9(i,noOfLecOnThatDayForThatClass.get(i).get(0),labHourInsert.get(i).get(0),lecSub,subjects);
                }
            }
            
         }
    }
     
     
     String slot1(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"8:10",subjects);
        
         return str;
     }
     
     String slot2(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
//            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"9:00",subjects);
             str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,"9:10",subjects);
        
         return str;
     }
     
     String slot3(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"10:25",subjects);
        
         return str;
     }
     
     String slot4(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
//            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"11:25",subjects);
             str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,"11:25",subjects);
        
         return str;
     }
     
     String slot5(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"12:45",subjects);
        
         return str;
     }
     
     String slot6(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
//            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"1:45",subjects);
            str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,"1:45",subjects);
             
         return str;
     }
     
     String slot7(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"3:00",subjects);
        
         return str;
     }
    
     String slot8(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"4:00",subjects);
        
         return str;
     }
     
     String slot9(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,int[] subjects){
         
         String str = "";
         if(todaysLecCountRemainig>0)
//            str = slotAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,"5:00",subjects);
             str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,"5:00",subjects);
        
         return str;
     }
    
     
     String slotAllocationFunction(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,String slotTime,int[] subjects){
         
         String str = "";
         System.out.println("..");
         System.out.println("Lab hours today : "+labHoursOnThatDay);
         System.out.println("Todays lec remaining : "+todaysLecCountRemainig);
         System.out.println("todays Lab count : "+onlyTodaysLabCount);
//         i.e nooflabsonthatday
System.out.println("--");
         
//        if(labHoursOnThatDay >0){
//            if(labHoursOnThatDay>0){
                if(todaysLecCountRemainig==2){
                    if(onlyTodaysLabCount<labHoursOnThatDay){
                        str = slotLabAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,slotTime,subjects);
                    }else{
                        str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,slotTime,subjects);
                    }
                }else if(todaysLecCountRemainig==4){
                    if(labHoursOnThatDay==2){
                        if(onlyTodaysLabCount==0){
                            str = slotLabAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,slotTime,subjects);
                        }else{
                            str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,slotTime,subjects);
                        }
                    }else{
                        str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,slotTime,subjects);
                    }
                }
                else{
                int[] labselect={0,1};
                 int randomIndex = generator.nextInt(labselect.length);
                 int labBoolean = labselect[randomIndex];
                 System.out.println("want to lab : "+labBoolean);

                 if(labBoolean == 1)
                 {
                    if(onlyTodaysLabCount<labHoursOnThatDay){
                     str = slotLabAllocationFunction(i,noOfLecOnThatDay,labHoursOnThatDay,lecSub,slotTime,subjects);
                    }
                    else{
                        str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,slotTime,subjects);
                    }
                 }else{
                        str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,slotTime,subjects);
                    }
                    System.out.println("end");
                }
           return str;
     }
     
     
    String slotLabAllocationFunction(int i,int noOfLecOnThatDay, int labHoursOnThatDay, ArrayList<Integer> lecSub,String slotTime,int[] subjects){
        
        int temp1 = 0;
        String str = "";
        if(labHoursOnThatDay >0){
            if(onlyTodaysLabCount<labHoursOnThatDay){
                 ArrayList<Integer> labSub = new ArrayList<Integer>();
                 
                 String sql="";
                 
                 try{
                      for(int val : subjects){
                        sql="Select lab_hours_per_week from subject where subject_id="+val;
                        System.out.println(sql);
                        ps=conn.prepareStatement(sql);
                        rs=ps.executeQuery();
                        int originalHrs = 0;
                        while(rs.next()){
                           originalHrs =rs.getInt("lab_hours_per_week");
                        }
                        
                        
                        if(originalHrs>0){
                            sql="Select lab_hour from timetable where subject_id="+val+" and division_name='"+div.get(i)+"'";
                            System.out.println(sql);
                            ps=conn.prepareStatement(sql);
                            rs=ps.executeQuery();
                            int allocateHrs = 0;
                            while(rs.next()){
                                allocateHrs += rs.getInt("lab_hour");
                            }
                            if(allocateHrs<originalHrs){
                        
                                labSub.add(val);
                            }
                        
                        }
                      }

                        Random r = new Random();
                        int free = -1;
                        int teacher_id=-1;
                        int randomLabSub=-1;
                        while(free!=0){
                            System.out.println("heyy");
                            randomLabSub = labSub.get(r.nextInt(labSub.size()));
                            System.out.println(randomLabSub);

                            teacher_id=allocationSubjectTeacher.get(i).get(randomLabSub);
                            System.out.println(teacher_id);
                            if(timeSlot.get(teacher_id).containsKey(slotTime))
                                free=timeSlot.get(teacher_id).get(slotTime);
                              System.out.println(free);
                           
                        }
                        String nextHour = "";
                        timeSlot.get(teacher_id).replace(slotTime,randomLabSub);

                        
                        if(slotTime.equalsIgnoreCase("8:10")){
                            nextHour = "9:10";
                        }
                        if(slotTime.equalsIgnoreCase("10:25")){
                            nextHour = "11:25";
                        }
                        if(slotTime.equalsIgnoreCase("12:45")){
                            nextHour = "1:45";
                        }
                        if(slotTime.equalsIgnoreCase("3:00")){
                            nextHour = "4:00";
                        }
                        if(slotTime.equalsIgnoreCase("4:00")){
                            nextHour = "5:00";
                        }
                        timeSlot.get(teacher_id).replace(nextHour,randomLabSub);
                        //for(int key: timeSlot.keySet()){
                            System.out.println(teacher_id + ": ");
                            for(String k: timeSlot.get(teacher_id).keySet()){
                                System.out.println(k + " : " + timeSlot.get(teacher_id).get(k));
                            }
//                      }
//                        System.out.println("11:25 : "+timeSlot.get(teacher_id).get("11:25"));
                        sql = "INSERT INTO timetable(division_name,slot,day_id,subject_id,teacher_id,lecture_hour,lab_hour) VALUES(?,?,?,?,?,?,?)";
                        try{
                            ps = conn.prepareStatement(sql);
//                            System.out.println(div.get(i));
                            ps.setString(1,div.get(i));
                            ps.setString(2,slotTime);
                            ps.setInt(3,0);
                            ps.setInt(4,randomLabSub);
                            ps.setInt(5,teacher_id);
                            ps.setInt(6,0);
                            ps.setInt(7,1);
                            System.out.println(sql);
                            ps.execute();
                            
                            
                            sql = "INSERT INTO timetable(division_name,slot,day_id,subject_id,teacher_id,lecture_hour,lab_hour) VALUES(?,?,?,?,?,?,?)";
                            
                            ps = conn.prepareStatement(sql);
//                            System.out.println(div.get(i));
                            ps.setString(1,div.get(i));
                            ps.setString(2,nextHour);
                            ps.setInt(3,0);
                            ps.setInt(4,randomLabSub);
                            ps.setInt(5,teacher_id);
                            ps.setInt(6,0);
                            ps.setInt(7,1);
                            System.out.println(sql);
                            ps.execute();
                            
                            todaysLecCountRemainig--;
                            todaysLecCountRemainig--;
                            onlyTodaysLabCount++;
                            temp1 = 1;
                            
                        }catch(SQLException e){
                            System.out.println(e.getMessage());
                        }
                     
                 }catch(Exception e){}
                   }
                 }else{
                    str = slotLecAllocationFunction(i,noOfLecOnThatDay,lecSub,slotTime,subjects);
                 }
        if(temp1==1){
            return "lab";
        }else{
            return "lec";
        }
        
    }
  
    String slotLecAllocationFunction(int i,int noOfLecOnThatDay, ArrayList<Integer> lecSub,String slotTime,int[] subjects){
        
        int temp2=0;
        if(noOfLecOnThatDay>0){
            if(todaysLecCountRemainig>0){
//                        ArrayList<Integer> lecSub = new ArrayList<Integer>();
                        String sql="";
                 
                 try{
                        
                        Random r = new Random();
                        int removeAfterAllocate = -1;
                        int free = -1;
                        int teacher_id=-1;
                        int randomLecSub=-1;
                        
//                        System.out.println(teacher_id + ": ");
//                            for(String k: timeSlot.get(teacher_id).keySet()){
//                                System.out.println(k + " : " + timeSlot.get(teacher_id).get(k));
//                            }
                        
                        while(free!=0){
                            System.out.println("heyy");
                            System.out.println("printing lecSub arraylist");
                            for(int m=0;m<lecSub.size();m++){
                                System.out.println(lecSub.get(m));
                            }
                            randomLecSub = lecSub.get(r.nextInt(lecSub.size()));
                            System.out.println("sub : "+randomLecSub);
                            
                            for(int p=0;p<lecSub.size();p++){
                                if(lecSub.get(p) == randomLecSub){
                                    removeAfterAllocate = p;
                                    System.out.println("removeallocatesub : "+removeAfterAllocate);
                                }
                            }

                            teacher_id=allocationSubjectTeacher.get(i).get(randomLecSub);
                            System.out.println("teacher : "+teacher_id);
                            for(String k: timeSlot.get(teacher_id).keySet()){
                                System.out.println(k + " : " + timeSlot.get(teacher_id).get(k));
                            }
                            if(timeSlot.get(teacher_id).containsKey(slotTime)){
                               free=timeSlot.get(teacher_id).get(slotTime);
                            }else{
                                System.out.println("slot not found");
                            }
                              System.out.println(free);
                           
                        }
                        
                        timeSlot.get(teacher_id).replace(slotTime,randomLecSub);
//                        for(int key: timeSlot.keySet()){
                            System.out.println(teacher_id + ": ");
                            for(String k: timeSlot.get(teacher_id).keySet()){
                                System.out.println(k + " : " + timeSlot.get(teacher_id).get(k));
                            }
//                      }
//                        timeSlot.get(teacher_id).replace("11:25",0,randomLabSub);
//                        System.out.println("11:25 : "+timeSlot.get(teacher_id).get("11:25"));
                        sql = "INSERT INTO timetable(division_name,slot,day_id,subject_id,teacher_id,lecture_hour,lab_hour) VALUES(?,?,?,?,?,?,?)";
                        try{
                            ps = conn.prepareStatement(sql);
//                            System.out.println(div.get(i));
                            ps.setString(1,div.get(i));
                            ps.setString(2,slotTime);
                            ps.setInt(3,0);
                            ps.setInt(4,randomLecSub);
                            ps.setInt(5,teacher_id);
                            ps.setInt(6,1);
                            ps.setInt(7,0);
                            System.out.println(sql);
                            ps.execute();
                            
                            
                            lecSub.remove(removeAfterAllocate);
                            todaysLecCountRemainig--;
                            temp2 = 1;
//                            for(int g=0;g<lecSub.size();g++){
//                                System.out.println(lecSub.get(g));
//                            }
                            
                        }catch(SQLException e){
                            System.out.println(e.getMessage());
                        }
                     
                 }catch(Exception e){}
                 
        }
                    }
        
        if(temp2==1){
            return "lec";
        }else{
            return "";
        }
    }
    
    
}

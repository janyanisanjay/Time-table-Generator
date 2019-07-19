
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class Allocation {
    ArrayList<String> div;
    ArrayList<ArrayList<Integer>> subject;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Random generator = new Random();
    ArrayList<HashMap<Integer,Integer>> allocationSubjectTeacher = new ArrayList<HashMap<Integer,Integer>>(); 
    
    
    Allocation(){
        
    }
    
    Allocation(ArrayList<String> div, ArrayList<ArrayList<Integer>> subject){
        this.div = div;
        this.subject = subject;
        conn = MySQLConnect.connectDB();
    }
    
    public void allocateTeacher(){
        
        for (int i = 0; i < subject.size(); i++) {

for (int k = 0; k < subject.get(i).size(); k++) {

System.out.print(" " + subject.get(i).get(k));

}

System.out.println();

}
//        System.out.println("heyyyyyyy");
//        
//        for(int i=0; i<div.size(); i++){
//                System.out.println(div.get(i));
//            }
        
        System.out.println("subject size : "+subject.size());
        System.out.println("div size : "+div.size());
        HashMap<Integer,Integer> subjectTeacher;
    for (int i = 0; i < subject.size(); i++) {
        System.out.println("i = "+i);
        subjectTeacher = new HashMap<>();
        for (int k = 0; k < subject.get(i).size(); k++) {
            try {
//                System.out.println(subject.get(i).size());
//                System.out.println("hello");
                int getSubjectID = subject.get(i).get(k);
                
                
                String sql = "SELECT ts.teacher_id FROM teacher_subject_preference ts, teacher t WHERE ts.subject_id ="+getSubjectID+" AND t.teacher_id = ts.teacher_id AND t.max_subject_count<4";
                ps = conn.prepareStatement(sql);
                
//                System.out.println(sql);
                rs = ps.executeQuery();
                
                int count = 0;
                while(rs.next()){
                    count++;
                }
                
                
                
//                System.out.println("count : "+count);
                int[] arr = new int[count];
                
                int j=0;
                rs = ps.executeQuery();
                
                
                
                
                while(rs.next()){
//                    System.out.println("Division : " +getSubjectID+" : subject: "+ rs.getInt("teacher_id"));
                    
//                    System.out.println(rs.getInt("teacher_id"));
                    arr[j] = rs.getInt("teacher_id");
                    j++;
                    
                }
                
//                for(int s=0;s<arr.length;s++){
//                    System.out.println(arr[s]);
//                }
                
                int randomIndex = generator.nextInt(arr.length);
                int teacher_id = arr[randomIndex];
                System.out.println("random "+teacher_id);
                
//                call one fn checck which checks that if teacher is senior and if has count less than 2 then lem him take subject
//                        else call fn again so that let non senior take the subject 
                
            
                

//                System.out.print("Subject id : "+getSubjectID);
//                        
//                System.out.println("      Teacher Id : "+teacher_id);
                
//                System.out.println("bef");
                subjectTeacher.put(getSubjectID,teacher_id);
                System.out.println(getSubjectID+" "+teacher_id);
//                System.out.println("aftr");
//                
//                for(Integer key: subjectTeacher.keySet()){
//    		System.out.println(key + ": " + subjectTeacher.get(key));
//                }
                
//                System.out.println("befr 1");
//                allocationSubjectTeacher.add(subjectTeacher);
//                System.out.println("after 1");
                
                //update query for count
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Allocation.class.getName()).log(Level.SEVERE, null, ex);
            }
//            System.out.println("k :" +k);
        }
        
        allocationSubjectTeacher.add(subjectTeacher);

        System.out.println(subject.get(i)+"------------------");
        for(Integer key: allocationSubjectTeacher.get(i).keySet()){
    		System.out.println(key + ": " + allocationSubjectTeacher.get(i).get(key));
    	}
        
    
    }
    for (Integer key : allocationSubjectTeacher.get(17).keySet() ) {
                System.out.println(key);
                System.out.println("    :"+allocationSubjectTeacher.get(17).get(key));
        }
    
    
        System.out.println("here");
    
    
    new Slot(div,subject,allocationSubjectTeacher).createSlot();
    
    
}
    
}

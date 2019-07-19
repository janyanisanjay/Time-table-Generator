
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAHIL
 */
public class Division {
//    private String divName;
//    private int id;
    private ArrayList<String> div;
//    private ArrayList<Integer> subjects;
    
    private Connection conn = null;
    //private Statement statement = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    private ArrayList<ArrayList<Integer>> subject;
    
    
    Division(ArrayList<String> div, ArrayList<ArrayList<Integer>> subject){
        this.div = div;
        this.subject = subject;
       conn = MySQLConnect.connectDB();
//         System.out.println("ssss");
         addSubject();
         
    }
    
    private void addSubject(){
//        System.out.println("ss");
        int sem=1;
        int flag=0;
        int match=0;
        
        String[] fe = {"D1A","D1B","D2A","D2B","D2C","D3","D4A","D4B","D5"};
        String[] se = {"D6A","D6B","D7A","D7B","D7C","D8","D9A","D9B","D9C","D10"};
        String[] te = {"D11A","D11B","D12A","D12B","D12C","D13","D14A","D14B","D14C","D15"};
        String[] be = {"D16A","D16B","D17A","D17B","D17C","D18","D19A","D19B","D19C","D20"};
        
        for(int i=0; i<div.size();i++){
          
        if(match==0){   
        for(String temp: fe){
//            System.out.println("hey");
            if(temp.equals(div.get(i))){
                if(sem==1){
//                    System.out.println("hi");
                    flag = 1;
                    match=1;
                    break;
                }
                else{
                    flag = 2;
                    match=1;
                    break;
                }
            }
        }
        }
//         System.out.println("out for  1");

        if(match==0){
        for(String temp: se){
//            System.out.println("in for 2");
            if(temp.equals(div.get(i))){
                if(sem==1){
                    flag = 3;
                    match=1;
                    break;
                }
                else{
                    flag = 4;
                    match=1;
                    break;
                }
            }
        }
        }
        if(match==0){
        for(String temp: te){
            if(temp.equals(div.get(i))){
                if(sem==1){
                    flag = 5;
                    match=1;
                    break;
                }
                else{
                    flag = 6;
                    match=1;
                    break;
                }
            }
        }
        }
        
        if(match==0){
        for(String temp: be){
            if(temp.equals(div.get(i))){
                if(sem==1){
                    flag = 7;
                    match=1;
                    break;
                }
                else{
                    flag = 8;
                    match=1;
                    break;
                }
            }
        }
        }
        
//         System.out.println("here");

        String sql ="SELECT s.subject_name,s.subject_id from subject s,division d WHERE s.branch_id = d.branch_id and d.division_name = ? and s.sem_id =?";
        try{
//             System.out.println("in try");

        ps=conn.prepareStatement(sql);
        ps.setString(1, div.get(i));
        ps.setInt(2, flag);
//            System.out.println(div.get(i));
//            System.out.println(flag);
        rs=ps.executeQuery();
//            System.out.println(sql);
//            System.out.println(div.get(i));
            ArrayList<Integer> sub = new ArrayList<Integer>();
        while(rs.next()){
            
//            System.out.println("-" +rs.getString("subject_name"));
            
            sub.add(rs.getInt("subject_id"));
            
        }
        
        subject.add(sub);
        
        
        
        
        match=0;
        
        }catch(Exception e){}
        
        }
        
//        for (int i = 0; i < subject.size(); i++) {
//
//for (int k = 0; k < subject.get(i).size(); k++) {
//
//System.out.print(" " + subject.get(i).get(k));
//
//}
//
//System.out.println();
//
//}
        
        
        new Allocation(div,subject).allocateTeacher();
        
        
        
    }
    
//    SELECT * from subject s,division d WHERE s.branch_id = d.branch_id and d.division_name = "D6A" and s.sem_id = 3
    
    
    
}

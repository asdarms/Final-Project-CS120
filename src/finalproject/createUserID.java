/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author samue
 */
import java.util.*;

public class createUserID {
    
    public static String randomID(){
        String userID = "";
        
        for(int i =0; i < 1;i++){
            Random digit = new Random();
            int randomInt = digit.nextInt(10);
            
            String num = Integer.toString(randomInt);
            userID = userID + num;
        }   
        
        return userID;
    }
}

package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int validPasswordCount = 0;
        File inputFile = new File("input.txt");
        try{
            int lineCount=1;
            Scanner fileReader = new Scanner(inputFile);
            while(fileReader.hasNext()){
                System.out.println("Trying line #: "+lineCount);
                Map<String,String> map = createPasswordMap(fileReader.nextLine());
                System.out.println("Mapped line #: "+lineCount);
                lineCount++;
                if(checkPasswordValidity(map)){
                    validPasswordCount++;
                };
            }
        }catch (FileNotFoundException e){
            System.out.println("Something went wrong with the file");
        }catch (Exception e){
            System.out.println("Something else went wrong!\n" + e.getMessage());
        }
        System.out.println("Valid password count is: "+ validPasswordCount);
    }



    private static Map<String,String> createPasswordMap(String inputString){
        Map<String,String> map = new HashMap<>();
        String min = String.valueOf(inputString.charAt(0));
        String max = String.valueOf(inputString.charAt(2));
        String searchParameter = String.valueOf(inputString.charAt(4));
        String password = inputString.substring(inputString.indexOf(": ")+2).trim();
        map.put("min",min);
        map.put("max",max);
        map.put("search",searchParameter);
        map.put("password",password);
        return map;
    }
    private static boolean checkPasswordValidity(Map<String,String> passwordMap){
        int min = Integer.parseInt(passwordMap.get("min"));
        int max = Integer.parseInt(passwordMap.get("max"));
        String searchParam = passwordMap.get("search");
        String password = passwordMap.get("password");
        int count = 0;
        for (int i = 0; i <password.length() ; i++) {
            if( String.valueOf(password.charAt(i)).equals(searchParam) ){
                count++;
            }
        }
        return count >= min && count <= max;
    }



}
package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int validPasswordCountPart1 = 0;
        int validPasswordCountPart2 = 0;
        File inputFile = new File("input.txt");
        try{
            Scanner fileReader = new Scanner(inputFile);
            while(fileReader.hasNext()){
                Map<String,String> map = createPasswordMap(fileReader.nextLine());
                if(checkPasswordValidityPart1(map)){
                    validPasswordCountPart1++;
                };
                if(checkPasswordValidityPart2(map)){
                    validPasswordCountPart2++;
                };
            }
        }catch (FileNotFoundException e){
            System.out.println("Something went wrong with the file");
        }catch (Exception e){
            System.out.println("Something else went wrong!\n" + e.getMessage());
        }
        System.out.println("Valid password count for part 1 is: "+ validPasswordCountPart1);
        System.out.println("Valid password count for part 2 is: "+ validPasswordCountPart2);
    }



    private static Map<String,String> createPasswordMap(String inputString){
        Map<String,String> map = new HashMap<>();
        String min = inputString.substring(0,inputString.indexOf("-"));
        String max = inputString.substring(inputString.indexOf("-")+1,inputString.indexOf(" ") );
        String searchParameter =inputString.substring(inputString.indexOf(":")-1,inputString.indexOf(":"));
        String password = inputString.substring(inputString.indexOf(": ")+2).trim();
        map.put("min",min);
        map.put("max",max);
        map.put("search",searchParameter);
        map.put("password",password);
        return map;
    }
    private static boolean checkPasswordValidityPart1(Map<String,String> passwordMap){
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

    public static boolean checkPasswordValidityPart2(Map<String,String> passwordMap){
        int position1 = Integer.parseInt(passwordMap.get("min")) - 1;
        int position2 = Integer.parseInt(passwordMap.get("max")) - 1;
        String searchParam = passwordMap.get("search");
        String password = passwordMap.get("password");
        return String.valueOf(password.charAt(position1)).equals(searchParam) ^ String.valueOf(password.charAt(position2)).equals(searchParam) ;
    }


}
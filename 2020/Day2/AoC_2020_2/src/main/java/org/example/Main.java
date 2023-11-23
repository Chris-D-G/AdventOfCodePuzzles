package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private static Map<String,String> createPasswordMap(String inputString){
        Map<String,String> map = new HashMap<>();
        String min = String.valueOf(inputString.charAt(0));
        String max = String.valueOf(inputString.charAt(2));
        String searchParameter = String.valueOf(inputString.charAt(4));
        String password = inputString.substring(inputString.indexOf(": ")+2);
        map.put("min",min);
        map.put("max",max);
        map.put("search",searchParameter);
        map.put("password",password);
        return map;
    }
    private boolean checkPasswordValidity(Map<String,String> passwordMap){
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
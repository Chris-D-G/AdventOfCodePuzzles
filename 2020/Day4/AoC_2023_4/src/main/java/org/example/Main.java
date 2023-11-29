package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) {
    File inputFile = new File("input.txt");
    int validPassports = 0;
    try(Scanner fileReader = new Scanner(inputFile)){
        String str = "";
        while(fileReader.hasNext()){
            String line = fileReader.nextLine();
            if(line.isEmpty()){
                Map<String,String> passportMap = passportMap(str);
                if(validPassport(passportMap)){
                    validPassports++;
                }
                str="";
                continue;
            }
            str+=line + " ";
        }
        Map<String,String> passportMap = passportMap(str);
        if(validPassport(passportMap)){
            validPassports++;
        }

    }catch (FileNotFoundException e){
        System.err.println("Something went wrong with the file opening. System will exit now." );
        System.exit(1);
    }
        System.out.println("Part 1 valid passport count: "+validPassports);
    }



    //check for valid passport
    private static boolean validPassport(Map<String,String> passportMap){
        String[] passportFields = {"byr","iyr","eyr","hgt","hcl","ecl","pid"};
        for(String field: passportFields){
            if(!passportMap.containsKey(field)){
                return false;
            }
        }
        return true;
    }
    // create passport map
    private static Map<String,String> passportMap(String str){
        Map<String,String> map = new TreeMap<>();
        String[] array = str.split(" ");
        for(String element: array){
            if(element.contains("byr")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("byr",value);
            }
            if(element.contains("iyr")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("iyr",value);
            }
            if(element.contains("eyr")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("eyr",value);
            }
            if(element.contains("hgt")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("hgt",value);
            }
            if(element.contains("hcl")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("hcl",value);
            }
            if(element.contains("ecl")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("ecl",value);
            }
            if(element.contains("pid")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("pid",value);
            }
            if(element.contains("cid")){
                String value=element.substring(element.indexOf(":")+1);
                map.put("cid",value);
            }
        }
        System.out.println(map.toString());
        return map;
    }



}
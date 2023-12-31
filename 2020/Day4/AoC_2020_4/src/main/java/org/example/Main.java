package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    private static final String[] PASSPORT_FIELDS = {"byr","iyr","eyr","hgt","hcl","ecl","pid"};
    private static final List<String> EYE_COLOR = List.of("amb","blu","brn","gry","grn","hzl","oth");
    private static int count = 0;

    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        int validPassports = 0;
        int validPassports2 = 0;
        try(Scanner fileReader = new Scanner(inputFile)){
            StringBuilder str = new StringBuilder();
            while(fileReader.hasNext()){
                String line = fileReader.nextLine();
                if(line.isEmpty()){
                    Map<String,String> passportMap = passportMap(str.toString());
                if(validPassport(passportMap)){
                    validPassports++;
                }
                    if(validPassport(passportMap) && validPassport2(passportMap)){
                        validPassports2++;
                    }
                    str = new StringBuilder();
                    continue;
                }
                str.append(line).append(" ");
            }
            Map<String,String> passportMap = passportMap(str.toString());
        if(validPassport(passportMap)){
            validPassports++;
        }
            if(validPassport(passportMap) && validPassport2(passportMap)){
                validPassports2++;
            }

        }catch (FileNotFoundException e){
            System.err.println("Something went wrong with opening the file. System will exit now." );
            System.exit(1);
        }
        System.out.println("Part 1 valid passport count: "+validPassports);
        System.out.println("Part 2 valid passport count: "+validPassports2);
    }

    /**
     * Checks if a passport meets the requirements of having all
     * required fields
     *
     * @param passportMap input passport data
     * @return true if passport is valid per requirements
     */
    private static boolean validPassport(Map<String,String> passportMap){
        for(String field: PASSPORT_FIELDS){
            if(!passportMap.containsKey(field)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if a passport meets all the requirements of a valid
     * passport. Each field is checked against specific requirements.
     *
     * @param passportMap input passport map
     * @return true if passport is valid
     */
    private static boolean validPassport2(Map<String,String> passportMap){
        for(String field: PASSPORT_FIELDS){
            switch (field){
                case "byr":
                    try{
                        if(passportMap.get("byr").length() != 4 || Integer.parseInt(passportMap.get("byr"))< 1920 || Integer.parseInt(passportMap.get("byr"))> 2002){
                            return false;
                        }
                    }catch (NumberFormatException e){
                        return false;
                    }
                    break;
                case "iyr":
                    try{
                        if(passportMap.get("iyr").length() != 4 || Integer.parseInt(passportMap.get("iyr"))< 2010 || Integer.parseInt(passportMap.get("iyr"))> 2020){
                            return false;
                        }
                    }catch (NumberFormatException e){
                        return false;
                    }
                    break;
                case "eyr":
                    try{
                        if(passportMap.get("eyr").length() != 4 || Integer.parseInt(passportMap.get("eyr"))< 2020 || Integer.parseInt(passportMap.get("eyr"))> 2030){
                            return false;
                        }
                    }catch (NumberFormatException e){
                        return false;
                    }
                    break;
                case "hgt":
                    if(!passportMap.get("hgt").contains("cm") && !passportMap.get("hgt").contains("in")){
                        return false;
                    }
                    if(passportMap.get("hgt").contains("cm")){
                        String measurement = passportMap.get("hgt").replace("cm","");
                        int height = Integer.parseInt(measurement);
                        if(height>193 || height<150){
                            return false;
                        }
                    }
                    if(passportMap.get("hgt").contains("in")){
                        String measurement = passportMap.get("hgt").replace("in","");
                        int height = Integer.parseInt(measurement);
                        if(height>76 || height<59){
                            return false;
                        }
                    }
                    break;
                case "hcl":
                    Pattern pattern = Pattern.compile("#[a-f0-9]{6}$",Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(passportMap.get("hcl"));
                    if(!matcher.find()){
                        return false;
                    }
                    break;
                case "ecl":
                    if(!EYE_COLOR.contains(passportMap.get("ecl"))){
                        return false;
                    }
                    break;
                case "pid":
                    Pattern pattern2 = Pattern.compile("^[0-9]{9}$",Pattern.CASE_INSENSITIVE);
                    Matcher matcher2 = pattern2.matcher(passportMap.get("pid"));
                    if(!matcher2.find()){
                        return false;
                    }
                    break;
            }
        }
        System.out.println("Good Passport: "+passportMap);
        return true;
    }

    /**
     * Creates passport in a TreeMap format by parsing the input string data
     * @param str String extracted from file data
     * @return Map with data parsed from file otherwise returns an empty map
     */
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
        count++;
        return map;
    }

}



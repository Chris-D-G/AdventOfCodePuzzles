package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String[] NUMBERS =  {"one","two","three","four","five","six","seven","eight","nine"};
    public static void main(String[] args) {
        long result = calibrationValueSum(parseData());
        System.out.println("Sum of calibration values: " + result);
    }

    /**
     * Parses data from a file and returns a list
     * @return list with parsed data otherwise exits the program
     */
    private static List<String> parseData(){
        File file = new File("input.txt");
        List<String> list = new ArrayList<>();
        try(Scanner fileReader = new Scanner(file);){
            while(fileReader.hasNext()){
                list.add(fileReader.nextLine().trim());
            }
        }catch (FileNotFoundException e){
            System.err.println("Unable to open file. System will exit now.");
            System.exit(1);
        }
        return list;
    }

    /**
     * Extracts the first and last digit from a string
     *
     * @param str string to search through
     * @return the first and last digits otherwise returns 0
     */
    private static long extractDigits(String str){
        boolean firstFound = false;
        boolean lastFound = false;
        int firstDigit = 0;
        int lastDigit = 0;
        System.out.println(str);
        String[] array = str.split("");
        for (int i = 0; i <array.length ; i++) {
            try{
                if(!firstFound){
                    firstDigit = Integer.parseInt( array[i]);
                    firstFound = true;
                }
            }catch (NumberFormatException e){
                int num =numberWordCheck(str, i);
                if(num !=-1){
                    firstDigit=num;
                    firstFound = true;
                }else{
                    firstFound = false;
                }
            }
            try{
                if(!lastFound){
                    lastDigit = Integer.parseInt(array[array.length-1-i]);
                    lastFound = true;
                }
            }catch (NumberFormatException e){
                int num =numberWordCheck(str, array.length-1-i);
                if(num !=-1){
                    lastDigit=num;
                    lastFound = true;
                }else{
                    lastFound = false;
                }
            }
            if(firstFound && lastFound){
                break;
            }
        }
        long digits = firstDigit* 10L + lastDigit;
        System.out.println(digits);
        return digits;
    }

    /**
     * Returns sum accumulated by extracting the first and last digits
     * of each list element to create a two-digit number and adding
     * across to running total
     * @param inputList list of strings
     * @return accumulated sum or otherwise returns 0
     */
    private static long calibrationValueSum(List<String> inputList){
        long result = 0L;
        for(String element : inputList){
            result+= extractDigits(element);
        }
        return result;
    }

    private static int numberWordCheck (String str, int index){
        int result = -1;
        try{
            switch (str.charAt(index)){
                case'o':
                    if(str.substring(index, index+3).equals("one")){
                        return 1;
                    }
                    break;
                case't':
                    if(str.substring(index,index+3).equals("two")){
                        return 2;
                    }else if(str.substring(index,index+5).equals("three")){
                        return 3;
                    }
                    break;
                case 'f':
                    if(str.substring(index,index+4).equals("four")){
                        return 4;
                    } else if (str.substring(index,index+4).equals("five")){
                        return 5;
                    }
                    break;
                case 's':
                    if(str.substring(index,index+3).equals("six")){
                        return 6;
                    }else if(str.substring(index,index+5).equals("seven")){
                        return 7;
                    }
                    break;
                case 'e':
                    if(str.substring(index,index+5).equals("eight")){
                        return 8;
                    }
                    break;
                case 'n':
                    if(str.substring(index,index+4).equals("nine")){
                        return 9;
                    }
                    break;
            }
        }catch (StringIndexOutOfBoundsException ignored){}

        return result;
    }
}
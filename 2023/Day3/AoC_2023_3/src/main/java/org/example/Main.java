package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String pattern ="[^\\w\\d\\s\\.]";
    private static final String justStar ="[\\*]";
    private static final String justNumber ="[\\d]";

    public static void main(String[] args) {
        findEnginePartNumbers(parseData());
    }

    private static List<String> parseData(){
        List<String> list = new ArrayList<>();
        File file = new File("test.txt");
        try(Scanner fileReader = new Scanner(file)){
            while(fileReader.hasNext()){
                list.add(fileReader.nextLine());
            }
        }catch (FileNotFoundException e){
            System.err.println("Unable to open the file. Empty list returned.");
            return list;
        }
        return list;
    }

    private static void findEnginePartNumbers(List<String> input){}

//
//    private static void findEnginePartNumbers(List<String> inputList){
//        long partEngineSum = 0L;
//        for (int i = 0; i <inputList.size(); i++){
//            for (int j = 0; j <inputList.get(i).length(); j++) {
//                if(String.valueOf(inputList.get(i).charAt(j)).matches(pattern)){
//                    //Check to the left of the symbol,accumulate digits, replace with periods
//                    int currentIndex =j-1;
//                    String enginePartNumber = "";
//                    while(!(currentIndex <0) && Character.isDigit(inputList.get(i).charAt(currentIndex))){
//                        enginePartNumber=inputList.get(i).charAt(currentIndex)+enginePartNumber;
//                        char[] array = inputList.get(i).toCharArray();
//                        array[currentIndex] ='.';
//                        inputList.remove(i) ;
//                        inputList.add(i,new String(array));
//                        currentIndex--;
//                    }
//                    if(!enginePartNumber.isEmpty()){
//                        partEngineSum+= Long.parseLong(enginePartNumber);
//                    }
//                    //Check to the right of the symbol,accumulate digits, replace with periods
//                    currentIndex=j+1;
//                    enginePartNumber="";
//                    while(!(currentIndex>inputList.get(i).length()-1)&& Character.isDigit(inputList.get(i).charAt(currentIndex))){
//                        enginePartNumber += inputList.get(i).charAt(currentIndex);
//                        char[] array = inputList.get(i).toCharArray();
//                        array[currentIndex] = '.';
//                        inputList.remove(i) ;
//                        inputList.add(i,new String(array));
//                        currentIndex++;
//                    }
//                    if(!enginePartNumber.isEmpty()){
//                        partEngineSum+= Long.parseLong(enginePartNumber);
//                    }
//                    //check diagonal left of symbol,accumulate digits, replace with periods
//                    enginePartNumber="";
//                    currentIndex = j-1;
//                    //line number = i-1
//                    if(i!=0){//
//                        while(!(currentIndex<0) && Character.isDigit(inputList.get(i-1).charAt(currentIndex)) ){
//                            enginePartNumber += inputList.get(i-1).charAt(currentIndex);
//                            char[] array = inputList.get(i).toCharArray();
//                            array[currentIndex]= '.';
//                            inputList.remove(i) ;
//                            inputList.add(i,new String(array));
//                            currentIndex--;
//                        }
//                        currentIndex =j;
//                        while(!(currentIndex>inputList.get(i-1).length()) && Character.isDigit(inputList.get(i-1).charAt(currentIndex)) ){
//                            enginePartNumber += inputList.get(i-1).charAt(currentIndex);
//                            char[] array = inputList.get(i).toCharArray();
//                            array[currentIndex]= '.';
//                            inputList.remove(i) ;
//                            inputList.add(i,new String(array));
//                            currentIndex++;
//                        }
//                        if(!enginePartNumber.isEmpty()){
//                            partEngineSum+=Long.parseLong(enginePartNumber);
//                        }
//                    }
//
//
//                }
//            }
//        }
//
//        System.out.println("Sum of part engine numbers: "+ partEngineSum);
//    }




}
package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    /**
     * Parses data from a file and returns a list of strings
     * @return list of strings if data is found otherwise returns empty list
     */
    private static List<String> parseData(){
        File file = new File("input.txt");
        List<String> list = new ArrayList<>();
        try(Scanner fileReader = new Scanner(file)){
            while(fileReader.hasNext()){
                list.add(fileReader.nextLine());
            }
        }catch (FileNotFoundException e){
            System.err.println("Unable to open the file.");
            System.exit(1);
        }
        return list;
    }

    private static long caculateResults(List<String> sizeOfPresents){
        long wrappingPaper = 0L;
        return 0L;
    }



}
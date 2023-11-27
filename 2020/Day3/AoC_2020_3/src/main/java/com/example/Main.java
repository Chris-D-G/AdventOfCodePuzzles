package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //
        File input = new File("input.txt");
        int lineCount = 0;
        List<String> list = new ArrayList<>();
        try( Scanner filereader = new Scanner(input) ){
            while(filereader.hasNext()){
                list.add(filereader.nextLine().trim()) ;
            }
        }catch (FileNotFoundException e){
            System.out.println("Something went wrong with the file!");
        }
        System.out.println("Tree count: " + countTrees(list));


    }
    public static boolean checkForTree(String input, int index){
        // tree = '#';
        // opening = '.';
        return input.charAt(index)=='#';

    }
    public static int countTrees(List<String> asciiTreeMap){
        // length of line :31 chars. index 0 -> 30
        // Number of lines: 323
        int treeCount = 0;
        int position = 3;
        for(int i =1; i<asciiTreeMap.size();i++){
            if(position<=30){
                if(checkForTree(asciiTreeMap.get(i),position)){
                    treeCount++;
                }
                position+=3;
            }else{
                position-=31;
                if(checkForTree(asciiTreeMap.get(i),position)){
                    treeCount++;
                }
            }
        }
        return treeCount;
    }


}

package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File input = new File("input.txt");
        List<String> list = createExpandedAsciiMap(input);
        System.out.println("Tree count part 1: " + countTrees(list,3,1));
        long slope1Results=countTrees(list,1,1);
        long slope2Results=countTrees(list,3,1);
        long slope3Results=countTrees(list,5,1);
        long slope4Results=countTrees(list,7,1);
        long slope5Results=countTrees(list,1,2);
        System.out.println("Tree count part 2:" +(slope1Results
                                                 *slope2Results
                                                 *slope3Results
                                                 *slope4Results
                                                 *slope5Results) );
    }
    public static List<String> createExpandedAsciiMap(File inputFile){
        List<String> list = new ArrayList<>();
        try( Scanner fileReader = new Scanner(inputFile) ){
            while(fileReader.hasNext()){
                String line =fileReader.nextLine().trim();
                list.add(line) ;
            }
        }catch (FileNotFoundException e){
            System.out.println("Something went wrong with the file!");
            return list;
        }
        return list;
    }

    public static boolean checkForTree(String input, int index){
        return input.charAt(index)=='#';
    }
    public static long countTrees(List<String> asciiTreeMap,int xTraversal, int yTraversal){
        long treeCount = 0;
        int position = xTraversal;
        for(int i =1; i<asciiTreeMap.size();i+=yTraversal){
            if(checkForTree(asciiTreeMap.get(i),position)){
                treeCount++;
            }
            position+=xTraversal;
            position%=asciiTreeMap.get(i).length();
        }
        System.out.println("Slope: Right: "+xTraversal+" Down: "+yTraversal+" Tree Count: "+treeCount);
        return treeCount;
    }
}

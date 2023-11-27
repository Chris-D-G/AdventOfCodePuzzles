package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<String> list = createAsciiMapList("input.txt");
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

    /**
     *
     * Creates a list representing the locations of open spaces and trees
     * from a provided file
     * @param filepath file path of text file to create list from
     * @return a list of strings, otherwise returns an empty list if unable to open a file
     */
    public static List<String> createAsciiMapList(String filepath){
        File input = new File(filepath);
        List<String> list = new ArrayList<>();
        try( Scanner fileReader = new Scanner(input) ){
            while(fileReader.hasNext()){
                String line =fileReader.nextLine().trim();
                list.add(line) ;
            }
        }catch (FileNotFoundException e){
            System.out.println("Something went wrong with the file. Empty list returned!");
            return list;
        }
        return list;
    }

    /**
     * Returns true if a tree represented by the character '#' is found
     * at the specified position in the input string
     * @param input string representing the location of trees and open spaces
     * @param index position to search at
     * @return true if a tree is found, otherwise false
     */
    public static boolean checkForTree(String input, int index){
        return input.charAt(index)=='#';
    }

    /**
     * Counts the numbers of tree found by traversing the provided location
     * map at the specified horizontal and vertical movement
     *
     * @param asciiMap list of string representing the location of trees and open spaces
     * @param xTraversal horizontal movement
     * @param yTraversal vertical movement
     * @return number of trees found for the provided map
     */
    public static long countTrees(List<String> asciiMap,int xTraversal, int yTraversal){
        long treeCount = 0;
        int position = xTraversal;
        for(int i =1; i<asciiMap.size();i+=yTraversal){
            if(checkForTree(asciiMap.get(i),position)){
                treeCount++;
            }
            position+=xTraversal;
            position%=asciiMap.get(i).length();
        }
        System.out.println("Slope: Right: "+xTraversal+" Down: "+yTraversal+" Tree Count: "+treeCount);
        return treeCount;
    }
}

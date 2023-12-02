package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        calculateResults(parseData());

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

    private static void calculateResults(List<String> sizeOfPresents){
        long wrappingPaper = 0L;
        long requiredRibbon =0L;
        for (String element : sizeOfPresents){
            List<Long> dimensions= Stream.of(element.split("x"))
                                        .map(Long::valueOf)
                                        .sorted()
                                        .collect(Collectors.toList());
            System.out.println(dimensions);
            long area1 = dimensions.get(0) * dimensions.get(1);
            long area2 = dimensions.get(1) * dimensions.get(2);
            long area3 = dimensions.get(0) * dimensions.get(2);
            long currentPaper = 2*(area1+area2+area3) + area1;
            long volume = dimensions.get(0) * dimensions.get(1) * dimensions.get(2);
            long currentRibbon =volume + 2*(dimensions.get(0) + dimensions.get(1));
            wrappingPaper+=currentPaper;
            requiredRibbon+=currentRibbon;
        }
        System.out.println("Total paper needed: "+ wrappingPaper+ " sqft." );
        System.out.println("Total ribbon needed: " + requiredRibbon + " ft.");
    }



}
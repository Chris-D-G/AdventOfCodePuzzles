package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Type filepath to load: ");
        String filepath = input.nextLine().toLowerCase().trim();
        List<Integer> list = loadData(filepath);
        if(list != null){
            System.out.print("\nProvide required sum to find factors for: ");
            int response = Integer.parseInt(input.nextLine());
            System.out.println("Part 1 Multiplicand = "+sumTwoGiveMultiplicand(list, response));
            System.out.println("Part 2 Multiplicand = "+sumThreeGiveMultiplicand(list, response));
        }

    }
    /**
     * Method to load numeric data from a file into a data structure
     *
     * @param filepath path of .txt file to load
     * @return an arraylist with loaded data.
     */
    private static List<Integer> loadData(String filepath){
        File file = new File(filepath);
        List<Integer> listData = null;
        try{
            listData = new ArrayList<>();
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNext()){
                listData.add(Integer.parseInt(fileReader.nextLine()));
            }
            Collections.sort(listData);
        }catch (FileNotFoundException e){
            System.out.println("Something went wrong with the file");
        }
        return listData;
    }

    /**
     *  Find two factors that add to requiredSum and return their product
     *
     * @param numList list of numbers to look through
     * @param requiredSum sum to look for
     * @return the product of the two factors
     */
    private static int sumTwoGiveMultiplicand(List<Integer> numList, int requiredSum){
        int x = 0;
        int y = 0;
        for(Integer number: numList){
            x=number;
            // based on principle that sum = number1 + (sum - number1)
            int summingPartner = requiredSum - x;
            if( numList.contains(summingPartner) ){
                y=requiredSum-x;
            }
            if(y!=0){
                break;
            }
        }
        System.out.println("x = " + x + "\ny = " + y);
        return x*y;
    }

    /**
     * Find three factors that add to requiredSum and return their product
     *
     * @param numList list of numbers to look through
     * @param requiredSum2 sum to look for
     * @return the product of the three factors
     */
    private static int sumThreeGiveMultiplicand(List<Integer> numList, int requiredSum2){
        int z = 0;
        int innerMultiplicand= 0;
        for (int i = 0; i <numList.size() ; i++) {
            z = numList.get(i);
            int summingPartnersSum = requiredSum2 - z;
            List<Integer> sublist = numList.subList(i+1,numList.size());
            innerMultiplicand = sumTwoGiveMultiplicand(sublist,summingPartnersSum);
            if(innerMultiplicand != 0){
                System.out.println("z = " + z);
                break;
            }
        }
        return z * innerMultiplicand;
    }
}
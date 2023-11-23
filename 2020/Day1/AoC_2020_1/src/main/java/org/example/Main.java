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
        List<Long> list = loadData(filepath);
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
    private static List<Long> loadData(String filepath){
        File file = new File(filepath);
        List<Long> listData = null;
        try{
            listData = new ArrayList<>();
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNext()){
                listData.add(Long.parseLong(fileReader.nextLine()));
            }
            Collections.sort(listData);
        }catch (FileNotFoundException e){
            System.out.println("Something went wrong with the file. Process will exit.");
            return null;
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
    private static long sumTwoGiveMultiplicand(List<Long> numList, long requiredSum){
        long firstFactor = 0;
        long secondFactor = 0;
        for(Long number: numList){
            firstFactor=number;
            // based on principle that sum = number1 + (sum - number1)
            Long summingPartner = requiredSum - firstFactor;
            if( numList.contains(summingPartner) ){
                secondFactor=requiredSum-firstFactor;
            }
            if(secondFactor!=0){
                break;
            }
        }
        System.out.println("1st Factor = " + firstFactor + "\n2nd Factor = " + secondFactor);
        return firstFactor*secondFactor;
    }

    /**
     * Find three factors that add to requiredSum and return their product
     *
     * @param numList list of numbers to look through
     * @param requiredSum sum to look for
     * @return the product of the three factors
     */
    private static long sumThreeGiveMultiplicand(List<Long> numList, long requiredSum){
        long thirdFactor = 0;
        long innerMultiplicand= 0;
        for (int i = 0; i <numList.size() ; i++) {
            thirdFactor = numList.get(i);
            long summingPartnersSum = requiredSum - thirdFactor;
            List<Long> sublist = numList.subList(i+1,numList.size());
            innerMultiplicand = sumTwoGiveMultiplicand(sublist,summingPartnersSum);
            if(innerMultiplicand != 0){
                System.out.println("3rd Factor = " + thirdFactor);
                break;
            }
        }
        return thirdFactor * innerMultiplicand;
    }


}
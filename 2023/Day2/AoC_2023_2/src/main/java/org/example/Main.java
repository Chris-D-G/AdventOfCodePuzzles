package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static final int RED_CUBE_LIMIT =12;
    private static final int GREEN_CUBE_LIMIT =13;
    private static final int BLUE_CUBE_LIMIT =14;

    public static void main(String[] args) {
        List<String> data = parseData();
        int sum = 0;
        long power =0L;
        for(String gameData:data){
            Map<String,Long> results = countCubes(gameData);
            sum+= results.get("game");
            power+= results.get("power");
        }
        System.out.println("Game # Sum: "+sum);
        System.out.println("Game Power Sum: "+power);
    }

    private static List<String> parseData(){
        File file = new File("input.txt");
        List<String> list = new ArrayList<>();
        try(Scanner fileReader = new Scanner(file)){
            while (fileReader.hasNext()){
                list.add(fileReader.nextLine());
            }
        }catch (FileNotFoundException e){
            System.err.println("Unable to open file. System will exit now");
            System.exit(1);
        }
        return list;
    }

    private static Map<String,Long> countCubes(String str){
        Map<String, Integer> map = new HashMap<>();
        map.put("game",0);
        int previousIndex =0;
        int currentIndex = str.indexOf(":");
        String game = str.substring(previousIndex,currentIndex);
        map.put("game",Integer.parseInt(game.substring(str.indexOf("Game ")+5)));
        List<String> list = new ArrayList<>();
        while(currentIndex!=-1){
            if(str.substring(currentIndex+1).contains(";")){
                previousIndex = currentIndex+1;
                currentIndex = str.indexOf(";",previousIndex);
                list.add(str.substring(previousIndex,currentIndex).trim());
            }else{
                list.add(str.substring(currentIndex+1).trim());
                currentIndex = -1;
            }
        }
        int gameNumber=0;
        boolean validGame = true;
        int redCubeMax =0;
        int greenCubeMax =0;
        int blueCubeMax =0;

        for(String element:list){ //each element is a draw
            String[] array= element.split(", "); //for each draw split by comma to get the cube amounts
            //reset the cubes after moving to the next draw
            map.put("red",0);
            map.put("blue",0);
            map.put("green",0);
            for(String cubes: array){
                int cubeCount =Integer.parseInt(cubes.substring(0,cubes.indexOf(" ")));
                if(cubes.contains(" red")){
                    map.put("red",map.get("red")+cubeCount);
                    if(cubeCount>redCubeMax){
                        redCubeMax = cubeCount;
                    }
                }
                if(cubes.contains(" green")){
                    map.put("green",map.get("green")+cubeCount);
                    if(cubeCount>greenCubeMax){
                        greenCubeMax = cubeCount;
                    }
                }
                if(cubes.contains(" blue")){
                    map.put("blue",map.get("blue")+cubeCount);
                    if(cubeCount>blueCubeMax){
                        blueCubeMax = cubeCount;
                    }
                }
            }
            if(!checkGameResults(map)){
                validGame=false;
            }
        }
        if(validGame){
            gameNumber=map.get("game");
        }
        Map<String,Long> gamePower = new HashMap<>();
        gamePower.put("game",(long)gameNumber);
        gamePower.put("power",((long)redCubeMax*greenCubeMax*blueCubeMax));
        return gamePower;
    }

    private static boolean checkGameResults(Map<String, Integer> gameMap){
        int redCount= gameMap.get("red");
        int greenCount= gameMap.get("green");
        int blueCount= gameMap.get("blue");
        return redCount<= RED_CUBE_LIMIT && greenCount<= GREEN_CUBE_LIMIT && blueCount<= BLUE_CUBE_LIMIT;
    }

}
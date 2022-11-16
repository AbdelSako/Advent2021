package Advent2021;

import java.io.FileNotFoundException;
import  java.io.FileReader;
import java.util.*;

public class GetPositioning {

    // Day 2 Part 1
    public static void getPositioning() throws FileNotFoundException {
        FileReader directionInstructions = new FileReader("/Users/main/Projects/Advent2021/src/Advent2021/positioningNumbers.txt");
        Scanner scanner = new Scanner(directionInstructions);

        LinkedList<String> directions = new LinkedList<>();
        LinkedList<Integer> numbers = new LinkedList<>();
        while (scanner.hasNext()) {
            String directionWord = scanner.next();
            directions.add(directionWord);
            int directionNumber = scanner.nextInt();
            numbers.add(directionNumber);
        }

        int horizontalProgress = 0;
        int verticalProgress = 0;
        int i = 0;
        for (int count = 0; count < directions.size(); count++) {
            if (directions.get(i).equals("forward")) {
                horizontalProgress = horizontalProgress + numbers.get(i);
            } else if (directions.get(i).equals("up")) {
                verticalProgress = verticalProgress - numbers.get(i);
            } else if (directions.get(i).equals("down")) {
                verticalProgress = verticalProgress + numbers.get(i);
            }
            i++;
        }

        System.out.println(horizontalProgress);
        System.out.println(verticalProgress);
    }
}


package Advent2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetNumbers {
    public static void getNumbers() throws FileNotFoundException {
        int totalIncreases = 0;
        int i = 0;

        Scanner scanner = new Scanner(new File("/Users/main/Projects/Advent2021/src/Advent2021/numbersList.txt"));
        ArrayList<Integer> numberList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            numberList.add(scanner.nextInt());
        }
        System.out.println(numberList.size());
        scanner.close();

        for (int count = 1; count < numberList.size(); count++) {
            if (numberList.get(i) < numberList.get(i + 1)) {
                totalIncreases++;
            }
            i++;
        }
        System.out.println(totalIncreases);
    }
}

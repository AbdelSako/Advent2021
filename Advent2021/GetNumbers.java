package Advent2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Day 1 Part 1

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

        int previous = 0;
        int current = 1;
        int next = 2;
        int sumOfThree = 0;
        ArrayList<Integer> sumsToCompare = new ArrayList<>();
        for (int j = 0; j < numberList.size() - 2; j++) {
            sumOfThree = numberList.get(previous) + numberList.get(current) + numberList.get(next);
            sumsToCompare.add(sumOfThree);
            previous++;
            current++;
            next++;
        }
        System.out.println(sumsToCompare);
        System.out.println(sumsToCompare.size());

        totalIncreases = 0;
        i = 0;
        for (int count = 1; count < sumsToCompare.size(); count++) {
            if (sumsToCompare.get(i) < sumsToCompare.get(i + 1)) {
                totalIncreases++;
            }
            i++;
        }
        System.out.println(totalIncreases);
    }
}


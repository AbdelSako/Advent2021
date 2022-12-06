/**
 * Created by: main
 * Date: 12/6/22
 * Time: 12:21 AM
 * Project Name: Advent2021
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/


package Advent2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class HydrothermalVents {
    /* Day 5 Part 1
     * Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the
     * coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line
     * segments include the points at both ends. To avoid the most dangerous areas, you need to determine
     * the number of points where at least two lines overlap. Consider only the vertical and horizontal
     * lines for Day 1.
     */

    /* Day 5 Part 2
     * Unfortunately, considering only horizontal and vertical lines doesn't give you the full picture;
     * you need to also consider diagonal lines. At how many points do at least two lines overlap?
     */

    private static FileReader reader = null;
    private static final List<String[]> coordinates = new ArrayList<>();
    private static final int[][] chartDangerousAreas = new int[1000][1000];
    private static int countOfValuesTwoPlus = 0;

    public static void loadHydrothermalVents() {
        try {
            reader = new FileReader("/Users/main/Projects/Advent2021/src/Advent2021/hydrothermalVents.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        storeHydrothermalVentsValues();
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Reader unable to close");
        }
    }

    private static void storeHydrothermalVentsValues() {
        Scanner scanner = new Scanner(reader);
        scanner.useDelimiter("\\s");
        int i = 0;
        while (scanner.hasNextLine()) {
            String nextNumbers = scanner.next(Pattern.compile("\\d+\\D\\d+"));
            String[] split = nextNumbers.split("\\D");
            coordinates.add(i, split);
            i++;
            if (scanner.hasNextLine()) {
                scanner.skip(Pattern.compile("\\D+"));
            }
        }
        rangeToConsider();
    }

    public static void rangeToConsider() {
        System.out.println("Do you want vertical and horizontal lines only? Yes or No: ");
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.next();
        if (input.equalsIgnoreCase("Yes")) {
            getSpanOfHorizontalAndVerticalOnly();
        } else if (input.equalsIgnoreCase("No")) {
            getSpanOfAllDangerZoneLines();
        } else {
            System.out.println("Please type Yes or No");
        }
    }

    public static void getSpanOfHorizontalAndVerticalOnly() {
        for (int i = 0; i < coordinates.size(); i+=2) {
            String[] setOne = coordinates.get(i);
            String[] setTwo = coordinates.get(i + 1);
            int xOne = Integer.parseInt(setOne[0]);
            int xTwo = Integer.parseInt(setTwo[0]);
            int yOne = Integer.parseInt(setOne[1]);
            int yTwo = Integer.parseInt(setTwo[1]);

            if (xOne > xTwo && yOne == yTwo) {
                for (int j = xOne; j >= xTwo; j--) {
                    chartDangerousAreas[j][yOne] += 1;
                }
            } else if (xOne < xTwo && yOne == yTwo) {
                for (int j = xTwo; j >= xOne; j--) {
                    chartDangerousAreas[j][yOne] += 1;
                }
            } else {
                //System.out.println("Neither x is bigger.");
            }

            if (yOne > yTwo && xOne == xTwo) {
                for (int j = yOne; j >= yTwo; j--) {
                    chartDangerousAreas[xOne][j] += 1;
                }
            } else if (yOne < yTwo && xOne == xTwo) {
                for (int j = yTwo; j >= yOne; j--) {
                    chartDangerousAreas[xOne][j] += 1;
                }
            } else {
                //System.out.println("Neither y is bigger.");
            }
        }
        countArrayValues();
    }

    public static void getSpanOfAllDangerZoneLines() {
        getSpanOfHorizontalAndVerticalOnly();
        int newXOne = 0;
        int newYOne = 0;
        for (int i = 0; i < coordinates.size(); i+=2) {
            String[] setOne = coordinates.get(i);
            String[] setTwo = coordinates.get(i + 1);
            int xOne = Integer.parseInt(setOne[0]);
            int xTwo = Integer.parseInt(setTwo[0]);
            int yOne = Integer.parseInt(setOne[1]);
            int yTwo = Integer.parseInt(setTwo[1]);

            if (xOne > xTwo && yOne > yTwo){
                int slope = (yOne - yTwo) / (xOne - xTwo);
                newXOne = xOne;
                newYOne = yOne;
                System.out.println(slope);
                System.out.println("The number for xOne is " + xOne + " and yOne is " + yOne);
                for (int j = xTwo; j < xOne - 1; j++) {
                    newXOne -= slope;
                    newYOne -= slope;
                    System.out.println("newXOne is " + newXOne + " and newYOne is " + newYOne);
                    chartDangerousAreas[newXOne][newYOne] += 1;
                }
                System.out.println("The number for xTwo is " + xTwo + " and yTwo is " + yTwo);
            } else if (xOne < xTwo && yOne < yTwo) {
                int slope = (yOne - yTwo) / (xOne - xTwo);
                newXOne = xOne;
                newYOne = yOne;
                System.out.println(slope);
                System.out.println("The number for xOne is " + xOne + " and yOne is " + yOne);
                for (int j = xOne; j < xTwo - 1; j++) {
                    newXOne += slope;
                    newYOne += slope;
                    System.out.println("newXOne is " + newXOne + " and newYOne is " + newYOne);
                    chartDangerousAreas[newXOne][newYOne] += 1;
                }
                System.out.println("The number for xTwo is " + xTwo + " and yTwo is " + yTwo);
            } else if (xOne > xTwo && yOne < yTwo){
                int slope = (yOne - yTwo) / (xOne - xTwo);
                newXOne = xOne;
                newYOne = yOne;
                System.out.println(slope);
                System.out.println("The number for xOne is " + xOne + " and yOne is " + yOne);
                for (int j = xTwo; j < xOne - 1; j++) {
                    newXOne += slope;
                    newYOne -= slope;
                    System.out.println("newXOne is " + newXOne + " and newYOne is " + newYOne);
                    chartDangerousAreas[newXOne][newYOne] += 1;
                }
                System.out.println("The number for xTwo is " + xTwo + " and yTwo is " + yTwo);
            } else if (xOne < xTwo && yOne > yTwo) {
                int slope = (yTwo - yOne) / (xTwo - xOne);
                newXOne = xOne;
                newYOne = yOne;
                System.out.println(slope);
                System.out.println("The number for xOne is " + xOne + " and yOne is " + yOne);
                for (int j = xOne; j < xTwo - 1; j++) {
                    newXOne -= slope;
                    newYOne += slope;
                    System.out.println("newXOne is " + newXOne + " and newYOne is " + newYOne);
                    chartDangerousAreas[newXOne][newYOne] += 1;
                }
                System.out.println("The number for xTwo is " + xTwo + " and yTwo is " + yTwo);
            }
        }
        countArrayValues();
    }

    public static void countArrayValues() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (chartDangerousAreas[i][j] > 1) {
                    countOfValuesTwoPlus++;
                }
            }
        }
        System.out.println("The count of points with more than one intersection is " + countOfValuesTwoPlus);
    }
}

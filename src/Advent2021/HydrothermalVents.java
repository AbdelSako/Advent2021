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
    /* Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the
     * coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line
     * segments include the points at both ends. To avoid the most dangerous areas, you need to determine
     * the number of points where at least two lines overlap. Consider only the vertical and horizontal
     * lines for Day 1.
     */
    private static FileReader reader = null;
    private static final List<String[]> coordinates = new ArrayList<>();
    private static final int[][] chartDangerousAreas = new int[1000][1000];

    public static void loadHydrothermalVentsList() {
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
    }

    public static void countArrayValues() {
        int countOfValuesTwoPlus = 0;
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

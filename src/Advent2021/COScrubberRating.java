package Advent2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class COScrubberRating {
    /* Day 3 Part 2
    To find CO2 scrubber rating, determine the least common value (0 or 1) in the current
    bit position, and keep only numbers with that bit in that position. If 0 and 1 are equally
    common, keep values with a 0 in the position being considered.
     */

    public static void coScrubberRating() {
        FileReader reader = null;
        try {
            reader = new FileReader("/Users/main/Projects/Advent2021/src/Advent2021/diagnosticReport.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Scanner scanner = new Scanner(reader);
        System.out.println("File found");

        ArrayList<String> diagnosticReportNumbers = new ArrayList<>();
        while (scanner.hasNext()) diagnosticReportNumbers.add(scanner.nextLine());

        int firstIndex = 0;
        int countIndexedZeros = 0;
        int countIndexedOnes = 0;
        String currentBinaryNumber;
        while (diagnosticReportNumbers.size() > 1) {
            for (int i = 0; i < diagnosticReportNumbers.size(); i++) {
                currentBinaryNumber = diagnosticReportNumbers.get(i);
                char indexedCurrentBinaryNumber = currentBinaryNumber.charAt(firstIndex);
                if (indexedCurrentBinaryNumber == '0') {
                    countIndexedZeros++;
                } else {
                    countIndexedOnes++;
                }
            }

            //System.out.println("The count of Zeros is " + countIndexedOnes);
            //System.out.println("The count of Ones is " + countIndexedZeros);

            boolean onesIsBigger = (countIndexedZeros < countIndexedOnes);
            boolean sameAmountZerosAndOnes = (countIndexedZeros == countIndexedOnes);

            if (onesIsBigger || sameAmountZerosAndOnes) {
                for (int i = 0; i < diagnosticReportNumbers.size(); i++) {
                    currentBinaryNumber = diagnosticReportNumbers.get(i);
                    if (currentBinaryNumber.charAt(firstIndex) == '1') {
                        diagnosticReportNumbers.remove(currentBinaryNumber);
                        i -= 1;
                    }
                }
            } else {
                for (int i = 0; i < diagnosticReportNumbers.size(); i++) {
                    currentBinaryNumber = diagnosticReportNumbers.get(i);
                    if (currentBinaryNumber.charAt(firstIndex) == '0') {
                        //System.out.println("Removing this number " + diagnosticReportNumbers.get(i));
                        diagnosticReportNumbers.remove(currentBinaryNumber);
                        i -= 1;
                    } else {
                        //System.out.println("Here " + diagnosticReportNumbers.get(i));
                    }
                }
            }

            //System.out.println(diagnosticReportNumbers);

            if (diagnosticReportNumbers.size() == 1) {
                break;
            } else {
                firstIndex++;
                countIndexedOnes = 0;
                countIndexedZeros = 0;
            }
        }

        System.out.println(Integer.parseInt(diagnosticReportNumbers.get(0), 2));
    }
}


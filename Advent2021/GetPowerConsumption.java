package Advent2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class GetPowerConsumption {

// Day 3 Part 1
    public static void getPowerConsumption() throws FileNotFoundException {
        FileReader reader = new FileReader("/Users/main/Projects/Advent2021/src/Advent2021/diagnosticReport.txt");
        Scanner scanner = new Scanner(reader);
        ArrayList<String> diagnosticReportNumbers = new ArrayList<>();
        ArrayList<Integer> countOfBinaryNumberZeroInLocation = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0));
        ArrayList<Integer> countOfBinaryNumberOneInLocation = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0));

        while (scanner.hasNextLong()) {
            diagnosticReportNumbers.add(scanner.nextLine());
        }
        //System.out.println(diagnosticReportNumbers);
        //System.out.println(diagnosticReportNumbers.size());

        /* Each bit in the gamma rate can be determined by finding the most common bit in
        the corresponding position of all numbers in the diagnostic report
        */
        for (int i = 0; i < diagnosticReportNumbers.size(); i++) {
            String workingNum = diagnosticReportNumbers.get(i);
            for (int j = 0; j < workingNum.length(); j++) {
                String workingNumIndexed = workingNum.substring(j, j + 1);
                //System.out.println(workingNumIndexed);
                if (workingNumIndexed.equals("0")) {
                    countOfBinaryNumberZeroInLocation.set(j, countOfBinaryNumberZeroInLocation.get(j) + 1);
                } else if (workingNumIndexed.equals("1")) {
                    countOfBinaryNumberOneInLocation.set(j, countOfBinaryNumberOneInLocation.get(j) + 1);
                }
                //System.out.println(countOfBinaryNumberZeroInLocation.get(2));
                //System.out.println(countOfBinaryNumberOneInLocation.get(2));
            }
        }

        int j = 0;
        StringBuilder binaryGammaRateSB;
        String binaryGammaRate = null;
        StringBuilder gammaBuilder = new StringBuilder();
        ArrayList<Integer> binaryGammaNumbers = new ArrayList<>();
        for (int i = 0; i < countOfBinaryNumberZeroInLocation.size(); i++) {
            if (countOfBinaryNumberZeroInLocation.get(j) > countOfBinaryNumberOneInLocation.get(j)) {
                binaryGammaNumbers.add(0);
            } else if (countOfBinaryNumberZeroInLocation.get(j) < countOfBinaryNumberOneInLocation.get(j)) {
                binaryGammaNumbers.add(1);
            }
            j++;
            //System.out.print(binaryGammaNumbers.get(i));
            binaryGammaRateSB = gammaBuilder.append(binaryGammaNumbers.get(i));
            binaryGammaRate = binaryGammaRateSB.toString();
        }
        //System.out.println(binaryGammaRate);

        /* Each bit in the epsilon rate can be determined by finding the least common bit in
        the corresponding position of all numbers in the diagnostic report
        */
        int k = 0;
        StringBuilder binaryEpsilonRateSB;
        String binaryEpsilonRate = null;
        StringBuilder stringBuilder2 = new StringBuilder();
        ArrayList<Integer> binaryEpsilonNumbers = new ArrayList<>();
        for (int i = 0; i < countOfBinaryNumberZeroInLocation.size(); i++) {
            if (countOfBinaryNumberZeroInLocation.get(k) < countOfBinaryNumberOneInLocation.get(k)) {
                binaryEpsilonNumbers.add(0);
            } else if (countOfBinaryNumberZeroInLocation.get(k) > countOfBinaryNumberOneInLocation.get(k)) {
                binaryEpsilonNumbers.add(1);
            }
            k++;
            //System.out.print(binaryGammaNumbers.get(i));
            binaryEpsilonRateSB = stringBuilder2.append(binaryEpsilonNumbers.get(i));
            binaryEpsilonRate = binaryEpsilonRateSB.toString();
        }

        System.out.println("The binary gamma rate is " + binaryGammaRate);
        System.out.println("The binary epsilon rate is " + binaryEpsilonRate);

        int decimalGammaRate = Integer.parseInt(binaryGammaRate, 2);
        int decimalEpsilonRate = Integer.parseInt(binaryEpsilonRate, 2);
        System.out.println("The decimal gamma rate is " + decimalGammaRate);
        System.out.println("The decimal epsilon rate is " + decimalEpsilonRate);

        int powerConsumption = decimalEpsilonRate * decimalGammaRate;
        System.out.println("The power consumption is " + powerConsumption);
    }
}

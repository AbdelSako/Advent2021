/**
 * Created by: main
 * Date: 11/28/22
 * Time: 7:54 AM
 * Project Name: Advent2021
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/

package Advent2021;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayBingo {
    //add bingo card lists to an array
    static Scanner scanner = null;
    private static List<String> bingoCardRow = new ArrayList<>(
            Arrays.asList("0", "1", "2", "3", "4"));
    private static List<String> bingoWholeCard = new ArrayList<>();
    private static List<String> numbersCalledForBingo = Arrays.asList();
    private static int i = 0;


    public static void loadBingoList() {
        FileReader reader = null;

        try {
            reader = new FileReader("/Users/main/Projects/Advent2021/src/Advent2021/bingoNumberList.txt");
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("File not found");
        }

        if (reader != null) {
            scanner = new Scanner(reader);
            callBingoNumbers("^((^(?!,)|(?!^),)(\\d+))+$", scanner.nextLine());
            while (scanner.hasNext()) {
                createBingoCards("\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+", scanner.nextLine());
            }
        }
    }

    static void createBingoCards(String regexCards, String nextLine) {
        boolean isBingoLine = false;
        Pattern fiveNumberFormat = Pattern.compile(regexCards);
        Matcher matcher = fiveNumberFormat.matcher(nextLine);

        while (matcher.find()) {
            //System.out.println("Matcher found " + matcher.group());
            //System.out.println("Iteration " + i);
            String bingoLine = matcher.group();
            bingoCardRow.set(i, bingoLine);
            i++;
            System.out.println(bingoCardRow);
            isBingoLine = true;
            //System.out.println("Did this increment? : " + i);
            int stepSize = 5;
            if (i % stepSize == 0) {
                bingoWholeCard.add(0, bingoCardRow.subList(0, 5).toString());
                System.out.println(bingoWholeCard);
                i = 0;
            }
        }

        if (!isBingoLine) {
            System.out.println("No match");
        }

        //System.out.println(bingoWholeCard.size());
    }

    static void callBingoNumbers(String regexNumbers, String nextLine) {
        boolean isBingoNumbers = false;
        Pattern lotsOfNumbers = Pattern.compile(regexNumbers);
        Matcher matcher = lotsOfNumbers.matcher(nextLine);

        while (matcher.find()) {
            String lineOfBingoNumbers = matcher.group();
            System.out.println(lineOfBingoNumbers);
            numbersCalledForBingo = List.of((lineOfBingoNumbers.split(",", -1)));
            System.out.println(numbersCalledForBingo);
        }
    }
}


    //compare numbers in array list to the row and column to see if there is a match

    //if card has a bingo, The score of the winning board can now be calculated.
    // Start by finding the sum of all unmarked numbers on that board.
    // Then, multiply that sum by the number that was just called when the board won.


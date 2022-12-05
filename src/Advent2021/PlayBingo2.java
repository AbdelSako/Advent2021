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

public class PlayBingo2 {
    static Scanner cardScanner = null;
    static Scanner numberScanner = null;
    private static List<String> numbersCalledForBingo = new ArrayList<>();
    private static FileReader bingoNumbersCalled = null;
    private static FileReader bingoCardList = null;
    private static List<String> allBingoCards = new ArrayList<>();
    private static String[][] individualBingoCard = new String[5][5];
    static int cardCount = 1;


    public static void loadBingoList() {
        try {
            bingoNumbersCalled = new FileReader("/Users/main/Projects/Advent2021/src/Advent2021/bingoNumbersCalled2.txt");
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Bingo Numbers Called not found");
        }

        try {
            bingoCardList = new FileReader("/Users/main/Projects/Advent2021/src/Advent2021/bingoNumberList2");
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Bingo Card List not found");
        }
    }

    public static void loadNumbersCalled() {
        if (bingoNumbersCalled != null) {
            numberScanner = new Scanner(bingoNumbersCalled);
            while (numberScanner.hasNext()) {
                numberScanner.useDelimiter(",");
                String bingoNumber = numberScanner.next();
                numbersCalledForBingo.add(bingoNumber);
                //System.out.println(numbersCalledForBingo);
            }
        }
    }

    public static void checkBingoCards() {
        //int countOfBingoCardIterations = 0;

        if (bingoCardList != null) {
            cardScanner = new Scanner(bingoCardList);
            cardScanner.useDelimiter("\\s+|\\n");
            while (cardScanner.hasNext()) {

                for (int rowOfCard = 0; rowOfCard < 5; rowOfCard++) {
                    for (int columnOfCard = 0; columnOfCard < 5; columnOfCard++) {
                        String nextBingoNumber = cardScanner.next("\\d+");
                        individualBingoCard[rowOfCard][columnOfCard] = nextBingoNumber;
                    }
                }
                iterateThroughEachCard();
            }
        }
    }

    public static void iterateThroughEachCard() {
        //this is checking one individual card for matches number by number
        for (int pullNextNumber = 0; pullNextNumber < numbersCalledForBingo.size(); pullNextNumber++) {
            for (int rowInCard = 0; rowInCard < 5; rowInCard++) {
                for (int colInCard = 0; colInCard < 5; colInCard++){
                    if (individualBingoCard[rowInCard][colInCard].equals(numbersCalledForBingo.get(pullNextNumber))) {
                        System.out.println("Matched " + numbersCalledForBingo.get(pullNextNumber) +
                                " at " + "(" + rowInCard + "," + colInCard + ") with the " +
                                (pullNextNumber + 1) + " number called.");
                    }
                }
            }
        }
        System.out.println("Current Card is " + cardCount);
        cardCount++;
        System.out.println("Next Card is " + cardCount);
    }

}


//if card has a bingo, The score of the winning board can now be calculated.
// Start by finding the sum of all unmarked numbers on that board.
// Then, multiply that sum by the number that was just called when the board won.


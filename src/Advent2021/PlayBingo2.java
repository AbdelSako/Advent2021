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
    private static Scanner cardScanner = null;
    private static Scanner numberScanner = null;
    private static FileReader bingoNumbersCalled = null;
    private static FileReader bingoCardList = null;
    private static List<String> numbersCalledForBingo = new ArrayList<>();
    private static String[][] individualBingoCard = new String[5][5];
    private static String[][] previousBingoCard = new String[5][5];
    private static String[][] yesOrNoBingo = new String[5][5];
    private static int cardCount = 1;
    private static int countToBingo = 1;
    private static int previousCountToBingo = 1;


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
        setPreviousBingoCard();
        if (bingoCardList != null) {
            cardScanner = new Scanner(bingoCardList);
            cardScanner.useDelimiter("\\s+|\\n");
            while (cardScanner.hasNext()) {

                for (int rowOfCard = 0; rowOfCard < 5; rowOfCard++) {
                    for (int columnOfCard = 0; columnOfCard < 5; columnOfCard++) {
                        String nextBingoNumber = cardScanner.next("\\d+");
                        individualBingoCard[rowOfCard][columnOfCard] = nextBingoNumber;
                        yesOrNoBingo[rowOfCard][columnOfCard] = "No";
                    }
                }
                iterateThroughEachCard();
            }
        }
    }

    private static void iterateThroughEachCard() {
        //this is checking one individual card for matches number by number
        outerLoop: for (int pullNextNumber = 0; pullNextNumber < numbersCalledForBingo.size(); pullNextNumber++) {
            for (int rowInCard = 0; rowInCard < 5; rowInCard++) {
                for (int colInCard = 0; colInCard < 5; colInCard++){
                    if (individualBingoCard[rowInCard][colInCard].equals(numbersCalledForBingo.get(pullNextNumber))) {
                        //System.out.println("Matched " + numbersCalledForBingo.get(pullNextNumber) +
                        //        " at " + "(" + rowInCard + "," + colInCard + ") with the " +
                        //        (pullNextNumber + 1) + " number called.");

                        yesOrNoBingo[rowInCard][colInCard] = "Yes";
                        //System.out.println(Arrays.deepToString(yesOrNoBingo));
                        //System.out.println(Arrays.deepToString(individualBingoCard));

                        if (isBingo()) {
                            //System.out.println("Bingo! at " + (pullNextNumber + 1));
                            countToBingo = pullNextNumber + 1;
                            //System.out.println("Previous count to Bingo; " + previousCountToBingo);
                            //System.out.println("Current count to Bingo: " + countToBingo);
                            compareCards();
                            break outerLoop;
                        }
                    }

                }
            }
        }
        System.out.println("Current Card is " + cardCount);
        cardCount++;
        //System.out.println("Next Card is " + cardCount);
    }

    private static void setPreviousBingoCard() {
        for (int rowOfCard = 0; rowOfCard < 5; rowOfCard++) {
            for (int colOfCard = 0; colOfCard < 5; colOfCard++) {
                previousBingoCard[rowOfCard][colOfCard] = "Empty";
            }
        }
    }

    private static boolean isBingo() {
        for (int x = 0; x < 5; x++) {
            if ("Yes".equals(yesOrNoBingo[x][0]) && "Yes".equals(yesOrNoBingo[x][1]) &&
                    "Yes".equals(yesOrNoBingo[x][2]) && "Yes".equals(yesOrNoBingo[x][3]) &&
                    "Yes".equals(yesOrNoBingo[x][4])) {
                return true;
            }
        }
        for (int y = 0; y < 5; y++) {
            if ("Yes".equals(yesOrNoBingo[0][y]) && "Yes".equals(yesOrNoBingo[1][y]) &&
                    "Yes".equals(yesOrNoBingo[2][y]) && "Yes".equals(yesOrNoBingo[3][y]) &&
                    "Yes".equals(yesOrNoBingo[4][y])) {
                return true;
            }
        }
        return false;
    }

    private static void compareCards() {
        if (previousCountToBingo == 1 || countToBingo < previousCountToBingo) {
            System.out.println("Previous winner: " + Arrays.deepToString(previousBingoCard));
            System.out.println("Current winner: " + Arrays.deepToString(individualBingoCard));
            for (int i = 0; i < individualBingoCard.length; i++) {
                System.arraycopy(individualBingoCard[i], 0, previousBingoCard[i], 0,
                        previousBingoCard[0].length);
            }
            System.out.println("Current previous winner: " + Arrays.deepToString(previousBingoCard));
            previousCountToBingo = countToBingo;
        }
    }
}


//if card has a bingo, The score of the winning board can now be calculated.
// Start by finding the sum of all unmarked numbers on that board.
// Then, multiply that sum by the number that was just called when the board won.


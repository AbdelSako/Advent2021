/**
 * Created by: main
 * Date: 11/28/22
 * Time: 7:54 AM
 * Project Name: Advent2021
 * Email: altrembl@amazon.com
 * Slack: altrembl
 **/

package Advent2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class PlayBingo2 {
    /* Day 4 Part 1
     * If all numbers in any row or any column of a board are marked, that board wins. (Diagonals don't count.)
     * The score of the winning board can now be calculated. Start by finding the sum of all unmarked numbers
     * on that board. Then, multiply that sum by the number that was just called when the board won.
     */
    private static FileReader bingoNumbersCalled = null;
    private static FileReader bingoCardList = null;
    private static final List<String> numbersCalledForBingo = new ArrayList<>();
    private static final String[][] individualBingoCard = new String[5][5];
    private static final String[][] previousBingoCard = new String[5][5];
    private static final String[][] previousLoseWinningBingoCard = new String[5][5];
    private static final String[][] yesOrNoBingo = new String[5][5];
    private static String[][] previousYesOrNoBingo = new String[5][5];
    private static int countToBingo = 1;
    private static int previousCountToBingo = 1;
    private static int winningNumber = 0;
    private static int loseNumber = 0;
    private static String desireToWin = "Not set";
    //private static int cardCount = 1;


    private static void loadBingoList() {
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

    private static void loadNumbersCalled() {
        if (bingoNumbersCalled != null) {
            Scanner numberScanner = new Scanner(bingoNumbersCalled);
            while (numberScanner.hasNext()) {
                numberScanner.useDelimiter(",");
                String bingoNumber = numberScanner.next();
                numbersCalledForBingo.add(bingoNumber);
            }
        }
    }

    private static void doYouWantToWin() {
        Scanner wantToWin = new Scanner(System.in);
        System.out.println("What would you like to do? Please enter Win or Lose: ");
        desireToWin = wantToWin.nextLine();
        if (desireToWin.equalsIgnoreCase("Win")) {
            desireToWin = "Win";
        } else if (desireToWin.equalsIgnoreCase("Lose")) {
            desireToWin = "Lose";
        } else {
            System.out.println("Please enter Win or Lose.");
            doYouWantToWin();
        }
    }

    public static void checkBingoCards() {
        loadBingoList();
        loadNumbersCalled();
        doYouWantToWin();
        setPreviousBingoCard();
        if (bingoCardList != null) {
            Scanner cardScanner = new Scanner(bingoCardList);
            cardScanner.useDelimiter("\\s+|\\n");
            while (cardScanner.hasNext()) {
                for (int rowOfCard = 0; rowOfCard < 5; rowOfCard++) {
                    for (int columnOfCard = 0; columnOfCard < 5; columnOfCard++) {
                        String nextBingoNumber = cardScanner.next("\\d+");
                        individualBingoCard[rowOfCard][columnOfCard] = nextBingoNumber;
                        yesOrNoBingo[rowOfCard][columnOfCard] = "No";
                    }
                }
                if (desireToWin.equals("Win")) {
                    iterateThroughEachCard();
                } else if (desireToWin.equals("Lose")) {
                    iterateThroughEachCardToLose();
                } else {
                    System.out.println("Desire to win not set.");
                }
            }
        }
    }

    private static void iterateThroughEachCard() {
        outerLoop:
        for (int pullNextNumber = 0; pullNextNumber < numbersCalledForBingo.size(); pullNextNumber++) {
            for (int rowInCard = 0; rowInCard < 5; rowInCard++) {
                for (int colInCard = 0; colInCard < 5; colInCard++) {
                    if (individualBingoCard[rowInCard][colInCard].equals(numbersCalledForBingo.get(pullNextNumber))) {
                        yesOrNoBingo[rowInCard][colInCard] = "Yes";
                        if (isBingo()) {
                            winningNumber = Integer.parseInt(numbersCalledForBingo.get(pullNextNumber));
                            countToBingo = pullNextNumber + 1;
                            compareCards();
                            break outerLoop;
                        }
                    }
                }
            }
        }
    }

    private static void iterateThroughEachCardToLose() {
        outerLoop:
        for (int pullNextNumber = 0; pullNextNumber < numbersCalledForBingo.size(); pullNextNumber++) {
            for (int rowInCard = 0; rowInCard < 5; rowInCard++) {
                for (int colInCard = 0; colInCard < 5; colInCard++) {
                    if (individualBingoCard[rowInCard][colInCard].equals(numbersCalledForBingo.get(pullNextNumber))) {
                        yesOrNoBingo[rowInCard][colInCard] = "Yes";
                        if (isBingo()) {
                            loseNumber = Integer.parseInt(numbersCalledForBingo.get(pullNextNumber));
                            countToBingo = pullNextNumber + 1;
                            compareCardsToLose();
                            break outerLoop;
                        }
                    }
                }
            }
        }
    }

    private static void setPreviousBingoCard() {
        for (int rowOfCard = 0; rowOfCard < 5; rowOfCard++) {
            for (int colOfCard = 0; colOfCard < 5; colOfCard++) {
                previousBingoCard[rowOfCard][colOfCard] = "Empty";
                previousLoseWinningBingoCard[rowOfCard][colOfCard] = "Empty";
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
            previousCountToBingo = countToBingo;
            int previousWinningNumber = winningNumber;
            System.out.println("Previous winning number: " + previousWinningNumber);
            previousYesOrNoBingo = yesOrNoBingo;
            calculateWinningBoard();
        }
    }

    private static void compareCardsToLose () {
        if (previousCountToBingo == 1 || countToBingo > previousCountToBingo) {
            System.out.println("Previous losing winner: " + Arrays.deepToString(previousLoseWinningBingoCard));
            System.out.println("Current losing winner: " + Arrays.deepToString(individualBingoCard));
            for (int i = 0; i < individualBingoCard.length; i++) {
                System.arraycopy(individualBingoCard[i], 0, previousLoseWinningBingoCard[i], 0,
                        previousLoseWinningBingoCard[0].length);
            }
            previousCountToBingo = countToBingo;
            int previousLosingNumber = loseNumber;
            System.out.println("Previous losing winning number: " + previousLosingNumber);
            previousYesOrNoBingo = yesOrNoBingo;
            calculateLosingBoard();
        }
    }

    private static void calculateWinningBoard () {
        long sumUnmarkedNumbers = 0L;
        for (int rowOfCard = 0; rowOfCard < 5; rowOfCard++) {
            for (int colOfCard = 0; colOfCard < 5; colOfCard++) {
                if (previousYesOrNoBingo[rowOfCard][colOfCard].equals("No")) {
                    sumUnmarkedNumbers += Integer.parseInt(previousBingoCard[rowOfCard][colOfCard]);
                }
            }
        }
        sumUnmarkedNumbers *= winningNumber;
        System.out.println("Winning board number: " + sumUnmarkedNumbers);
    }

    /* Day 4 Part 2
    * Figure out which board will win last and choose that one.
    */
    private static void calculateLosingBoard () {
        long sumUnmarkedNumbers = 0L;
        for (int rowOfCard = 0; rowOfCard < 5; rowOfCard++) {
            for (int colOfCard = 0; colOfCard < 5; colOfCard++) {
                if (previousYesOrNoBingo[rowOfCard][colOfCard].equals("No")) {
                    sumUnmarkedNumbers += Integer.parseInt(previousLoseWinningBingoCard[rowOfCard][colOfCard]);
                }
            }
        }
        sumUnmarkedNumbers *= loseNumber;
        System.out.println("Losing board number: " + sumUnmarkedNumbers);
    }
}

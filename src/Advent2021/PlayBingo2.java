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

    public static void loadBingoCards() {
        String[][] individualBingoCard = new String[5][5];
        List<String> allBingoCards = new ArrayList<>();
        int countOfBingoCardIterations = 0;

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

                //System.out.println(Arrays.deepToString(individualBingoCard));
                allBingoCards.add(countOfBingoCardIterations, Arrays.deepToString(individualBingoCard));
                //System.out.println(allBingoCards);
                countOfBingoCardIterations++;
            }
        }
        //System.out.println(countOfBingoCardIterations);
    }

    public static void
}

/*                for (int initialize = 0; initialize < 100; initialize++) {
                    allBingoCards.add(initialize, );
                }

                allBingoCards.add(bingoCardCount, individualBingoCard);
                bingoCardCount++;
                System.out.println(allBingoCards.size());
                *//*for (int i = 0; i < allBingoCards.size(); i ++) {
                    String[][] listOfCards = allBingoCards.get(i);
                    System.out.println(listOfCards);
                }*//*
            }
        }

    }
}*/


//if card has a bingo, The score of the winning board can now be calculated.
// Start by finding the sum of all unmarked numbers on that board.
// Then, multiply that sum by the number that was just called when the board won.


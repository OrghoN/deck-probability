package stats;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author James Cannon
 * @version 18 July 2016 - 3:45 P.M.
 */
public class Stats {

    private static final double SIMULATIONS = 1000;
    private static double perfectGame = 0;//number of perfect games
    private static double perfectHand = 0;//number of perfect hands
    private static final int[] MULLS = new int[6];//array for keeping track of mulligans

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Deck.initLists();

        for (int i = 0; i < SIMULATIONS; i++) {//loop the simulations
            Deck.initGame();//(re)set the game
            Deck.shuffle();//randomize the deck
            Deck.draw(7);//move 7 cards from the deck to the hand
            Deck.resolveMulligans();//resolve mulligans
            MULLS[Deck.scry()]++;//add stats to MULLS array and scry if necessary
            /*scry() returns an int equal to 7-hand.size(). Thus, if the simulation
            required one mulligan, the hand size would be six and scry() returns 1.
            MULLS[] keeps track of the number of mulls and shows the difference between
            a mull to 6 and a mull to 3. A keep at 7 goes to MULLS[0].*/

            if (Deck.pHand()) {
                /*The initial if statement calls handCheck, hContainsLand, and hContains Fetch
                per specifications of the situation. In this case, the hand must contain
                INOPENING, at least 2 lands, and at least 1 fetch land. If it does, the
                variable for recording perfect opening hands, increments, otherwise,
                the simulations is treated as a failure and a new simulation begins
                at the top of the loop.*/
                perfectHand++;
//                System.out.println("Perfect Hand");
//                System.out.println("Before play: " + Deck.HAND + "\nDeck: " + Deck.DECK);
                Deck.play(1);//play() accounts for fetch lands and other cards that interact with the deck on T1
//                System.out.println("Ater play: " + Deck.HAND + "\nDeck: " + Deck.DECK);
                Deck.draw(1);//T2 begins with a draw
//                System.out.println("After Draw: " + Deck.HAND + "\nDeck: " + Deck.DECK + "\n\n");
                if (Deck.pGame()) {
                    /*The secondary if statement checks if the hand contains the
                    required sequence of cards for the second turn. In this case, it
                    checks for FIRSTDRAW and 2 fetch lands. If it has the required
                    cards, the variable for recording a perfect game is incremented.*/
                    perfectGame++;
//                    System.out.println("Perfect Game");
                }
            } else {
                Deck.play(1);
                Deck.draw(1);
            }
            Deck.play(2);
            Deck.draw(1);
            Deck.play(3);
            Deck.draw(1);
            Deck.play(4);

        }//for
        int total = 0;
        for (int i = 0; i < MULLS.length; i++) {
            total += MULLS[i];
        }
        //output display
        System.out.println(perfectHand + " perfect hands");
        System.out.println(perfectGame + " perfect games");
        System.out.printf("%.3f percent of hands mulled to 6\t", ((MULLS[1]) / SIMULATIONS * 100));
        System.out.printf("%.3f percent of hands mulled to 5\t", ((MULLS[2]) / SIMULATIONS * 100));
        System.out.printf("%.3f percent of hands mulled to 4\n", ((MULLS[3]) / SIMULATIONS * 100));
        System.out.printf("%.3f percent of hands mulled to 3\t", ((MULLS[4]) / SIMULATIONS * 100));
        System.out.printf("%.3f percent of hands mulled to 2 or less\n", (MULLS[5] / SIMULATIONS * 100));
        System.out.printf("%.3f percent of hands were perfect\n", (perfectHand / SIMULATIONS * 100));
        System.out.printf("%.3f percent of games were perfect\n", (perfectGame / SIMULATIONS * 100));
    }

}

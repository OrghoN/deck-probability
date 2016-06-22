package stats;

import java.util.ArrayList;

/**
 *
 * @author James Cannon
 * @version 22 June 2016 - 2:53 P.M.
 */
public class Stats {

    private static final double SIMULATIONS = 1000000;
    private static double perfectGame = 0;//number of perfect games
    private static double perfectHand = 0;//number of perfect hands
    private static final ArrayList<String> INOPENING = new ArrayList<>();//List of cards required in opening
    private static final ArrayList<String> FIRSTDRAW = new ArrayList<>();//List of cards required in first draw
    private static final int[] MULLS = new int[6];//array for keeping track of mulligans

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        INOPENING.add("Steppe Lynx");//init list cards required
        FIRSTDRAW.add("Atarka's Command");
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

            if (Deck.HAND.containsAll(INOPENING) && Deck.containsCard(Deck.HAND, Deck.ALL_LAND, 2, 6)
                    && Deck.containsCard(Deck.HAND, Deck.FETCH_LANDS, 1, 6)
                    && (Deck.containsCard(Deck.HAND, Deck.W_SHOCKS, 1, 6)
                    || Deck.containsCard(Deck.HAND, Deck.FETCH_LANDS, 2, 6))) {
                /*The initial if statement calls handCheck, hContainsLand, and hContains Fetch
                per specifications of the situation. In this case, the hand must contain
                INOPENING, at least 2 lands, and at least 1 fetch land. If it does, the
                variable for recording perfect opening hands, increments, otherwise,
                the simulations is treated as a failure and a new simulation begins
                at the top of the loop.*/
                perfectHand++;
//                System.out.println("Perfect Hand");
                Deck.play(0);//play() accounts for fetch lands and other cards that interact with the deck on T1
                Deck.draw(1);//T2 begins with a draw
                if (Deck.HAND.containsAll(FIRSTDRAW) && Deck.containsCard(Deck.HAND, Deck.FETCH_LANDS, 2, 6)) {
                    /*The secondary if statement checks if the hand contains the
                    required sequence of cards for the second turn. In this case, it
                    checks for FIRSTDRAW and 2 fetch lands. If it has the required
                    cards, the variable for recording a perfect game is incremented.*/
                    perfectGame++;
//                    System.out.println("Perfect Game");
                }
            }

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

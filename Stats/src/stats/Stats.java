package stats;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author James Cannon
 * @version 29 December 2016 - 3:00 P.M.
 */
public class Stats {

    private static final double SIMULATIONS = 1000000;
    private static double perfectGame = 0;//number of perfect games
    private static double perfectHand = 0;//number of perfect hands
    private static final int[] MULLS = new int[6];//array for keeping track of mulligans
    private static final int TURNS = 4;//number of turns the simulation runs for
    private static final int[] DMG = new int[11];//array for keeping track of damage totals
    private static final int[] MANA = new int[TURNS + 3]; // array for keeping track of lands
    private static final int CRITICAL = 17;
    private static double DAMAGE = 0;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
	Deck.initLists();
	int damage;
	int win = 0;

	for (int i = 0; i < SIMULATIONS; i++) {//loop the simulations
	    damage = 0;
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
		damage += Deck.play(1);//play() accounts for fetch lands and other cards that interact with the deck on T1
		if (Deck.pGame()) {
		    /*The secondary if statement checks if the hand contains the
                    required sequence of cards for the second turn. In this case, it
                    checks for FIRSTDRAW and 2 fetch lands. If it has the required
                    cards, the variable for recording a perfect game is incremented.*/
		    perfectGame++;
		}
	    } else {
		damage += Deck.play(1);
	    }
	    for (int turn = 2; turn <= TURNS; turn++) {
		damage += Deck.play(turn);
	    }
		
//	    for (int turn_tracker = 2; damage <=20; turn_tracker++){ //optional calculation; how many turns until 20 damage.
//		    damage += Deck.play(turn_tracker);
//	    }
		
	    int var = damage / 5;
	    if ( var >9) {
		DMG[10]++;
	    } else DMG[var]++;
		
	    if (damage>=CRITICAL){
		win++;
	    }
	    
	    DAMAGE += damage;
	    MANA[Deck.calcMana()]++;

	}//for
	    
	int total = 0;
	for (int i = 0; i < MULLS.length; i++) {
	    total += MULLS[i];
	}
	//output display
	System.out.println(perfectHand + " perfect hands");
	System.out.println(perfectGame + " perfect games");
	System.out.printf("%.3f percent of hands kept at 7\t", ((MULLS[0]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of hands mulled to 6\t", ((MULLS[1]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of hands mulled to 5\n", ((MULLS[2]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of hands mulled to 4\t", ((MULLS[3]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of hands mulled to 3\t", ((MULLS[4]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of hands mulled to 2 or less\n", (MULLS[5] / SIMULATIONS * 100));
	System.out.printf("%.3f percent of hands were perfect\n", (perfectHand / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games were perfect\n", (perfectGame / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 6 land\t", ((MANA[6]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 5 land\t", ((MANA[5]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 4 land\t", ((MANA[4]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 3 land\n", ((MANA[3]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 2 land\t", ((MANA[2]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 1 land\t", ((MANA[1]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 0 land\n\n", ((MANA[0]) / SIMULATIONS * 100));
	System.out.printf("Average damage over %.0f games is %.3f\n", SIMULATIONS, DAMAGE / SIMULATIONS);
	System.out.printf("%.3f percent of games were above 17 damage\n", (win/SIMULATIONS*100));
	System.out.printf("%.3f percent of games had 0-4 threatened damage\t", ((DMG[0]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 5-9 threatened damage\n", ((DMG[1]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 10-14 threatened damage\t", ((DMG[2]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 15-19 threatened damage\n", ((DMG[3]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 20-24 threatened damage\t", ((DMG[4]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 25-30 threatened damage\n", ((DMG[5]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 30-34 threatened damage\t", ((DMG[6]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 35-39 threatened damage\n", ((DMG[7]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 40-44 threatened damage\t", ((DMG[8]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 45-49 threatened damage\n", ((DMG[9]) / SIMULATIONS * 100));
	System.out.printf("%.3f percent of games had 50+ threatened damage\t", ((DMG[10]) / SIMULATIONS * 100));
    }

}

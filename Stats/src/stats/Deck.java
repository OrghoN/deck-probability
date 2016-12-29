package stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author James Cannon
 * @version 29 December 2016 3:00 P.M.
 */
public class Deck {

    private static final Random RNG = new Random();//used in shuffle()
    public static final List<String> DECK = new ArrayList<>();//ArrayList for Deck
    public static final List<String> DECK_INITIAL = new ArrayList<>();//ArrayList for DecK Initial
    public static final List<String> HAND = new ArrayList<>();//ArrayList for Hand
    public static final List<String> FETCH_LANDS = new ArrayList<>();
    public static final List<String> W_SHOCKS = new ArrayList<>();
    public static final List<String> U_SHOCKS = new ArrayList<>();
    public static final List<String> B_SHOCKS = new ArrayList<>();
    public static final List<String> R_SHOCKS = new ArrayList<>();
    public static final List<String> G_SHOCKS = new ArrayList<>();
    public static final List<String> W_LANDS = new ArrayList<>();
    public static final List<String> U_LANDS = new ArrayList<>();
    public static final List<String> B_LANDS = new ArrayList<>();
    public static final List<String> G_LANDS = new ArrayList<>();
    public static final List<String> R_LANDS = new ArrayList<>();
    public static final List<String> WU_LANDS = new ArrayList<>();
    public static final List<String> WB_LANDS = new ArrayList<>();
    public static final List<String> WR_LANDS = new ArrayList<>();
    public static final List<String> WG_LANDS = new ArrayList<>();
    public static final List<String> UB_LANDS = new ArrayList<>();
    public static final List<String> UR_LANDS = new ArrayList<>();
    public static final List<String> UG_LANDS = new ArrayList<>();
    public static final List<String> BR_LANDS = new ArrayList<>();
    public static final List<String> BG_LANDS = new ArrayList<>();
    public static final List<String> RG_LANDS = new ArrayList<>();
    public static final List<String> CREATURES = new ArrayList<>();
    public static final List<String> R_CREATURES = new ArrayList<>();
    public static final List<String> G_CREATURES = new ArrayList<>();
    public static final List<String> W_CREATURES = new ArrayList<>();
    public static final List<String> H_CREATURES = new ArrayList<>();//haste creatures
    public static final List<String> ALL_LAND = new ArrayList<>();
    public static final List<String> SHOCK_LANDS = new ArrayList<>();
    public static final List<String> BASIC_LANDS = new ArrayList<>();
    public static final List<String> PLAINS = new ArrayList<>();
    public static final List<String> ISLANDS = new ArrayList<>();
    public static final List<String> SWAMPS = new ArrayList<>();
    public static final List<String> MOUNTAINS = new ArrayList<>();
    public static final List<String> FORESTS = new ArrayList<>();
    public static final List<String> FIELD = new ArrayList<>();
    public static final List<Integer> POWER = new ArrayList<>();
    private static final ArrayList<String> INOPENING = new ArrayList<>();//List of cards required in opening
    private static final ArrayList<String> FIRSTDRAW = new ArrayList<>();//List of cards required in first draw
    private static final ArrayList<Integer> EVOLVE_COUNTERS = new ArrayList<>();//+1/+1 counters to go along with each element of field.
    public static boolean initDeck = false;
    public static int W_MANA = 0;
    public static int U_MANA = 0;
    public static int B_MANA = 0;
    public static int R_MANA = 0;
    public static int G_MANA = 0;
    public static int WU_MANA = 0;
    public static int WB_MANA = 0;
    public static int WR_MANA = 0;
    public static int WG_MANA = 0;
    public static int UB_MANA = 0;
    public static int UR_MANA = 0;
    public static int UG_MANA = 0;
    public static int BR_MANA = 0;
    public static int BG_MANA = 0;
    public static int RG_MANA = 0;
    public static int COLORLESS_MANA = 0;
    public static int extrdmg = 0;
    public static int MANA = 0;
    public static int CLEANUP = 0;
    public static int LANDFALL = 0;
    public static boolean SURGE = false;

    /**
     * TODO Comment code
     *
     * @throws FileNotFoundException
     */
    static void initLists() throws FileNotFoundException {
	INOPENING.add("Steppe Lynx");//init list cards required
	FIRSTDRAW.add("Atarka's Command");
	Scanner scan = new Scanner(new File("Card Definitions.txt"));
	String[] line;
	while (scan.hasNextLine()) {
	    line = scan.nextLine().split("\t");
	    if (line.length != 2) {
		continue;
	    }
	    line[0] = line[0].toLowerCase();
	    switch (line[0]) {
		case "fetch land": {
		    FETCH_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "white shock": {
		    PLAINS.add(line[1]);
		    W_SHOCKS.add(line[1]);
		    SHOCK_LANDS.add(line[1]);
		    W_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "blue shock": {
		    ISLANDS.add(line[1]);
		    U_SHOCKS.add(line[1]);
		    SHOCK_LANDS.add(line[1]);
		    U_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "black shock": {
		    SWAMPS.add(line[1]);
		    B_SHOCKS.add(line[1]);
		    SHOCK_LANDS.add(line[1]);
		    B_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "red shock": {
		    MOUNTAINS.add(line[1]);
		    R_SHOCKS.add(line[1]);
		    SHOCK_LANDS.add(line[1]);
		    R_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "green shock": {
		    FORESTS.add(line[1]);
		    G_SHOCKS.add(line[1]);
		    SHOCK_LANDS.add(line[1]);
		    G_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "plains": {
		    PLAINS.add(line[1]);
		    W_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "islands": {
		    ISLANDS.add(line[1]);
		    U_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "swamps": {
		    SWAMPS.add(line[1]);
		    B_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "mountains": {
		    MOUNTAINS.add(line[1]);
		    R_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "forests": {
		    FORESTS.add(line[1]);
		    G_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "basic land": {
		    BASIC_LANDS.add(line[1]);
		    ALL_LAND.add(line[1]);
		    break;
		}
		case "white creature": {
		    W_CREATURES.add(line[1]);
		    CREATURES.add(line[1]);
		    break;
		}
		case "red creature": {
		    R_CREATURES.add(line[1]);
		    CREATURES.add(line[1]);
		    break;
		}
		case "green creature": {
		    G_CREATURES.add(line[1]);
		    CREATURES.add(line[1]);
		    break;
		}
		case "haste creature": {
		    H_CREATURES.add(line[1]);
		    break;
		}
		case "creature": {
		    CREATURES.add(line[1]);
		    break;
		}
		default: {
		    System.err.println(line[0] + "\t" + line[1]);
		    break;
		}
	    }
	}
//	System.out.println("Red Creatures: " + R_CREATURES);
//	System.out.println("Green Creatures: " + G_CREATURES);
//	System.out.println("White Creatures: " + W_CREATURES);
//	System.out.println("Haste Creatures: " + H_CREATURES);
//	System.out.println("All Creatures: " + CREATURES);
    }

    /**
     * For the purposes of the simulations, this clears all non-final arrays and
     * resets to the start of the game
     *
     * @throws FileNotFoundException
     */
    static void initGame() throws FileNotFoundException {;
	EVOLVE_COUNTERS.clear();
	HAND.clear();
	DECK.clear();
	FIELD.clear();
	POWER.clear();
	if (!initDeck) {
	    Scanner scan = new Scanner(new File("Deck File.txt"));
	    int quantity;
	    String line;
	    while (scan.hasNextLine()) {
		quantity = scan.nextInt();
		line = scan.nextLine().substring(1);
		for (int i = 0; i < quantity; i++) {
		    DECK_INITIAL.add(line);
		}
	    }
	    initDeck = true;
	}
	DECK.addAll(DECK_INITIAL);
    }

    /**
     * Randomize the deck
     */
    static void shuffle() {
	long seed = System.nanoTime();
	Collections.shuffle(DECK, new Random(seed));
    }

    /**
     * Take (cards) number of cards from the deck and add them to the hand. Then
     * remove them from the deck. Define DECK[0] as the top of the deck for play
     * and drawing purposes.
     *
     * @param cards number of cards to be drawn
     */
    static void draw(int cards) {
//        System.out.println("Draw " + cards);
	for (int i = 0; i < cards; i++) {
	    HAND.add(DECK.remove(0));
	}
    }

    /**
     * resolveMulligans() uses HAND.size() as a variable in the switch. This
     * method is recursive. At each case, it checks the hand against a minimum
     * and maximum number of lands, if the hand is outside the bounds, the deck
     * is reset, shuffled and the draw(int) method is called with one less than
     * the previous. TODO see if this can be looped instead of a switch. TODO
     * add cases for checking number of creatures and/or avg cmc of the hand
     */
    static void resolveMulligans() throws FileNotFoundException {
	switch (HAND.size()) {
	    case 7:
		if (pHand()) {
		    return;
		}
		if (containsCard(HAND, ALL_LAND, 2, 4)
			&& (containsCard(HAND, CREATURES, 2, 5))) {
		    if ((numCard(HAND, G_LANDS) > 0 || numCard(HAND, FETCH_LANDS) > 0)
			    && (numCard(HAND, G_CREATURES) > 0)) {
			return;
		    }
		    if ((numCard(HAND, R_LANDS) > 0 || numCard(HAND, FETCH_LANDS) > 0)
			    && (numCard(HAND, R_CREATURES) > 0)) {
			return;
		    }
		    if ((numCard(HAND, W_LANDS) > 0 || numCard(HAND, FETCH_LANDS) > 0)
			    && (numCard(HAND, W_CREATURES) > 0)) {
			return;
		    }
		}
		break;
	    case 6:
		if (containsCard(HAND, ALL_LAND, 1, 4) && (numCard(HAND, CREATURES) > 1
			|| (numCard(HAND, "Steppe Lynx") > 0 && numCard(HAND, ALL_LAND) > 2))) {
		    return;
		}
		break;
	    case 5:
		if (numCard(HAND, ALL_LAND) > 0 && numCard(HAND, CREATURES) > 0 && (numCard(HAND, "Experiment One") != 1 || numCard(HAND, CREATURES) > 1)) {
		    return;
		}
		break;
	    case 4:
		if (numCard(HAND, ALL_LAND) > 0 && numCard(HAND, CREATURES) > 0) {
		    return;
		}
		break;
	    case 3:
		if (numCard(HAND, ALL_LAND) > 0 && numCard(HAND, CREATURES) > 0) {
		    return;
		}
		break;
	    default: {
		return;
	    }
	}
	int i = (HAND.size() - 1);
	initGame();
	shuffle();
	draw(i);
	resolveMulligans();
    }

    /**
     * Takes a list and checks to see how many elements of a separate list are
     * contained within. (list[] contains card[] true/false)
     *
     * @param list The list to be checked
     * @param card The list of cards to be checked against
     * @param lower Minimum number of objects in card that must be in list
     * @param upper Maximum number of objects in card that must be in list
     * @return true if a number of objects (equal to or greater than lower AND
     * equal to or less than upper) from card appear in list.
     */
    static boolean containsCard(List<String> list, List<String> card, int lower, int upper) {
	int count = numCard(list, card);
	return (count >= lower && count <= upper);
    }

    /**
     * Checks to see how many of an element, card, are contained in a list,
     * list. Returns true if the number of elements in the list are between
     * lower and upper (inclusive)
     *
     * @param list list to be checked
     * @param card element to be checked for in the list
     * @param lower lower bound (inclusive)
     * @param upper upper bound (inclusive)
     * @return TRUE if card appears in list >= lower number of times && <= upper
     * number of times
     */
    static boolean containsCard(List<String> list, String card, int lower, int upper) {
	int count = numCard(list, card);
	return (count >= lower && count <= upper);
    }

    /**
     * TODO Comment
     *
     * @param list
     * @param card
     * @return
     */
    static int numCard(List<String> list, List<String> card) {
	int count = 0;
	for (int i = 0; i < list.size(); i++) {
	    if (isCard(list, card, i)) {
		count++;
	    }
	}
	return count;
    }

    /**
     * TODO Comment code
     *
     * @param list
     * @param card
     * @return
     */
    static int numCard(List<String> list, String card) {
	int count = 0;
	for (int i = 0; i < list.size(); i++) {
	    if (isCard(list, card, i)) {
		count++;
	    }
	}
	return count;
    }

    /**
     * Takes a card and checks to see if it is in the list of cards. All cards
     * managed by ArrayLists.
     *
     * @param card ArrayList containing the card to be checked
     * @param list List of cards to be checked against
     * @param i index of card in List card.
     * @return true if the card appears in the list
     *
     * Example: isCard(FIELD,CREATURES,0) checks the 0th element of FIELD
     * against all elements in CREATURES
     */
    static boolean isCard(List<String> card, List<String> list, int i) {
	return list.indexOf(card.get(i)) > -1;

    }

    /**
     * TODO: Comment
     *
     * @param card
     * @param list
     * @param i
     * @return
     */
    static boolean isCard(List<String> card, String list, int i) {
	return (list.equals(card.get(i)));
    }

    /**
     * scry() looks at the hand size, determines if a mulligan has been taken
     * (hand.size() less than 7) and then performs various checks to determine
     * whether to keep the top card of the deck on top or move it to the bottom.
     * TODO apply more logic and more cases to this. Include options for
     * creature, cmc (converted mana cost) of creatures, etc. @return The number
     * of times mulliganed. For instance, if you mulled twice, you would have 5
     * cards in hand. This returns 7-HAND.size() thus a mull to five would
     * return 2.
     */
    static int scry() {
	if (HAND.size() < 7) {
	    if (pHand()) {
		if (numCard(HAND, FIRSTDRAW) > 0) {
		    if (isCard(DECK, FIRSTDRAW, 0)) {
			return 7 - HAND.size();
		    }
		}
	    }
	    if (numCard(HAND, ALL_LAND) == 1) {/* if you kept at one land, scry away
anything that is not a land*/
		if (!isCard(DECK, ALL_LAND, 0)) {
		    scryBottom();
		}
	    } else if (numCard(HAND, ALL_LAND) > 2) {
		if (numCard(HAND, FETCH_LANDS) > 0) {
		    if (!isCard(DECK, CREATURES, 0)) {
			scryBottom();
		    }
		} else if ((numCard(HAND, R_LANDS) == 0) && numCard(HAND, R_CREATURES) > 0) {
		    if (isCard(DECK, FETCH_LANDS, 0) || isCard(DECK, R_LANDS, 0)) {
			return 7 - HAND.size();
		    }
		} else if ((numCard(HAND, G_LANDS) == 0) && numCard(HAND, G_CREATURES) > 0) {
		    if (isCard(DECK, FETCH_LANDS, 0) || isCard(DECK, G_LANDS, 0)) {
			return 7 - HAND.size();
		    }
		} else if ((numCard(HAND, W_LANDS) == 0) && numCard(HAND, W_CREATURES) > 0) {
		    if (isCard(DECK, W_LANDS, 0)) {//logic is different for white because of the lack of white creatures/spells
			return 7 - HAND.size();
		    }
		} else if (isCard(DECK, ALL_LAND, 0)) {
		    scryBottom();
		}
	    } else if (containsCard(HAND, CREATURES, 0, 2)) {
		if (!isCard(DECK, CREATURES, 0)) {
		    scryBottom();
		}
	    }
	    return 7 - HAND.size();
	} else {
	    return 0;
	}
    }

    /**
     * Take the element from the "top" of the deck and move it to the "bottom"
     */
    static void scryBottom() {
	DECK.add(DECK.remove(0));
    }

    /**
     * TODO Comment
     *
     * @return
     */
    static boolean pHand() {
	return (HAND.containsAll(INOPENING)
		&& numCard(HAND, FETCH_LANDS) > 0
		&& (numCard(Deck.HAND, W_SHOCKS) > 0
		|| numCard(Deck.HAND, FETCH_LANDS) > 1));
    }

    /**
     * TODO Comment
     *
     * @return
     */
    static boolean pGame() {
	return (HAND.containsAll(FIRSTDRAW) && numCard(HAND, FETCH_LANDS) > 1);
    }

    /**
     * Under construction
     *
     * @param turn
     */
    static int play(int turn) {//under construction
	initTurn();
	if (turn != 1) {
	    draw(1);
	}
	LANDFALL = playLand();
	calcMana(turn);
	MANA = calcMana();
	int nhp = firstMain();
	boolean var = (numCard(FIELD, CREATURES) > 0);
	int power = calcPower(LANDFALL) - nhp;
	power += extrdmg;
	MANA = secondMain(MANA);
	if (var) {
	    power += extendedCleanup(MANA);
	} else {
	    power += cleanup(MANA);
	}
	return power;
    }

    static void initTurn() {
	SURGE = false;
	LANDFALL = 0;
	W_MANA = 0;
	U_MANA = 0;
	B_MANA = 0;
	R_MANA = 0;
	G_MANA = 0;
	WU_MANA = 0;
	WB_MANA = 0;
	WR_MANA = 0;
	WG_MANA = 0;
	UB_MANA = 0;
	UR_MANA = 0;
	UG_MANA = 0;
	BR_MANA = 0;
	BG_MANA = 0;
	RG_MANA = 0;
	MANA = 0;
    }

    /**
     *
     * TODO:Comment, extensively
     *
     * @return
     */
    static int playLand() {
	int landfall = 0;
	if (numCard(HAND, ALL_LAND) > 0) {//make sure the hand has land in it
	    if (numCard(HAND, ALL_LAND) + numCard(HAND, "Steppe Lynx") == HAND.size()
		    && calcMana() > 0 && numCard(FIELD, "Steppe Lynx") == 0) {
		return 0;//if you only have steppe lynx and land in hand, don't play a land
	    }
	    if (numCard(FIELD, ALL_LAND) == 0) {//if there are zero lands on the field
		if (pHand()) {
		    if (numCard(HAND, W_SHOCKS) > 0) {
			FIELD.add(HAND.remove(findCard(HAND, W_SHOCKS)));

			landfall = 1;
		    } else {
			if (numCard(HAND, "Bloodstained Mire") == 0) {
			    FIELD.add(DECK.remove(findCard(DECK, "Temple Garden")));
			} else {
			    FIELD.add(DECK.remove(findCard(DECK, "Sacred Foundry")));
			}
			HAND.remove(findCard(HAND, FETCH_LANDS));
			shuffle();
			landfall = 2;
		    }
		} else if (numCard(HAND, "Stomping Ground") > 0) {
		    FIELD.add(HAND.remove(findCard(HAND, "Stomping Ground")));
		    landfall = 1;
		} else if (numCard(HAND, FETCH_LANDS) > 0) {
		    FIELD.add(DECK.remove(findCard(DECK, "Stomping Ground")));
		    HAND.remove(findCard(HAND, FETCH_LANDS));
		    shuffle();
		    landfall = 2;
		} else if (numCard(HAND, SHOCK_LANDS) > 0) {
		    FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
		    landfall = 1;
		} else {
		    FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
		    landfall = 1;
		}
	    } else if (numCard(FIELD, ALL_LAND) > 0) {//if there are 2 or more lands on the field
		if (numCard(FIELD, FORESTS) > 0
			&& numCard(FIELD, MOUNTAINS) > 0
			&& numCard(FIELD, PLAINS) > 0) {
		    if (numCard(HAND, "Stomping Ground") > 0) {
			FIELD.add(HAND.remove(findCard(HAND, "Stomping Ground")));
			landfall = 1;
		    } else if (numCard(HAND, FETCH_LANDS) > 0) {
			if (numCard(DECK, "Stomping Ground") > 0) {
			    FIELD.add(DECK.remove(findCard(DECK, "Stomping Ground")));
			} else if (numCard(DECK, SHOCK_LANDS) > 0) {
			    FIELD.add(DECK.remove(findCard(DECK, SHOCK_LANDS)));
			} else if (numCard(DECK, BASIC_LANDS) > 0) {
			    FIELD.add(DECK.remove(findCard(DECK, BASIC_LANDS)));
			} else {
			    System.out.println("Failure to find HERE");
			    landfall -= 1;
			}
			HAND.remove(findCard(HAND, FETCH_LANDS));
			shuffle();
			landfall += 2;
		    } else if (numCard(HAND, SHOCK_LANDS) > 0) {
			FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
			landfall = 1;
		    } else {
			FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
			landfall = 1;
		    }
		} else if ((numCard(FIELD, FORESTS) == 0)
			|| (numCard(FIELD, MOUNTAINS) == 0)) {
		    if (numCard(HAND, "Stomping Ground") > 0) {
			FIELD.add(HAND.remove(findCard(HAND, "Stomping Ground")));
			landfall = 1;
		    } else if (numCard(HAND, FETCH_LANDS) > 0) {
			FIELD.add(DECK.remove(findCard(DECK, "Stomping Ground")));
			HAND.remove(findCard(HAND, FETCH_LANDS));
			shuffle();
			landfall = 2;
		    } else if (numCard(FIELD, FORESTS) == 0) {
			if (numCard(HAND, FORESTS) > 0) {
			    FIELD.add(HAND.remove(findCard(HAND, FORESTS)));
			    landfall = 1;
			} else {
			    if (numCard(HAND, G_LANDS) > 0) {
				FIELD.add(HAND.remove(findCard(HAND, G_LANDS)));
			    } else if (numCard(HAND, SHOCK_LANDS) > 0) {
				FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
			    } else {
				FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
			    }
			    landfall = 1;
			}
		    } else if (containsCard(HAND, MOUNTAINS, 1, HAND.size())) {
			FIELD.add(HAND.remove(findCard(HAND, MOUNTAINS)));
			landfall = 1;

		    } else {
			if (numCard(HAND, R_LANDS) > 0) {
			    FIELD.add(HAND.remove(findCard(HAND, R_LANDS)));
			} else if (numCard(HAND, SHOCK_LANDS) > 0) {
			    FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
			} else {
			    FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
			}
			landfall = 1;
		    }
		} else if (numCard(HAND, W_SHOCKS) > 0) {
		    FIELD.add(HAND.remove(findCard(HAND, W_SHOCKS)));
		    landfall = 1;
		} else if (numCard(HAND, FETCH_LANDS) > 0) {
		    FIELD.add(DECK.remove(findCard(DECK, W_SHOCKS)));
		    HAND.remove(findCard(HAND, FETCH_LANDS));
		    shuffle();
		    landfall = 2;
		} else {
		    FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
		    landfall = 1;
		}
	    }
	}
	return landfall;
    }

    /**
     * returns the index of the first object in a List (list) to be found in a
     * second List (card)
     *
     * @param list The list of objects being searched through
     * @param card The list of objects being compared against.
     * @return the first index of list where list[index] is equal to an object
     * in card
     *
     * TODO: Fix this. Horribly inefficient
     */
    static int findCard(List<String> list, List<String> cards) {
	for (int i = 0; i < list.size(); i++) {
	    if (isCard(list, cards, i)) {
		return i;
	    }
	}

	System.out.println("Cards: " + cards + "not found in " + list + "\n\n");

	return -1;
    }

    /**
     * Returns the index of an element, card, in a list, list
     *
     * @param list list of card to be checked through
     * @param card card to be checked for
     * @return index of the the card in the list
     */
    static int findCard(List<String> list, String card) {
	return list.indexOf(card);
    }

    /**
     * TODO: Comment
     *
     * @return
     */
    static int calcMana() {
	int count = 0;
	for (int i = 0; i < FIELD.size(); i++) {
	    if (isCard(FIELD, ALL_LAND, i)) {
		count++;
	    }
	}
	return count;
    }

    static void calcMana(int ij) {
	for (int i = 0; i < FIELD.size(); i++) {
	    if (isCard(FIELD, ALL_LAND, i)) {
		if (isCard(FIELD, W_SHOCKS, i)) {
		    if (isCard(FIELD, "Hallowed Fountain", i)) {
			WU_MANA++;
		    } else if (isCard(FIELD, "Godless Shrine", i)) {
			WB_MANA++;
		    } else if (isCard(FIELD, "Sacred Foundry", i)) {
			WR_MANA++;
		    } else if (isCard(FIELD, "Temple Garden", i)) {
			WG_MANA++;
		    }
		} else if (isCard(FIELD, U_SHOCKS, i)) {
		    if (isCard(FIELD, "Watery Grave", i)) {
			UB_MANA++;
		    } else if (isCard(FIELD, "Steam Vents", i)) {
			UR_MANA++;
		    } else if (isCard(FIELD, "Breeding Pool", i)) {
			UG_MANA++;
		    }
		} else if (isCard(FIELD, B_SHOCKS, i)) {
		    if (isCard(FIELD, "Blood Crypt", i)) {
			BR_MANA++;
		    } else if (isCard(FIELD, "Overgrown Tomb", i)) {
			BG_MANA++;
		    }
		} else if (isCard(FIELD, "Stomping Ground", i)) {
		    RG_MANA++;
		} else if (isCard(FIELD, PLAINS, i)) {
		    W_MANA++;
		} else if (isCard(FIELD, ISLANDS, i)) {
		    U_MANA++;
		} else if (isCard(FIELD, SWAMPS, i)) {
		    B_MANA++;
		} else if (isCard(FIELD, MOUNTAINS, i)) {
		    R_MANA++;
		} else if (isCard(FIELD, FORESTS, i)) {
		    G_MANA++;
		} else {
		    MANA++;
		}
	    }
	}

    }

    /**
     * TODO: Comment code The firstMain() plays intelligently based on creatures
     * in hand and on the field
     *
     * After every creature played it passes the creatures power and toughness
     * to the evolve function If a creature without haste is played, it keeps
     * track of that with the nhp and nhc variables First it checks for
     * Burning-Tree Emissary and mana greater than 1. It plays all BTE's at that
     * point Then, it checks for a combo set up. If the hand has everything
     * necessary for T2, 11 damage, exit out of first main Third, it checks to
     * see if the combo has been set up. If it has, then it executes the combo
     * Then it checks for creatures with haste in hand, if there are haste
     * creatures, it executes the following: If there is only one mana
     * available, it plays a Goblin Guide if there is one. If there is more than
     * one mana available and no Bushwhackers in hand, play all Goblin Guides If
     * there is more than 2 mana available and bushwhackers in hand, the
     * functions sets aside 2 mana for each bushwhacker and call the second main
     * function to play any remaining creatures. Then it plays any remaining
     * Goblin Guides that it has mana for Next, if surge hasn't been triggered
     * but there is 3 or more mana available, play a bushwhacker Then if surge
     * still hasn't been triggered, play a goblin guide if you have it
     *
     * @param
     * @return
     *
     * TODO Check logic for possibly prioritizing T1 Experiment One and Wild
     * Nacatl over Goblin Guide. Situation follows: Turn: 1, before play: [Wild
     * Nacatl, Windswept Heath, Kird Ape, Windswept Heath, Experiment One, Kird
     * Ape, Goblin Guide]
     *
     *
     */
    static int firstMain() {
	int nhp = 0;//non haste power
	int nhc = 0;//non hast creatres
	extrdmg = 0;
	while (MANA > 1 && numCard(HAND, "Burning-Tree Emissary") > 0) {
	    SURGE = true;
	    FIELD.add(HAND.remove(findCard(HAND, "Burning-Tree Emissary")));
	    evolve(2, 2);
	    nhp += 2;
	    nhc++;
	}
	if ((MANA == 1) && HAND.containsAll(INOPENING)
		&& numCard(HAND, FETCH_LANDS) > 0) {//turn 1, if we have the combo, get out of this function
	    return nhp;
	}
	if ((HAND.containsAll(FIRSTDRAW) && numCard(HAND, FETCH_LANDS) > 0)
		&& numCard(FIELD, "Steppe Lynx") > 0 && (MANA > 1)) {//C-C-C-C-Combo!
	    MANA -= 2;
	    HAND.remove(findCard(HAND, "Atarka's Command"));
	    SURGE = true;
	    if (numCard(DECK, "Stomping Ground") > 0) {
		FIELD.add(DECK.remove(findCard(DECK, "Stomping Ground")));
	    } else if (numCard(DECK, SHOCK_LANDS) > 0) {//Come back to this logic
		FIELD.add(DECK.remove(findCard(DECK, SHOCK_LANDS)));
	    } else if (numCard(DECK, BASIC_LANDS) > 0) {
		FIELD.add(DECK.remove(findCard(DECK, BASIC_LANDS)));
	    } else {
		System.out.println("Failure to find");
		LANDFALL -= 1;
		MANA -= 1;
	    }
	    HAND.remove(findCard(HAND, FETCH_LANDS));
	    shuffle();
	    LANDFALL += 2;
	    MANA += 1;
	    if (numCard(FIELD, CREATURES) > 2 + nhc) {
//if you can deal more damage through the +1/+1 mode of Atarka's Command, do that
		extrdmg += (numCard(FIELD, CREATURES) - nhc);
	    } else {//otherwise, bolt their face
		extrdmg += 3;
	    }
	}
	if (numCard(HAND, H_CREATURES) > 0) {
	    if (MANA == 1 && numCard(HAND, "Goblin Guide") > 0) {
		MANA -= 1;
		SURGE = true;
		FIELD.add(HAND.remove(findCard(HAND, "Goblin Guide")));
		evolve(2, 2);
	    }
	    while (MANA >= 1 && numCard(HAND, "Reckless Bushwhacker") == 0
		    && (numCard(HAND, "Goblin Guide") > 0)) {
		MANA -= 1;
		SURGE = true;
		FIELD.add(HAND.remove(findCard(HAND, "Goblin Guide")));
		evolve(2, 2);
	    }
	    if (MANA > 2 && numCard(HAND, "Reckless Bushwhacker") > 0) {
		int var = 2 * numCard(HAND, "Reckless Bushwhacker");
		//mana needed to cast all bushwhackers in hand
		while (var > MANA) {
		    var -= 2;
		}
		int x = numCard(FIELD, CREATURES);
		MANA = secondMain(MANA - var);
		nhc += numCard(FIELD, CREATURES) - x;

		while ((MANA > var || MANA % 2 == 1) && numCard(HAND, "Goblin Guide") > 0) {
		    MANA -= 1;
		    SURGE = true;
		    FIELD.add(HAND.remove(findCard(HAND, "Goblin Guide")));
		    evolve(2, 2);
		}
	    }
	    if (MANA >= 3 && !SURGE && numCard(HAND, "Reckless Bushwhacker") > 0) {
		MANA -= 3;
		SURGE = true;
		extrdmg += (numCard(FIELD, CREATURES));
		nhp = 0;
		nhc = 0;
		FIELD.add(HAND.remove(findCard(HAND, "Reckless Bushwhacker")));
		evolve(2, 1);
	    }
	    if (!SURGE && numCard(HAND, "Goblin Guide") > 0) {
		MANA--;
		SURGE = true;
		evolve(2, 2);
	    }
	    while (MANA >= 2 && numCard(HAND, "Reckless Bushwhacker") > 0 && SURGE
		    && numCard(FIELD, CREATURES) > 0) {
		MANA -= 2;
		extrdmg += (numCard(FIELD, CREATURES));
		nhp = 0;
		nhc = 0;
		FIELD.add(HAND.remove(findCard(HAND, "Reckless Bushwhacker")));
		evolve(2, 1);
	    }

	}

	if (numCard(FIELD, "Experiment One") > 0) {
	    int i = calcPower(LANDFALL);
	    int v = numCard(FIELD, CREATURES);
	    MANA = secondMain(MANA);
	    nhp += calcPower(LANDFALL) - i;
	    nhc += numCard(FIELD, CREATURES) - v;
	}

	while (numCard(HAND, "Reckless Bushwhacker") > 0) {
	    if (SURGE && MANA > 1) {
		MANA -= 2;
		extrdmg += (numCard(FIELD, CREATURES));
		nhp = 0;
		nhc = 0;
	    } else if (MANA > 2) {
		MANA -= 3;
		SURGE = true;
	    } else {
		break;
	    }
	    FIELD.add(HAND.remove(findCard(HAND, "Reckless Bushwhacker")));
	    evolve(2, 1);
	}
	if (numCard(HAND, CREATURES) == 0 && MANA > 1) {

	    while (MANA > 1 && numCard(FIELD, CREATURES) > 0
		    && (numCard(HAND, "Atarka's Command") > 0 || numCard(HAND, "Ghor-Clan Rampager") > 0)) {
		if (numCard(FIELD, CREATURES) > 2) {
		    if (numCard(HAND, "Atarka's Command") > 0) {
			MANA -= 2;
			HAND.remove(findCard(HAND, "Atarka's Command"));
			extrdmg += (numCard(FIELD, CREATURES) - nhc) + 3;
		    } else if (numCard(HAND, "Ghor-Clan Rampager") > 0) {
			MANA -= 2;
			HAND.remove(findCard(HAND, "Ghor-Clan Rampager"));
			extrdmg += 4;
		    }
		} else if (numCard(HAND, "Ghor-Clan Rampager") > 0) {
		    MANA -= 2;
		    HAND.remove(findCard(HAND, "Ghor-Clan Rampager"));
		    extrdmg += 4;
		} else if (numCard(HAND, "Atarka's Command") > 0) {
		    MANA -= 2;
		    HAND.remove(findCard(HAND, "Atarka's Command"));
		    extrdmg += (numCard(FIELD, CREATURES) - nhc) + 3;
		}
	    }
	}
//	System.out.println("Extrdmg: " + extrdmg);
//	System.out.println("nhp: " + nhp);
	return nhp;
    }

    /**
     * This function intelligently casts spells based on mana available and
     * cards in hand
     *
     * @param generic number of generic mana in the cost
     * @param cost colored mana symbols in the cost. Format should be in wubrg
     * order If it is a hybrid mana, denote that with H followed by the two
     * colors (in wubrg order).
     * @return false if unable to cast the spell, true if able to cast the
     * spell. input for the function should look like 3,RHRG for 3 generic, one
     * red, one red/green hybrid
     */
    static boolean castSpell(int generic, String cost) {
	int w = 0;
	int u = 0;
	int b = 0;
	int r = 0;
	int g = 0;
	int wu = 0;
	int wb = 0;
	int wr = 0;
	int wg = 0;
	int ub = 0;
	int ur = 0;
	int ug = 0;
	int br = 0;
	int bg = 0;
	int rg = 0;

	for (int i = 0; i < cost.length(); i++) {
	    switch (cost.charAt(i)) {
		case 'w': {
		    w++;
		    break;
		}
		case 'u': {
		    u++;
		    break;
		}
		case 'b': {
		    b++;
		    break;
		}
		case 'r': {
		    r++;
		    break;
		}
		case 'g': {
		    g++;
		    break;
		}
		case 'h': {
		    char ch = cost.charAt(i + 2);
		    switch (cost.charAt(i + 1)) {
			case 'w': {
			    switch (ch) {
				case 'u': {
				    wu++;
				    break;
				}
				case 'b': {
				    wb++;
				    break;
				}
				case 'r': {
				    wr++;
				    break;
				}
				case 'g': {
				    wg++;
				    break;
				}
			    }
			    break;
			}
			case 'u': {
			    switch (ch) {
				case 'b': {
				    ub++;
				    break;
				}
				case 'r': {
				    ur++;
				    break;
				}
				case 'g': {
				    ug++;
				    break;
				}
			    }
			    break;
			}
			case 'b': {
			    switch (ch) {
				case 'r': {
				    br++;
				    break;
				}
				case 'g': {
				    bg++;
				    break;
				}
			    }
			    break;
			}
			case 'r': {
			    rg++;
			    break;
			}
		    }
		    i += 2;
		}
	    }
	}
	if (w > W_MANA + WU_MANA + WB_MANA + WR_MANA + WG_MANA
		|| u > U_MANA + WU_MANA + UB_MANA + UR_MANA + UG_MANA
		|| b > B_MANA + WB_MANA + UB_MANA + BR_MANA + BG_MANA
		|| r > R_MANA + WR_MANA + UR_MANA + BR_MANA + RG_MANA
		|| g > G_MANA + WG_MANA + UG_MANA + BG_MANA + RG_MANA
		|| w + u + b + r + g + wu + wb + wr + wg + ub + ur + ug + br
		+ bg + rg + generic > W_MANA + U_MANA + B_MANA + R_MANA + G_MANA
		+ WU_MANA + WB_MANA + WR_MANA + WG_MANA + UB_MANA + UR_MANA
		+ UG_MANA + BR_MANA + BG_MANA + RG_MANA + COLORLESS_MANA) {
	    return false;
	}
	while (w + u + b + r + g + wu + wb + wr + wg + ub + ur + ug + br + bg + rg > 0) {
	    while (w > 0) {
		if (W_MANA > 0) {
		    W_MANA--;
		    w--;
		} else if (u == 0 && WU_MANA > 0) {
		    WU_MANA--;
		    w--;
		} else if (b == 0 && WB_MANA > 0) {
		    WB_MANA--;
		    w--;
		} else if (u == 0 && WR_MANA > 0) {
		    WR_MANA--;
		    w--;
		} else if (b == 0 && WG_MANA > 0) {
		    WG_MANA--;
		    w--;
		} else//come back and make this more intelligent
		{
		    if (WU_MANA > 0) {
			WU_MANA--;
			w--;
		    } else if (WB_MANA > 0) {
			WB_MANA--;
			wb--;
		    }
		}

	    }
	}
	return false;
    }

    /**
     * TODO: Comment code
     *
     * @param power
     * @param toughness
     */
    static void evolve(int power, int toughness) {
	for (int i = 0; i < EVOLVE_COUNTERS.size(); i++) {

	    if (power > EVOLVE_COUNTERS.get(i) + 1 || toughness > EVOLVE_COUNTERS.get(i) + 1) {
		EVOLVE_COUNTERS.set(i, EVOLVE_COUNTERS.get(i) + 1);
	    }
	}

    }

    /**
     * TODO: Comment code
     *
     * @param mana
     * @return
     */
    static int secondMain(int mana) {
	if (mana > 0) {
	    if (numCard(HAND, FETCH_LANDS) > 0) {
		while (numCard(HAND, "Steppe Lynx") > 0 && mana > 0) {
		    mana -= 1;
		    SURGE = true;
		    FIELD.add(HAND.remove(findCard(HAND, "Steppe Lynx")));
		    evolve(0, 1);
		}
	    }
	    while (numCard(HAND, "Experiment One") > 0 && mana > 0) {
		mana -= 1;
		SURGE = true;
		FIELD.add(HAND.remove(findCard(HAND, "Experiment One")));
		EVOLVE_COUNTERS.add(0);
	    }
	    while (numCard(HAND, "Wild Nacatl") > 0 && mana > 0) {
		mana -= 1;
		SURGE = true;
		FIELD.add(HAND.remove(findCard(HAND, "Wild Nacatl")));
		int var = 0;
		if (numCard(FIELD, MOUNTAINS) > 0) {
		    var++;
		}
		if (numCard(FIELD, PLAINS) > 0 || numCard(FIELD, W_SHOCKS) > 0) {
		    var++;
		}
		evolve(1 + var, 1 + var);
	    }
	    while ((numCard(HAND, "Kird Ape") > 0) && mana > 0) {
		mana -= 1;
		SURGE = true;
		FIELD.add(HAND.remove(findCard(HAND, "Kird Ape")));
		if (numCard(FIELD, FORESTS) > 0) {
		    evolve(2, 3);
		} else {
		    evolve(1, 1);
		}
	    }
	    while (numCard(HAND, "Steppe Lynx") > 0 && mana > 0) {
		mana -= 1;
		SURGE = true;
		FIELD.add(HAND.remove(findCard(HAND, "Steppe Lynx")));
		evolve(0, 2);
	    }

	}
	return mana;
    }

    /**
     * TODO: Comment
     *
     * @param landfall
     * @return
     */
    static int calcPower(int landfall) {
	int power = 0;
//	System.out.println("In calcPower");
	for (int i = 0; i < FIELD.size(); i++) {
	    if (isCard(FIELD, CREATURES, i)) {
//		System.out.println("Creature: " + FIELD.get(i));
		switch (FIELD.get(i)) {
		    case "Burning-Tree Emissary": {
			power += 2;
			break;
		    }
		    case "Experiment One": {
			power += 1; //come back to this one
			break;
		    }
		    case "Ghor-Clan Rampager": {
			power += 4;
			break;
		    }
		    case "Goblin Guide": {
			power += 2;
			break;
		    }
		    case "Kird Ape": {
			if (numCard(FIELD, FORESTS) > 0) {
			    power += 2;
			} else {
			    power += 1;
			}
			break;
		    }
		    case "Reckless Bushwhacker": {
			power += 2;
			break;
		    }
		    case "Steppe Lynx": {
			power += (landfall * 2);
			break;
		    }
		    case "Wild Nacatl": {
			power += 1;
			if (numCard(FIELD, MOUNTAINS) > 0) {
			    power += 1;
			}
			if (numCard(FIELD, PLAINS) > 0 || numCard(FIELD, W_SHOCKS) > 0) {
			    power += 1;
			}
			break;
		    }
		}
	    }
	}
	int evolve_power = 0;
	for (int j = 0; j < EVOLVE_COUNTERS.size(); j++) {
	    evolve_power += EVOLVE_COUNTERS.get(j);
	}
//	System.out.println("Evolve Power: " + evolve_power);
	return power + evolve_power;
    }

    static int cleanup(int mana) {
	int cleanup = 0;
	while (mana > 0 && (numCard(HAND, "Lightning Bolt") > 0)) {
	    mana--;
	    HAND.remove(findCard(HAND, "Lightning Bolt"));
	    cleanup += 3;
	}
	return cleanup;
    }

    static int extendedCleanup(int mana) {
	int cleanup = 0;
	if (mana > 0 && (numCard(HAND, "Lightning Bolt") > 0 || numCard(HAND, "Ghor-Clan Rampager") > 0)) {
	    if (mana % 2 == 1) {
		if (numCard(HAND, "Lightning Bolt") > 0) {
		    mana--;
		    HAND.remove(findCard(HAND, "Lightning Bolt"));
		    cleanup += 3;
		}
	    }
	    if (mana > 1) {
		if (numCard(HAND, "Lightning Bolt") > 1) {
		    while (mana > 0 && numCard(HAND, "Lightning Bolt") > 0) {
			mana--;
			HAND.remove(findCard(HAND, "Lightning Bolt"));
			cleanup += 3;
		    }
		}
		if (mana > 1 && numCard(HAND, "Ghor-Clan Rampager") > 0) {
		    mana -= 2;
		    HAND.remove(findCard(HAND, "Ghor-Clan Rampager"));
		    cleanup += 4;
		}
		while (mana > 0 && numCard(HAND, "Lightning Bolt") > 0) {
		    mana--;
		    HAND.remove(findCard(HAND, "Lightning Bolt"));
		    cleanup += 3;
		}
	    }
	}
	return cleanup;
    }
}

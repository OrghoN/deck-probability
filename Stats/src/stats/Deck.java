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
 * @version 22 July 2016 3:55 P.M.
 */
public class Deck {

    private static final Random RNG = new Random();//used in shuffle()
    public static final List<String> DECK = new ArrayList<>();//ArrayList for Deck
    public static final List<String> DECK_INITIAL = new ArrayList<>();//ArrayList for DecK Initial
    public static final List<String> HAND = new ArrayList<>();//ArrayList for Hand
    public static final List<String> FETCH_LANDS = new ArrayList<>();
    public static final List<String> W_SHOCKS = new ArrayList<>();
    public static final List<String> W_LANDS = new ArrayList<>();
    public static final List<String> G_LANDS = new ArrayList<>();
    public static final List<String> R_LANDS = new ArrayList<>();
    public static final List<String> CREATURES = new ArrayList<>();
    public static final List<String> ALL_LAND = new ArrayList<>();
    public static final List<String> SHOCK_LANDS = new ArrayList<>();
    public static final List<String> BASIC_LANDS = new ArrayList<>();
    public static final List<String> FIELD = new ArrayList<>();
    public static final List<Integer> POWER = new ArrayList<>();
    private static final ArrayList<String> INOPENING = new ArrayList<>();//List of cards required in opening
    private static final ArrayList<String> FIRSTDRAW = new ArrayList<>();//List of cards required in first draw
    public static boolean initDeck = false;

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
                    W_SHOCKS.add(line[1]);
                    W_LANDS.add(line[1]);
                }
                case "other shock": {
                    SHOCK_LANDS.add(line[1]);
                    ALL_LAND.add(line[1]);

                    break;
                }
                case "white land": {
                    W_LANDS.add(line[1]);
                    ALL_LAND.add(line[1]);
                    break;
                }
                case "red land": {
                    R_LANDS.add(line[1]);
                    ALL_LAND.add(line[1]);
                    break;
                }
                case "green land": {
                    G_LANDS.add(line[1]);
                    ALL_LAND.add(line[1]);
                    break;
                }
                case "basic land": {
                    BASIC_LANDS.add(line[1]);
                    ALL_LAND.add(line[1]);
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
    }

    /**
     * For the purposes of the simulations, this clears all non-final arrays and
     * resets to the start of the game
     *
     * @throws FileNotFoundException
     */
    static void initGame() throws FileNotFoundException {;
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
            HAND.add(DECK.remove(i));
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
                if (containsCard(HAND, ALL_LAND, 2, 4)
                        && (containsCard(HAND, CREATURES, 2, 5) || containsCard(HAND, "Steppe Lynx", 1, 1))) {
                    return;
                }
                break;
            case 6:
                if (containsCard(HAND, ALL_LAND, 1, 4) && containsCard(HAND, CREATURES, 1, 5)) {
                    return;
                }
                break;
            case 5:
                if (containsCard(HAND, ALL_LAND, 1, 4)) {
                    return;
                }
                break;
            case 4:
                if (containsCard(HAND, ALL_LAND, 1, 4)) {
                    return;
                }
                break;
            case 3:
                if (containsCard(HAND, ALL_LAND, 1, 3)) {
                    return;
                }
                break;
            default:
                return;
        }
        int i = (HAND.size() - 1);
        initGame();
        shuffle();
        draw(i);
        resolveMulligans();
    }

    /**
     * scry() looks at the hand size, determines if a mulligan has been taken
     * (hand.size() less than 7) and then performs various checks to determine
     * whether to keep the top card of the deck on top or move it to the bottom.
     * TODO apply more logic and more cases to this. Include options for
     * creature, cmc (converted mana cost) of creatures, etc. @return The number of times mulliganed.
     * For instance, if you mulled twice, you would have 5 cards in hand. This
     * returns 7-HAND.size() thus a mull to five would return 2.
     */
    static int scry() {
        if (HAND.size() < 7) {
            if (containsCard(HAND, ALL_LAND, 1, 1)) {/* if you kept at one land, scry away
anything that is not a land*/
                if (!isCard(DECK, ALL_LAND, 0)) {
                    scryBottom();
                }
            } else if (containsCard(HAND, ALL_LAND, 3, 7)) {
                if (isCard(DECK, ALL_LAND, 0)) {
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
 * TODO Comment
 * @return 
 */
    static boolean pHand() {
        return (HAND.containsAll(INOPENING) && containsCard(HAND, ALL_LAND, 2, 6)
                && containsCard(HAND, FETCH_LANDS, 1, 6)
                && (containsCard(Deck.HAND, W_SHOCKS, 1, 6)
                || containsCard(Deck.HAND, FETCH_LANDS, 2, 6)));
    }

    /**
     * TODO Comment
     * @return 
     */
    static boolean pGame() {
        return (HAND.containsAll(FIRSTDRAW) && containsCard(HAND, FETCH_LANDS, 2, 6));
    }

    /**
     * Take the element from the "top" of the deck and move it to the "bottom"
     */
    static void scryBottom() {
        DECK.add(DECK.remove(0));
    }

    /**
     * Under construction
     *
     * @param turn
     */
    static void play(int turn) {//under construction
        int landfall = playLand();
        int mana = calcMana();
        mana = playHaste(mana);
        int power = calcPower(landfall);
        mana = playCreature(mana);
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
        return list.indexOf(card.get(i))>-1;
        
    }
/**
 * TODO: Comment
 * @param card
 * @param list
 * @param i
 * @return 
 */
    static boolean isCard(List<String> card, String list, int i) {
        return (list.equals(card.get(i)));
    }

    /**
     * Takes a list and checks to see how many elements of a separate list are contained
     * within. (list[] contains card[] true/false)
     *
     * @param list The list to be checked
     * @param card The list of cards to be checked against
     * @param lower Minimum number of objects in card that must be in list
     * @param upper Maximum number of objects in card that must be in list
     * @return true if a number of objects (equal to or greater than lower AND
     * equal to or less than upper) from card appear in list.
     */
    static boolean containsCard(List<String> list, List<String> card, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (isCard(list, card, i)) {
                count++;
            }
        }
        return (count >= lower && count <= upper);
    }
    
/**
 * Checks to see how many of an element, card, are contained in a list, list. 
 * Returns true if the number of elements in the list are between lower and upper (inclusive)
 * @param list list to be checked
 * @param card element to be checked for in the list
 * @param lower lower bound (inclusive)
 * @param upper upper bound (inclusive)
 * @return TRUE if card appears in list >= lower number of times && <= upper number
 * of times 
 */
    static boolean containsCard(List<String> list, String card, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (isCard(list, card, i)) {
                count++;
            }
        }
        return (count >= lower && count <= upper);
    }

    /**
     * returns the index of the first object in a List (list) to be found in a second
     * List (card)
     *
     * @param list The list of objects being searched through
     * @param card The list of objects being compared against.
     * @return the first index of list where list[index] is equal to an object
     * in card
     * 
     * TODO: Fix this. Horribly inefficient
     */
    static int findCard(List<String> list, List<String> cards) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (isCard(list, cards, i)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Cards: " + cards + "not found in " + list + "\n\n");
        }
        return index;
    }
    
/**
 * Returns the index of an element, card, in a list, list
 * @param list list of card to be checked through
 * @param card card to be checked for
 * @return index of the the card in the list 
 */
    static int findCard(List<String> list, String card) {
        return list.indexOf(card);
    }

    /**
     *
     *TODO:Comment, extensively
     * @return
     */
    static int playLand() {
        int landfall = 0;
        if (containsCard(HAND, ALL_LAND, 1, HAND.size())) {//make sure the hand has land in it
            if (!containsCard(FIELD, ALL_LAND, 1, FIELD.size())) {//if there are zero lands on the field
                if (pHand()) {
                    if (containsCard(HAND, W_SHOCKS, 1, HAND.size())) {
                        FIELD.add(HAND.remove(findCard(HAND, W_SHOCKS)));
                        landfall = 1;
                    } else {
                        if (!containsCard(HAND, "Bloodstained Mire", 1, HAND.size())) {
                            FIELD.add(DECK.remove(findCard(DECK, "Temple Garden")));
                        } else {
                            FIELD.add(DECK.remove(findCard(DECK, "Sacred Foundry")));
                        }
                        HAND.remove(findCard(HAND, FETCH_LANDS));
                        shuffle();
                        landfall = 2;
                    }
                } else if (containsCard(HAND, "Stomping Ground", 1, HAND.size())) {
                    FIELD.add(HAND.remove(findCard(HAND, "Stomping Ground")));
                    landfall = 1;
                } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
                    FIELD.add(DECK.remove(findCard(DECK, "Stomping Ground")));
                    HAND.remove(findCard(HAND, FETCH_LANDS));
                    shuffle();
                    landfall = 2;
                } else if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                    FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
                    landfall = 1;
                } else {
                    FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
                    landfall = 1;
                }
            } else if (containsCard(FIELD, ALL_LAND, 1, FIELD.size())) {//if there are 2 or more lands on the field
                if (containsCard(FIELD, G_LANDS, 1, FIELD.size())
                        && containsCard(FIELD, R_LANDS, 1, FIELD.size())
                        && containsCard(FIELD, W_LANDS, 1, FIELD.size())) {
                    if (containsCard(HAND, "Stomping Ground", 1, HAND.size())) {
                        FIELD.add(HAND.remove(findCard(HAND, "Stomping Ground")));
                        landfall = 1;
                    } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
                        if (containsCard(DECK, "Stomping Ground", 1, DECK.size())) {
                            FIELD.add(DECK.remove(findCard(DECK, "Stomping Ground")));
                        } else {
                            FIELD.add(DECK.remove(findCard(DECK, SHOCK_LANDS)));
                        }
                        HAND.remove(findCard(HAND, FETCH_LANDS));
                        shuffle();
                        landfall = 2;
                    } else if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                        FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
                        landfall = 1;
                    } else {
                        FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
                        landfall = 1;
                    }
                } else if ((!containsCard(FIELD, G_LANDS, 1, FIELD.size()))
                        || (!containsCard(FIELD, R_LANDS, 1, FIELD.size()))) {
                    if (containsCard(HAND, "Stomping Ground", 1, HAND.size())) {
                        FIELD.add(HAND.remove(findCard(HAND, "Stomping Ground")));
                        landfall = 1;
                    } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
                        FIELD.add(DECK.remove(findCard(DECK, "Stomping Ground")));
                        HAND.remove(findCard(HAND, FETCH_LANDS));
                        shuffle();
                        landfall = 2;
                    } else if (!containsCard(FIELD, G_LANDS, 1, FIELD.size())) {
                        if (containsCard(HAND, G_LANDS, 1, HAND.size())) {
                            FIELD.add(HAND.remove(findCard(HAND, G_LANDS)));
                            landfall = 1;
                        } else {
                            if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                                FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
                            } else {
                                FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
                            }
                            landfall = 1;
                        }
                    } else if (containsCard(HAND, R_LANDS, 1, HAND.size())) {
                        FIELD.add(HAND.remove(findCard(HAND, R_LANDS)));
                        landfall = 1;

                    } else {
                        if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                            FIELD.add(HAND.remove(findCard(HAND, SHOCK_LANDS)));
                        } else {
                            FIELD.add(HAND.remove(findCard(HAND, ALL_LAND)));
                        }
                        landfall = 1;
                    }
                } else if (containsCard(HAND, W_SHOCKS, 1, HAND.size())) {
                    FIELD.add(HAND.remove(findCard(HAND, W_SHOCKS)));
                    landfall = 1;
                } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
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
 * TODO: Comment
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

    /**
     * TODO: Write code
     * @param mana
     * @return 
     */
    static int playCreature(int mana) {
        return mana;
    }
    
/**
 * TODO: Write code
 * @param mana
 * @return 
 */
    static int playHaste(int mana) {
        switch (mana) {
            case 1: {

            }
        }
        return mana;
    }

    /**
     * TODO: Comment
     * @param landfall
     * @return 
     */
    static int calcPower(int landfall) {
        int power = 0;
        for (int i = 0; i < FIELD.size(); i++) {
            if (isCard(FIELD, CREATURES, i)) {
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
                    }
                    case "Kird Ape": {
                        if (containsCard(FIELD, G_LANDS, 1, FIELD.size())) {
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
                        if (containsCard(FIELD, R_LANDS, 1, FIELD.size())) {
                            power += 1;
                        }
                        if (containsCard(FIELD, W_LANDS, 1, FIELD.size())) {
                            power += 1;
                        }
                        break;
                    }
                }
            }
        }
        return power;
    }
}

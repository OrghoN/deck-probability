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
 * @version 22 June 2016 4:00 P.M.
 */
public class Deck {

    private static final Random RNG = new Random();//used in shuffle()
    public static final List<String> DECK = new ArrayList<>();//ArrayList for Deck
    public static final List<String> DECK_INITIAL = new ArrayList<>();//ArrayList for DecK Initial
    public static final List<String> HAND = new ArrayList<>();//ArrayList for Hand
    public static final List<String> FETCH_LANDS = new ArrayList<>();
    public static final List<String> W_SHOCKS = new ArrayList<>();
    public static final List<String> CREATURES = new ArrayList<>();
    public static final List<String> ALL_LAND = new ArrayList<>();
    public static final List<String> SHOCK_LANDS = new ArrayList<>();
    public static final List<String> BASIC_LANDS = new ArrayList<>();
    public static final List<String> STEPPE_LYNX = new ArrayList<>();
    public static final List<String> FIELD = new ArrayList<>();
    public static boolean initDeck = false;

    static void initLists() throws FileNotFoundException {
        //String line;
        Scanner scan = new Scanner(new File("Card Definitions.txt"));
        String[] line;
        while (scan.hasNextLine()) {
            //line = scan.nextLine();
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
                }
                case "other shock": {
                    SHOCK_LANDS.add(line[1]);
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
        STEPPE_LYNX.add("Steppe Lynx");
    }

    /**
     * Make sure the hand and deck are void, then add the decklist to the game
     */
    static void initGame() throws FileNotFoundException {
//        System.out.println("\n\nNew Game");
        HAND.clear();
        DECK.clear();
        FIELD.clear();

//        if (!initDeck) {
//            Scanner scan = new Scanner(new File("Deck File.txt"));
//            int quantity;
//            String line;
//            while (scan.hasNextLine()) {
//                //line = scan.nextLine();
//                quantity = scan.nextInt();
//                line = scan.nextLine().substring(1);
//                for (int i = 0; i < quantity; i++) {
//                    DECK_INITIAL.add(line);
//                }
//            }
//            initDeck = true;
//        }
//        DECK.addAll(DECK_INITIAL);
        DECK.add("Windswept Heath");
        DECK.add("Windswept Heath");
        DECK.add("Bloodstained Mire");
        DECK.add("Bloodstained Mire");
        DECK.add("Bloodstained Mire");
        DECK.add("Bloodstained Mire");
        DECK.add("Forest");
        DECK.add("Windswept Heath");
        DECK.add("Sacred Foundry");
        DECK.add("Sacred Foundry");
        DECK.add("Stomping Grounds");
        DECK.add("Mountain");
        DECK.add("Stomping Grounds");
        DECK.add("Temple Garden");
        DECK.add("Windswept Heath");
        DECK.add("Windswept Heath");
        DECK.add("Wooded Foothills");
        DECK.add("Wooded Foothills");
        DECK.add("Wooded Foothills");
        DECK.add("Wooded Foothills");
        DECK.add("Atarka's Command");
        DECK.add("Atarka's Command");
        DECK.add("Atarka's Command");
        DECK.add("Atarka's Command");
        DECK.add("Lightning Bolt");
        DECK.add("Lightning Bolt");
        DECK.add("Lightning Bolt");
        DECK.add("Lightning Bolt");
        DECK.add("Path to Exile");
        DECK.add("Path to Exile");
        DECK.add("Burning-Tree Emissary");
        DECK.add("Burning-Tree Emissary");
        DECK.add("Burning-Tree Emissary");
        DECK.add("Burning-Tree Emissary");
        DECK.add("Experiment One");
        DECK.add("Experiment One");
        DECK.add("Experiment One");
        DECK.add("Experiment One");
        DECK.add("Ghor-Clan Rampager");
        DECK.add("Ghor-Clan Rampager");
        DECK.add("Goblin Guide");
        DECK.add("Goblin Guide");
        DECK.add("Goblin Guide");
        DECK.add("Goblin Guide");
        DECK.add("Kird Ape");
        DECK.add("Kird Ape");
        DECK.add("Kird Ape");
        DECK.add("Kird Ape");
        DECK.add("Reckless Bushwhacker");
        DECK.add("Reckless Bushwhacker");
        DECK.add("Reckless Bushwhacker");
        DECK.add("Reckless Bushwhacker");
        DECK.add("Steppe Lynx");
        DECK.add("Steppe Lynx");
        DECK.add("Steppe Lynx");
        DECK.add("Steppe Lynx");
        DECK.add("Wild Nacatl");
        DECK.add("Wild Nacatl");
        DECK.add("Wild Nacatl");
        DECK.add("Wild Nacatl");
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
        for (int i = 0; i < cards; i++) {
            HAND.add(DECK.get(i));
        }
        for (int j = 0; j < cards; j++) {
            DECK.remove(0);
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
//        System.out.println("Resolve Mulligans");

        switch (HAND.size()) {
            case 7: {
//                System.out.println(HAND.size() + " cards");
                if (containsCard(HAND, ALL_LAND, 2, 4)) {
//                    System.out.println("Keep");
                    return;
                }
                break;
            }
            case 6: {
//                System.out.println(HAND.size() + " cards");
                if (containsCard(HAND, ALL_LAND, 1, 4)) {
//                    System.out.println("Keep");
                    return;
                }
                break;
            }
            case 5: {
//                System.out.println(HAND.size() + " cards");
                if (containsCard(HAND, ALL_LAND, 1, 4)) {
//                    System.out.println("Keep");
                    return;
                }
                break;
            }
            case 4: {
//                System.out.println(HAND.size() + " cards");
                if (containsCard(HAND, ALL_LAND, 1, 4)) {
//                    System.out.println("Keep");
                    return;
                }
                break;
            }
            case 3: {
//                System.out.println(HAND.size() + " cards");
                if (containsCard(HAND, ALL_LAND, 1, 3)) {

//                    System.out.println("Keep");
                    return;
                }
                break;
            }
            default: {
//                System.out.println("Defaulted");
                return;

            }
        }
//                    System.out.println("Mull");
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
     * creature, cmc of creatures, etc. @return The number of times mulliganed.
     * For instance, if you mulled twice, you would have 5 cards in hand. This
     * returns 7-HAND.size() thus a mull to five would return 2.
     */
    static int scry() {
        if (HAND.size() < 7) {
//            System.out.println("Scry");
//            System.out.println("Hand: " + HAND);
            if (containsCard(HAND, ALL_LAND, 1, 1)) {/* if you kept at one land, scry away
anything that is not a land*/
                if (!isCard(DECK, ALL_LAND, 0)) {
//                    System.out.println("Before Scry: " + DECK);
                    scryBottom();
//                    System.out.println("After Scry: " + DECK);
                }
            } else if (containsCard(HAND, ALL_LAND, 3, 7)) {
                if (isCard(DECK, ALL_LAND, 0)) {
//                    System.out.println("Before Scry: " + DECK);
                    scryBottom();
//                    System.out.println("After Scry: " + DECK);
                }
            }
            return 7 - HAND.size();
        } else {
            return 0;
        }

    }

    static boolean firstCheck() {
        return (containsCard(HAND, ALL_LAND, 2, 4) && (containsCard(HAND, W_SHOCKS, 1, 4)
                || containsCard(HAND, FETCH_LANDS, 2, 4)));
    }

    /**
     * Take the element from the "top" of the deck and move it to the "bottom"
     */
    static void scryBottom() {
        DECK.add(DECK.get(0));
        DECK.remove(0);
    }

    /**
     * Under construction
     *
     * @param turn
     */
    static void play(int turn) {//under construction
//        System.out.println("Play");
        switch (turn) {
            case 0: {

                if (containsCard(HAND, W_SHOCKS, 1, 60)) {
//                    System.out.println("Hand: " + HAND);
                    FIELD.add(HAND.get(findCard(HAND, W_SHOCKS)));
                    HAND.remove(findCard(HAND, W_SHOCKS));
//                    System.out.println("Hand: " + HAND);
                } else if (!containsCard(HAND, W_SHOCKS, 3, 3)) {
                    FIELD.add(DECK.get(findCard(DECK, W_SHOCKS)));
                    DECK.remove(findCard(DECK, W_SHOCKS));
                    HAND.remove(findCard(HAND, FETCH_LANDS));
                    shuffle();
                }
                FIELD.add(HAND.get(findCard(HAND, STEPPE_LYNX)));
                HAND.remove(findCard(HAND, STEPPE_LYNX));
//                System.out.println("Field contains: " + FIELD);
                break;
            }
            default: {
                System.out.println("Error. Turn not valid");
                break;
            }
        }
    }

    /**
     * Takes a card and checks to see if it is in the list of cards. All cards
     * managed by ArrayLists.
     *
     * @param card ArrayList containing the card to be checked
     * @param list List of cards to be checked against
     * @param i index of card in List card.
     * @return true if the card appears in the list
     */
    static boolean isCard(List<String> card, List<String> list, int i) {
        boolean var = false;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).equals(card.get(i))) {
                var = true;
                break;
            }
        }
        return var;
    }

    /**
     * Takes a list and checks to see how many of a separate list are contained
     * within. (list contains card true/false)
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
     * returns the index of the first object in a List to be found in a second
     * List
     *
     * @param list The list of objects being searched through
     * @param card The list of objects being compared against.
     * @return the first index of list where list[index] is equal to an object
     * in card
     */
    static int findCard(List<String> list, List<String> card) {
        int index = 999;
        for (int i = 0; i < list.size(); i++) {
            if (isCard(list, card, i)) {
                index = i;
                break;
            }
        }
        if (index == 999) {
            System.out.println("Card: " + card + "not found in " + list + "\n\n");
        }
        return index;
    }
}

package stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author James Cannon
 * @version 13 June 2016 4:00 P.M.
 */
public class Deck {

    private static final Random RNG = new Random();//used in shuffle()
    public static final List<String> DECK = new ArrayList<>();//ArrayList for Deck
    public static final List<String> HAND = new ArrayList<>();//ArrayList for Hand
    public static final List<String> FETCH_LANDS = new ArrayList<>();
    public static final List<String> W_SHOCKS = new ArrayList<>();

    static void initLists() {
        FETCH_LANDS.add("Arid Mesa");
        FETCH_LANDS.add("Bloodstained Mire");
        FETCH_LANDS.add("Flooded Strand");
        FETCH_LANDS.add("Marsh Flats");
        FETCH_LANDS.add("Misty Rainfores");
        FETCH_LANDS.add("Polluted Delta");
        FETCH_LANDS.add("Verdant Catacombs");
        FETCH_LANDS.add("Windswept Heath");
        FETCH_LANDS.add("Wooded Foothills");
        W_SHOCKS.add("Godless Shrine");
        W_SHOCKS.add("Hallowed Fountain");
        W_SHOCKS.add("Temple Garden");
        W_SHOCKS.add("Sacred Foundry");
    }

    /**
     * Make sure the hand and deck are void, then add the decklist to the game
     */
    static void initGame() {
        HAND.clear();
        DECK.clear();
        DECK.add("Arid Mesa");
        DECK.add("Arid Mesa");
        DECK.add("Bloodstained Mire");
        DECK.add("Bloodstained Mire");
        DECK.add("Bloodstained Mire");
        DECK.add("Bloodstained Mire");
        DECK.add("Forest");
        DECK.add("Plains");
        DECK.add("Sacred Foundry");
        DECK.add("Sacred Foundry");
        DECK.add("Stomping Grounds");
        DECK.add("Stomping Grounds");
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
    static void resolveMulligans() {
//        System.out.println("Resolve Mulligans");
        switch (HAND.size()) {
            case 7: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(2, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(6);
                    resolveMulligans();
                    break;
                }
            }
            case 6: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(5);
                    resolveMulligans();
                    break;
                }
            }
            case 5: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(4);
                    resolveMulligans();
                    break;
                }
            }
            case 4: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 4)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(3);
                    resolveMulligans();
                    break;
                }
            }
            case 3: {
//                System.out.println(cardsInHand() + " cards");
                if (hContainsLand(1, 3)) {
//                    System.out.println("Keep");
                    break;
                } else {
//                    System.out.println("Mull");
                    initGame();
                    shuffle();
                    draw(2);
                    resolveMulligans();
                    break;
                }
            }
            default: {
//                System.out.println("Defaulted");
                break;
            }

        }

    }

    /**
     * scry() looks at the hand size, determines if a mulligan has been taken
     * (hand.size() < 7) and then performs various checks to determine whether
     * to keep the top card of the deck on top or move it to the bottom. TODO
     * apply more logic and more cases to this. Include options for creature,
     * cmc of creatures, etc. @return The number of times mulliganed. For
     * instance, if you mulled twice, you would have 5 cards in hand. This
     * returns 7-HAND.size() thus a mull to five would return 2.
     */
    static int scry() {
        if (HAND.size() < 7) {
            //System.out.println("Scry");
            if (hContainsLand(1, 1)) {/* if you kept at one land, scry away 
anything that is not a land*/
                if (!isLand(DECK, 0)) {
//                    System.out.println("Before Scry: " + deck);
                    scryBottom();
//                    System.out.println("After Scry: " + deck);
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
        DECK.add(DECK.get(0));
        DECK.remove(0);
    }

    /**
     * hContainsLand checks whether the HAND contains a number of lands between
     * lower and upper (inclusive)
     *
     * @param lower the lowest number of lands acceptable
     * @param upper the highest number of lands acceptable
     * @return TRUE if the number of lands in the hand falls between lower and
     * upper (inclusive)
     */
    static boolean hContainsLand(int lower, int upper) {
        int landCount = 0;
        for (int i = 0; i < HAND.size(); i++) {
            if (isLand(HAND, i)) {
                landCount++;
            }
        }
//        System.out.println("Hand contains: " + hand + "\nLand Count: " + landCount);
        return (landCount >= lower && landCount <= upper);
    }

    /**
     * Checks a list element against a predetermined list of creatures
     *
     * @param list The list containing the element being checked
     * @param i the index of the element being checked
     * @return TRUE if the element matches one of the predetermined list
     */
    static boolean isCreature(List<String> list, int i) {
        return ("Burning-Tree Emissary".equals(list.get(i))) || ("Reckless Bushwhacker".equals(list.get(i)))
                || ("Experiment One".equals(list.get(i))) || ("Ghor-Clan Rampager".equals(list.get(i)))
                || ("Goblin Guide".equals(list.get(i))) || ("Kird Ape".equals(list.get(i)))
                || ("Steppe Lynx".equals(list.get(i))) || ("Wild Nacatl".equals(list.get(i)));
    }

    /**
     * Checks a list element against a predetermined list of lands. Calls the
     * similar function isFetch() as all fetch lands are lands
     *
     * @param list The list containing the element being checked
     * @param i the index of the element being checked
     * @return TRUE if the element matches one of the predetermined list
     */
    static boolean isLand(List<String> list, int i) {
        return ("Forest".equals(list.get(i))) || (isCard(list, FETCH_LANDS, i))
                || ("Plains".equals(list.get(i))) || ("Sacred Foundry".equals(list.get(i)))
                || ("Stomping Grounds".equals(list.get(i))) || ("Temple Garden".equals(list.get(i)));
    }

    /**
     * Under construction
     *
     * @param turn
     */
    static void play(int turn) {//under construction
        switch (turn) {
            case 0: {
                if (containsCard(HAND, W_SHOCKS, 1, 60)
                        && containsCard(HAND, FETCH_LANDS, 1, 2)) {
//                    System.out.println("Hand: " + HAND);
                    HAND.remove(findCard(HAND, W_SHOCKS));
//                    System.out.println("Hand: " + HAND + "\n\n");
                }
                if (containsCard(HAND, FETCH_LANDS, 3, 6)) {
                    DECK.remove(findCard(DECK, W_SHOCKS));
                }
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
     * within
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

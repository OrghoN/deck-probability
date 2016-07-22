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
 * @version 20 July 2016 4:00 P.M.
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
    public static final List<String> STEPPE_LYNX = new ArrayList<>();
    public static final List<String> STOMPING_GROUND = new ArrayList<>();
    public static final List<String> TEMPLE_GARDEN = new ArrayList<>();
    public static final List<String> SACRED_FOUNDRY = new ArrayList<>();
    public static final List<String> BLOODSTAINED_MIRE = new ArrayList<>();
    public static final List<String> FIELD = new ArrayList<>();
    public static final List<Integer> POWER = new ArrayList<>();
    private static final ArrayList<String> INOPENING = new ArrayList<>();//List of cards required in opening
    private static final ArrayList<String> FIRSTDRAW = new ArrayList<>();//List of cards required in first draw
    public static boolean initDeck = false;

    static void initLists() throws FileNotFoundException {
        INOPENING.add("Steppe Lynx");//init list cards required
        FIRSTDRAW.add("Atarka's Command");
        STEPPE_LYNX.add("Steppe Lynx");
        STOMPING_GROUND.add("Stomping Ground");
        TEMPLE_GARDEN.add("Temple Garden");
        SACRED_FOUNDRY.add("Sacred Foundry");
        BLOODSTAINED_MIRE.add("Bloodstained Mire");
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
        switch (HAND.size()) {
            case 7: {
                if (containsCard(HAND, ALL_LAND, 2, 4)
                        && (containsCard(HAND, CREATURES, 2, 5) || containsCard(HAND, STEPPE_LYNX, 1, 1))) {
                    return;
                }
                break;
            }
            case 6: {
                if (containsCard(HAND, ALL_LAND, 1, 4) && containsCard(HAND, CREATURES, 1, 5)) {
                    return;
                }
                break;
            }
            case 5: {
                if (containsCard(HAND, ALL_LAND, 1, 4)) {
                    return;
                }
                break;
            }
            case 4: {
                if (containsCard(HAND, ALL_LAND, 1, 4)) {
                    return;
                }
                break;
            }
            case 3: {
                if (containsCard(HAND, ALL_LAND, 1, 3)) {
                    return;
                }
                break;
            }
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

    static boolean pHand() {
        return (HAND.containsAll(INOPENING) && containsCard(HAND, ALL_LAND, 2, 6)
                && containsCard(HAND, FETCH_LANDS, 1, 6)
                && (containsCard(Deck.HAND, W_SHOCKS, 1, 6)
                || containsCard(Deck.HAND, FETCH_LANDS, 2, 6)));
    }

    static boolean pGame() {
        return (HAND.containsAll(FIRSTDRAW) && containsCard(HAND, FETCH_LANDS, 2, 6));
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
//        System.out.println("Field = " + FIELD);
//        System.out.println("Hand = " + HAND);
        int landfall = playLand();
//        System.out.println("Play Land");
        int mana = calcMana();
//        System.out.println("Hand = " + HAND);
//        System.out.println("Field = " + FIELD + "\nMana = " + mana);
        mana = playCreature(mana);
        int power = calcPower(landfall);
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

    /**
     *
     *
     * @return
     */
    static int playLand() {
        int landfall = 0;
        if (containsCard(HAND, ALL_LAND, 1, HAND.size())) {//make sure the hand has land in it
            if (!containsCard(FIELD, ALL_LAND, 1, FIELD.size())) {//if there are zero lands on the field
                if (pHand()) {
                    if (containsCard(HAND, W_SHOCKS, 1, HAND.size())) {
                        FIELD.add(HAND.get(findCard(HAND, W_SHOCKS)));
                        HAND.remove(findCard(HAND, W_SHOCKS));
                        landfall = 1;
                    } else {
                        if (!containsCard(HAND, BLOODSTAINED_MIRE, 1, HAND.size())) {
                            FIELD.add(DECK.get(findCard(DECK, TEMPLE_GARDEN)));
                            DECK.remove(findCard(DECK, TEMPLE_GARDEN));
                        } else {
                            FIELD.add(DECK.get(findCard(DECK, SACRED_FOUNDRY)));
                            DECK.remove(findCard(DECK, SACRED_FOUNDRY));
                        }
                        HAND.remove(findCard(HAND, FETCH_LANDS));
                        shuffle();
                        landfall = 2;
                    }
                } else if (containsCard(HAND, STOMPING_GROUND, 1, HAND.size())) {
                    FIELD.add(HAND.get(findCard(HAND, STOMPING_GROUND)));
                    HAND.remove(findCard(HAND, STOMPING_GROUND));
                    landfall = 1;
                } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
                    FIELD.add(DECK.get(findCard(DECK, STOMPING_GROUND)));
                    DECK.remove(findCard(DECK, STOMPING_GROUND));
                    HAND.remove(findCard(HAND, FETCH_LANDS));
                    shuffle();
                    landfall = 2;
                } else if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                    FIELD.add(HAND.get(findCard(HAND, SHOCK_LANDS)));
                    HAND.remove(findCard(HAND, SHOCK_LANDS));
                    landfall = 1;
                } else {
                    FIELD.add(HAND.get(findCard(HAND, ALL_LAND)));
                    HAND.remove(findCard(HAND, ALL_LAND));
                    landfall = 1;
                }
            } else if (containsCard(FIELD, ALL_LAND, 1, FIELD.size())) {//if there are 2 or more lands on the field
                if (containsCard(FIELD, G_LANDS, 1, FIELD.size())
                        && containsCard(FIELD, R_LANDS, 1, FIELD.size())
                        && containsCard(FIELD, W_LANDS, 1, FIELD.size())) {
                    if (containsCard(HAND, STOMPING_GROUND, 1, HAND.size())) {
                        FIELD.add(HAND.get(findCard(HAND, STOMPING_GROUND)));
                        HAND.remove(findCard(HAND, STOMPING_GROUND));
                        landfall = 1;
                    } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
                        if (containsCard(DECK, STOMPING_GROUND, 1, DECK.size())) {
                            FIELD.add(DECK.get(findCard(DECK, STOMPING_GROUND)));
                            DECK.remove(findCard(DECK, STOMPING_GROUND));
                        } else {
                            FIELD.add(DECK.get(findCard(DECK, SHOCK_LANDS)));
                            DECK.remove(findCard(DECK, SHOCK_LANDS));
                        }
                        HAND.remove(findCard(HAND, FETCH_LANDS));
                        shuffle();
                        landfall = 2;
                    } else if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                        FIELD.add(HAND.get(findCard(HAND, SHOCK_LANDS)));
                        HAND.remove(findCard(HAND, SHOCK_LANDS));
                        landfall = 1;
                    } else {
                        FIELD.add(HAND.get(findCard(HAND, ALL_LAND)));
                        HAND.remove(findCard(HAND, ALL_LAND));
                        landfall = 1;
                    }
                } else if ((!containsCard(FIELD, G_LANDS, 1, FIELD.size()))
                        || (!containsCard(FIELD, R_LANDS, 1, FIELD.size()))) {
                    if (containsCard(HAND, STOMPING_GROUND, 1, HAND.size())) {
                        FIELD.add(HAND.get(findCard(HAND, STOMPING_GROUND)));
                        HAND.remove(findCard(HAND, STOMPING_GROUND));
                        landfall = 1;
                    } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
                        FIELD.add(DECK.get(findCard(DECK, STOMPING_GROUND)));
                        DECK.remove(findCard(DECK, STOMPING_GROUND));
                        HAND.remove(findCard(HAND, FETCH_LANDS));
                        shuffle();
                        landfall = 2;
                    } else if (!containsCard(FIELD, G_LANDS, 1, FIELD.size())) {
                        if (containsCard(HAND, G_LANDS, 1, HAND.size())) {
                            FIELD.add(HAND.get(findCard(HAND, G_LANDS)));
                            HAND.remove(findCard(HAND, G_LANDS));
                            landfall = 1;
                        } else {
                            if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                                FIELD.add(HAND.get(findCard(HAND, SHOCK_LANDS)));
                                HAND.remove(findCard(HAND, SHOCK_LANDS));
                            } else {
                                FIELD.add(HAND.get(findCard(HAND, ALL_LAND)));
                                HAND.remove(findCard(HAND, ALL_LAND));
                            }
                            landfall = 1;
                        }
                    } else if (containsCard(HAND, R_LANDS, 1, HAND.size())) {
                        FIELD.add(HAND.get(findCard(HAND, R_LANDS)));
                        HAND.remove(findCard(HAND, R_LANDS));
                        landfall = 1;

                    } else {
                        if (containsCard(HAND, SHOCK_LANDS, 1, HAND.size())) {
                            FIELD.add(HAND.get(findCard(HAND, SHOCK_LANDS)));
                            HAND.remove(findCard(HAND, SHOCK_LANDS));
                        } else {
                            FIELD.add(HAND.get(findCard(HAND, ALL_LAND)));
                            HAND.remove(findCard(HAND, ALL_LAND));
                        }
                        landfall = 1;
                    }
                } else if (containsCard(HAND, W_SHOCKS, 1, HAND.size())) {
                    FIELD.add(HAND.get(findCard(HAND, W_SHOCKS)));
                    HAND.remove(findCard(HAND, W_SHOCKS));
                    landfall = 1;
                } else if (containsCard(HAND, FETCH_LANDS, 1, HAND.size())) {
                    FIELD.add(DECK.get(findCard(DECK, W_SHOCKS)));
                    DECK.remove(findCard(DECK, W_SHOCKS));
                    HAND.remove(findCard(HAND, FETCH_LANDS));
                    shuffle();
                    landfall = 2;
                } else {
                    FIELD.add(HAND.get(findCard(HAND, ALL_LAND)));
                    HAND.remove(findCard(HAND, ALL_LAND));
                    landfall = 1;
                }
            }
        }
        return landfall;
    }

    static int calcMana() {
        int count = 0;
        for (int i = 0; i < FIELD.size(); i++) {
            if (isCard(FIELD, ALL_LAND, i)) {
                count++;
            }
        }
        return count;
    }

    static int playCreature(int mana) {
        return mana;
    }

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
                    }

                }

            }
        }

        return power;
    }
}

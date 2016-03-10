package deckprobability;

/**
 *
 * @author James Cannon
 * @version 10 March 2016 - 9:16 A.M.
 */
public class DeckProbability {

    /**
     * 
     * @param n see return
     * @return n!
     */
    public static double factorial(int n) {
        if (n == 0) {
            return 1;
        }//if
        else {
            return n * factorial(n - 1);
        }//else
    }//factorial(int)

    /**
     * 
     * @param n see return
     * @param r see return
     * @return nPr (n permutations r)
     */
    public static double permutations(int n, int r) {
        return factorial(n) / factorial(n - r);
    }//permutations(int, int)

    /**
     * 
     * @param n see return
     * @param r see return
     * @return nCr (n choose r)
     */
    public static double combinations(int n, int r) {
        return permutations(n, r) / factorial(r);
    }//combinations(int, int)

    public static void main(String[] args) {
        int fullGroup = 10;
        int numOfDesired = 5;
        int selections = 3;
        System.out.println(combinations(numOfDesired, selections));
        System.out.println(combinations(fullGroup, selections));
        System.out.println(combinations(numOfDesired, selections)
                / combinations(fullGroup, selections));
    }//main(String)

}//DeckProbabilty 

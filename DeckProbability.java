package deckprobability;

/**
 *
 * @author James Cannon
 * @version 7 March 2016 - 9:16 P.M.
 */
public class DeckProbability 
{
    
    private static double permutations(int n, int r){
        return factorial(n)/factorial(n-r);
    }
    
    private static double combinations(int n, int r){
        return permutations(n,r)/factorial(r);
    }
    
    private static double factorial(int n){
        if (n == 0){
            return 1;
        }//if
        else{
            return n * factorial(n-1);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int fullGroup = 10;
        int numOfDesired = 5;
        int selections = 3;
        System.out.println(combinations(numOfDesired,selections));
        System.out.println(combinations(fullGroup,selections));
        System.out.println(combinations(numOfDesired,selections)/combinations(fullGroup,selections));
    }

}

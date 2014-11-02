
package Data2;


public class Data2 {
    
    public static boolean hasOdds(Iterator it){
        boolean anyOdds = false;
        try{
            Iterator iter = it;
            
            while(!anyOdds){
                Integer current = (Integer)iter.here();
                anyOdds = current%2 == 1;
                iter = iter.next();
            }
        }
        catch(NothingHere e){
        }
            return anyOdds;
    }
    
    public static Comparable longest(Iterator it){
        Comparable longest = 0;
        Iterator iter = it;
        try{
        longest = iter.here();
            while(true){
            if(longest.compareTo(iter.here()) > 0){
                longest = iter.here();
            }
            iter = iter.next();
            }
        }
        catch(NothingHere e){
        }
            return longest;
    }

   
    public static void main(String[] args) {
       Testers.testHasOdds(20);
       Testers.testNoFilledBagsAndEmptyHuhAndRemoveAll(1);
    }
    
}



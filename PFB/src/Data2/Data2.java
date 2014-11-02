
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

   
    public static void main(String[] args) {
       Testers.testHasOdds(20);
       Testers.testNoFilledBagsAndEmptyHuhAndRemoveAll(1);
    }
    
}



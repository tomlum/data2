
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
        Comparable longest = "Nothing in the Iterator";
        Iterator iter = it;
        try{
        longest = iter.here();
            while(iter.anythingHere()){
            if(iter.here().toString().length() > longest.toString().length()){
                longest = iter.here();
            }
            iter = iter.next();
            }
        }
        catch(NothingHere e){
        }
            return longest;
    }
    
    public static int iterLength(Iterator it){
        Comparable longest = "Nothing in the Iterator";
        int count = 0;
        Iterator iter = it;
        try{
            while(iter.anythingHere()){
            count++;
            iter = iter.next();
            }
            
        }
        catch(NothingHere e){
        }
            return count;
    }

   
    public static void main(String[] args) {
       Testers.testHasOdds(20);
       Testers.testNoFilledBagsAndEmptyHuhAndRemoveAllAndCountOf(10);
       Testers.testLongest(20);
       Testers.testIterLength(100);
    }
    
}



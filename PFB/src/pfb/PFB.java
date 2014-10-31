
package pfb;


public class PFB {
    
    public static boolean hasOdds(Iterator it){
        boolean anyOdds = false;
        try{
            Integer current = (Integer)it.here();
            anyOdds = (Integer)it.here()%2 == 1;
            
            while(it.hasNext() && !anyOdds){
                anyOdds = (Integer)it.here()%2 == 1;
                current = (Integer)it.next();
            }
        }
        catch(NothingHere e){
        }
            return anyOdds;
    }

   
    public static void main(String[] args) {
        Testers.testHasOdds(30);
    }
    
}



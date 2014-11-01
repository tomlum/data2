package Data2;
import java.util.Random;

public class Testers {
    
    public static void printSequence(Iterator it){
        Iterator iter = it;
        System.out.println(iter.hasNext());
        try{
        while(iter.hasNext()){
        System.out.println(iter.here());
        iter = iter.next();
        }
        System.out.println(iter.here());
        }
        catch(NothingHere e){
            System.out.println("Nothing Here");
        }
    }
    
    public static void testHasOdds(int trials){
        for(int i = 0; i < trials; i++){
            FinSet theOddTree = randomBST(1, randomInt(1,20));
            FinSet theEvenTree = randomBST(2, randomInt(1,20));
            PFB<Integer> theOddBag = randomPFB(1, randomInt(1,20));
            PFB<Integer> theEvenBag = randomPFB(2, randomInt(1,20));
            if(!Data2.hasOdds(theOddTree)||Data2.hasOdds(theEvenTree)){
                System.out.println("Error in HasOdds, Trees");
            }
            if(!Data2.hasOdds(theOddBag)||Data2.hasOdds(theEvenBag)){
                System.out.println("Error in HasOdds, Bags");
            }
            if(theOddTree.isEmptyHuh()||theEvenTree.isEmptyHuh()){
                 System.out.println("Random Sets are Emtpy");
            }
            if(theOddBag.isEmptyHuh()||theEvenBag.isEmptyHuh()){
                 System.out.println("Random Bags are Emtpy");
            }
        }
    }
    
    
    public static FinSet randomBST(int type, int size){
        FinSet tree = new Leaf();
        for(int i = 0; i < size; i++){
            int toAdd = randomOddOrEven(type, 20);
            tree = tree.add(toAdd);
            //System.out.println(toAdd);
        }
        return tree;
    }
    
    public static PFB randomPFB(int type, int size){
        PFB bag = new PFBLeaf();
        for(int i = 0; i < size; i++){
            int toAdd = randomOddOrEven(type, 20);
            bag = bag.add(toAdd, randomInt(1,5));
            //System.out.println(toAdd);
        }
        return bag;
    }
    
    static Random rand = new Random();
    public static int randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    public static int randomOddOrEven(int type, int range){
        int theInt = randomInt(0, range-1);
        if((type==1&&theInt%2==0)||(type==2&&theInt%2!=0)){
            theInt++;
        }
        return theInt;
    }
    
}

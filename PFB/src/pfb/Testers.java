package pfb;
import java.util.Random;

public class Testers {
    public static void testHasOdds(int trials){
        for(int i = 0; i < trials; i++){
            RegularBiTr theOddTree = randomPFB(1, randomInt(0,20));
            RegularBiTr theEvenTree = randomPFB(2, randomInt(0,20));
            if(PFB.hasOdds(theOddTree)||PFB.hasOdds(theEvenTree)){
                System.out.println("Error in HasOdds");
            }
        }
    }
    
    public static RegularBiTr randomPFB(int type, int size){
        RegularBiTr tree = new RegularLeaf();
        for(int i = 0; i < size; i++){
            int toAdd = randomOddOrEven(type, 20);
            tree.add(toAdd);
            //System.out.println(toAdd);
        }
        return tree;
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

package Data2;
import java.util.Random;

public class Testers {
    
    public static void testRBInvars(int trials, int maxsize){
        for(int i = 0; i < trials; i++){
            PFB theBag = new PFBLeaf();
            for(int j = 0; j < maxsize; j++){
            theBag = theBag.add(randomInt(1,100));
            }
            boolean rbInvar2 = true;
            int arbitraryBCount = theBag.ranPathBCount();
            for(int j = 0; j < maxsize; j++){
                if(arbitraryBCount!=theBag.ranPathBCount()){
                    rbInvar2 = false;
                }
            }
            if(!theBag.rbInvar1()||!rbInvar2){
                PFBBranch elBag = (PFBBranch)theBag;
                System.out.println("Error in PFB RB Invars");
            }
        }
    }
    
    public static void printSequence(Iterator it){
        Iterator iter = it;
        try{
            while(iter.anythingHere()){
        System.out.println(iter.here());
        iter = iter.next();
        }
        }
        catch(NothingHere e){
            System.out.println("caught");
        }
    }
   
    
    public static void testHasOdds(int trials){
        for(int i = 0; i < trials; i++){
            FinSet theOddTree = randomBST(1, randomInt(1,20));
            FinSet theEvenTree = randomBST(2, randomInt(1,20));
            PFB theOddBag = randomPFB(1, randomInt(1,20));
            PFB theEvenBag = randomPFB(2, randomInt(1,20));
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
    
    public static void testLongest(int trials){
        for(int i = 0; i < trials; i++){
            FinSet theTree = new Leaf();
            PFB<Integer> theBag = new PFBLeaf();
            PFB<String> theBag2 = new PFBLeaf();
            int size = randomInt(0,30);
            for(int j = 0; j < size; j++){
            Integer toAdd = randomInt(1,20);
            String toAdd2 = randomString(1,20);
            theTree = theTree.add(toAdd);
            theBag = theBag.add(toAdd, 10);
            theBag2 = theBag2.add(toAdd2, 10);
            }
            Integer toAddMax = randomInt(100,140);
            String toAddMax2 = randomString(21,40);
            theTree = theTree.add(toAddMax);
            theBag = theBag.add(toAddMax);
            theBag2 = theBag2.add(toAddMax2);
            
            if(!Data2.longest(theBag).equals(toAddMax)||
                    !Data2.longest(theBag2).equals(toAddMax2)||
                    !Data2.longest(theTree).equals(toAddMax)){
                System.out.println("Error in add/Longest");
            }
        }
    }
    
    
    public static void testIterLength(int trials){
        for(int i = 0; i < trials; i++){
            FinSet theTree = new Leaf();
            PFB theBag = new PFBLeaf();
            PFB theBag2 = new PFBLeaf();
            int size = randomInt(0,30);
            for(int j = 0; j < size; j++){
            int toAdd = randomInt(1,20);
            String toAdd2 = randomString(1,20);
            theTree = theTree.add(toAdd);
            theBag = theBag.add(toAdd, 10);
            theBag2 = theBag2.add(toAdd2, 10);
            }
            
            if(Data2.iterLength(theBag)!=theBag.cardinality()||
                    Data2.iterLength(theBag2)!=theBag2.cardinality()||
                    Data2.iterLength(theTree)!=theTree.cardinality()){
                System.out.println("Error iterLength");
            }
        }
    }
    
    
    public static void testCountMethods(int trials){
        for(int i = 0; i < trials; i++){
            PFB theBag = new PFBLeaf();
            int size = randomInt(1,30);
            for(int j = 0; j < size; j++){
            int toAdd = randomInt(1,50);
            int countToAdd = randomInt(1,20);
            theBag = theBag.add(toAdd, countToAdd);
            if(!theBag.member(toAdd) || theBag.countOf(toAdd)!=countToAdd){
                System.out.println("error in member/countof");
            }
            theBag = theBag.removeAll(toAdd);
            
            if(theBag.cardinality()==0 || theBag.eltCount()!=0){
                System.out.println("error in cardinality/eltCount");
            }
            theBag = theBag.removeAll(toAdd);
            
            }
            if(!theBag.noFilledBags()||theBag.isEmptyHuh()){
                System.out.println("Error in NFB/EmptyHuh/RemoveAll");
            }
        }
    }
    
    
    public static FinSet randomBST(int type, int size){
        FinSet tree = new Leaf();
        for(int i = 0; i < size; i++){
            int toAdd = randomOddOrEven(type, 100);
            tree = tree.add(toAdd);
            //System.out.println(toAdd);
        }
        return tree;
    }
    
    public static PFB randomPFB(int type, int size){
        PFB bag = new PFBLeaf();
        for(int i = 0; i < size; i++){
            int toAdd = randomOddOrEven(type, size);
            bag = bag.add(toAdd, randomInt(1,5));
            //System.out.println(toAdd);
        }
        //System.out.println("_");
        return bag;
    }
    
    public static PFB randomStringPFB(int size){
        PFB bag = new PFBLeaf();
        for(int i = 0; i < size; i++){
            String toAdd = randomString(1,20);
            bag = bag.add(toAdd, randomInt(1,5));
        }
        return bag;
    }
    
    public static String randomString(int min, int max){
        StringBuffer temp = new StringBuffer();
        int length = randomInt(min,max);
        for(int i = 0; i < length; i++){
            temp.append(Character.toChars(randomInt(65, 90)));
        }
        return temp.toString();
    }
    
    static Random rand = new Random();
    public static Integer randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    public static int randomOddOrEven(int type, int range){
        int theInt = randomInt(0, range-1);
        if((type==1&&theInt%2==0)||(type==2&&theInt%2!=0)){
            theInt++;
        }
        return theInt;
    }
    
    public static void checkSubsetTransitivity(int reps){
        for(int i = 0; i < reps; i++){
            
        PFB RandomBag = randomPFB(0, 10);
        PFB RandomBag2 = randomPFB(0, 100);
        PFB RandomBag3 = randomPFB(0, 500);
        
          
        if((RandomBag.subset(RandomBag2) && RandomBag2.subset(RandomBag3))&&!(RandomBag.subset(RandomBag3))){
            System.out.println("Back to the Drawing Board Tom");
        }
        
        }
    }
    
    public static void checkProperty4(int reps, int range){
        for(int i = 0; i < reps; i++){
            
        PFB RandomTree1 = randomPFB(0,range);
        PFB RandomTree2 = randomPFB(0,range);
        PFB InterTree = RandomTree1.inter(RandomTree2);
        PFB UnionTree = RandomTree1.union(RandomTree2);
        PFB DiffTree = RandomTree1.diff(RandomTree2);
        PFB DiffTree2 = RandomTree2.diff(RandomTree1);
        
        
        for(int j = 0; j <= range; j++){
        if(!DiffTree.union(DiffTree2).union(InterTree).equal(UnionTree)){
            System.out.println("Back to the Drawing Board Tom, fix checkProperty4");
        }
        if(!DiffTree.union(DiffTree2).union(InterTree).member(j) == UnionTree.member(j)){
            System.out.println("Back to the Drawing Board Tom, fix checkProperty4");
        }
        }
        
        }
    }
    
    
     public static void checkProperty6(int reps, int range){
        for(int i = 0; i < reps; i++){
            
        PFB RandomTree1 = randomPFB(0,range);
        PFB RandomTree2 = randomPFB(0,range);
        PFB RandomTree3 = randomPFB(0,range);
        PFB InterTree12 = RandomTree1.inter(RandomTree2);
        PFB InterTree23 = RandomTree2.inter(RandomTree3);
        PFB InterTree13 = RandomTree1.inter(RandomTree3);
        PFB InterUnionTree123 = InterTree12.union(InterTree13.union(InterTree23));
        PFB InterTree123 = InterTree12.inter(InterTree13.inter(InterTree23));
        
        
        for(int j = 0; j <= range; j++){
        if(InterTree123.member(j) && !(
                InterTree12.inter(InterTree13).member(j)
                && InterTree12.inter(InterTree23).member(j)
                && InterTree13.inter(InterTree23).member(j)
                
                )){
            System.out.println("Back to the Drawing Board Tom, fix checkProperty3");
        }
        }
        
        
        }
    }
    
    
}

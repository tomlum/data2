import java.util.Random;

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
       Testers.testCountMethods(10);
       Testers.testLongest(20);
       Testers.testIterLength(100);
       Testers.testRBInvars(100, 100);
       Testers.checkSubsetTransitivity(100);
       Testers.checkProperty4(100, 100);
       Testers.checkProperty6(100, 100);
    }
    
}



public class NothingHere extends Exception {

    public NothingHere() {
    }

    public NothingHere(String msg) {
        super(msg);
    }
}



public interface Iterator<T extends Comparable<T>> {
    public T here() throws NothingHere;
    public boolean anythingHere();
    public Iterator next() throws NothingHere;
}



public class Trunk implements Iterator{
    Iterator le;
    Iterator ri;
    
    public Trunk(Iterator l, Iterator r){
        le = l;
        ri = r;
    }
    
    public boolean anythingHere(){
        return this.le.anythingHere()||this.ri.anythingHere();
    }
    
    public Comparable here() throws NothingHere{
        if(this.le.anythingHere()){
            return this.le.here();
        }
        return this.ri.here();
    }
    
    public Iterator  next() throws NothingHere{
        if(this.le.anythingHere()){
            return new Trunk(this.le.next(), this.ri);
        }
        return new Trunk(this.le, this.ri.next());
    }
}



 /**
     *Extends - Iterator
     *<br>All methods create new instances and do not mutate old ones
     *<br>Performance:
     *<br>  Best: logarithmic O(log n)
     *<br>  Worst: linear O(n)
     */

public interface FinSet extends Iterator{
    /**
     *@return the cardinality of this
     *this.cardinality() > 0 <-> not this.isEmptyHuh()
     */
    public int cardinality();
    /**
     *@return if this is empty
     *not this.isEmptyHuh() <-> this.cardinality() > 0
     */
    public boolean isEmptyHuh();
    /**
     *@param elt the to search for
     *@return if elt is within this
     *this.add(elt).memeber(elt) -> true
     */
    public boolean member(int elt);
    /**
     *@param elt the to add
     *@return adds elt to this
     *this.add(elt) -> this.member(elt)
     */
    public FinSet add(int elt);
    /**
     *@param elt the to remove
     *@return removes elt from this
     *this.remove(elt) -> not this.member(elt)
     */
    public FinSet remove(int elt);
    /**
     *@param u the PFB to iunion
     *@return the union of This and u
     */
    public FinSet union(FinSet u);
    /**
     *@param u the PFB to inter
     *@return the intersection of this and u
     *a.union(b) == b.union(a) -> a.equal(b) 
     */
    public FinSet inter(FinSet u);
    /**
     *@param u the FinSet to diff
     *@return the difference of this and u
     *a.diff(b) == PFBLeaf() -> a.equal(b)
     */
    public FinSet diff(FinSet u);
    /**
     *@param u the FinSet to check for equality
     *@return if this and u are equal 
     *a.equal(b) -> a.cardinality() == b.cardinality()
     */
    public boolean equal(FinSet u);
    /**
     *@param u the FinSet to check for subset
     *@return if this is a subset of u
     *a.subset(b) -> a.cardinality() less than or equal to b.cardinality()
     */
    public boolean subset(FinSet u);
}




/**
     *Extends - Iterator
     * <br> All methods create new instances and do not mutate old ones
     * <br> Performance:
     * <br>   Best: logarithmic O(log n) 
     * <br>   Worst: logarithmic O(log n)
     */

public interface PFB<T extends Comparable<T>> extends Iterator{
    /**
     *@return if there are no Branches below this
     *@see #noFilledBags()
     *not this.isEmptyHuh() <-> this.cardinality() > 0
     */
    public boolean isEmptyHuh();
    /**
     *@return if there are no PFBs that hold a quantity of 0 elements in this
     *@see #isEmptyHuh()
     *not this.noFilledBags() <-> this.eltCount() > 0
     */
    public boolean noFilledBags();
    /**
     *@return the left PFB of this
     *@throws NothingHere throws if there is no PFB there
     *not this.isEmptyHuh() -> this.left() == this.left
     */
    public PFB left()throws NothingHere;
    /**
     *@return the right PFB of this
     *@throws NothingHere throws if there is no PFB there
     *not isEmptyHuh -> this.right() == this.right
     */
    public PFB right()throws NothingHere;
    /**
     *@return if this is red
     *this.redHuh() -> this.red
     */
    public boolean redHuh();
    /**
     *@return if the 1st RedBlack invariant holds true for this: 
     *There can never be a red node with a red child
     *rbInvar1 == true -> RB Tree representation invariant is preserved
     */
    public boolean rbInvar1();
    /**
     *@return the quantity of PFBs with quantities greater than 0 within this
     *eltCount() > 0 <-> not noFilledBags
     *@see #cardinality() 
     */
    public int eltCount();
    /**
     *@param elt the to search for
     *@return if elt is within this and has a quantity greater than 0
     *this.add(elt).memeber(elt) -> true
     */
    public boolean member(T elt);
    /**
     *@param elt the element to search for
     *@return the quantity of elt within this
     * this.add(elt, 3).countOf(elt) -> 3 
     */
    public int countOf(T elt);
    /**
     *@return how many PFBs (filled or not) are within this
     *@see #eltCount() () 
     *this.cardinality() > 0 <-> not this.isEmptyHuh()
     * 
     */
    public int cardinality();
    /**
     *@param elt the element to add
     *@param count the quantity of elt to add
     *@return adds elt with quantity count to this
     *this.add(elt, count) -> this.member(elt)&&this.countOf(elt) >= count
     *this.add(elt, count) -> not this.redHuh()
     */
    public PFB add(T elt, int count);
    /**
     *@param elt the element to add
     *@return adds elt with quantity 1 to this
     *this.add(elt) -> this.member(elt)&&this.countOf(elt) >= 1
     *this.add(elt) -> not this.redHuh()
     */
    public PFB add(T elt);
    /**
     *@param elt the element to add
     *@param count the quantity of elt to add
     *@return adds elt with quantity count to this
     *This is a helper function that should only be called with add
     *@see #add(java.lang.Comparable) 
     *this.ad(elt, count) -> this.member(elt)&&this.countOf(elt) >= count
     */
    public PFB ad(T elt, int count);
    /**
     *@param elt the element to remove
     *@param c the quantity of elt to remove
     *@return reduces the quantity of elt within this by c
     *@see removeAll
     *this.remove(elt, this.countOf(elt)) -> not this.member(elt)&&this.countOf(elt) == 0
     */
    public PFB remove(T elt, int c);
    /**
     *@param elt the element to remove
     *@return reduces the quantity of elt within this to 0
     *@see remove
     *this.removeAll(elt) -> not this.member(elt)&&this.countOf(elt) == 0
     */
    public PFB removeAll(T elt);
    /**
     *@return the number of black PFBs along a random path along this
     *this.ranPathBCount == this.ranPathBCount -> RB Tree representation invariant is preserved
     */
    public int ranPathBCount();
    /**
     *@param u the PFB to union
     *@return the union of this and u
     *a.union(b) == b.union(a) -> a.equal(b) 
     */
    public PFB union(PFB u);
    /**
     *@param u the PFB to inter
     *@return the intersection of this and u
     *a.inter(b) == a -> a.equal(b)
     */
    public PFB inter(PFB u);
    /**
     *@param u the PFB to diff
     *@return the difference of this and u
     *a.diff(b) == PFBLeaf() -> a.equal(b)
     */
    public PFB diff(PFB u);
    /**
     *@param u the PFB to check for equality
     *@return if this and u are equal 
     *a.equal(b) -> a.eltCount() == b.eltCount()
     */
    public boolean equal(PFB u);
    /**
     *@param u the PFB to check for subset
     *@return if this is a subset of u
     *a.subset(b) -> a.eltCount() less than or equal to b.eltCount()
     */
    public boolean subset(PFB u);
}



public class PFBBranch<T extends Comparable<T>> implements PFB<T>, Iterator{
    
    T elt;
    int count; 
    PFB le;
    PFB ri;
    int height;
    boolean red;
    
    
    public PFBBranch(T elt, int c){
        this.elt = elt;
        this.count = c;
        this.le = new PFBLeaf();
        this.ri = new PFBLeaf();
        this.red = true;
    }
    
    public PFBBranch(T elt, int c, PFB le, PFB ri){
        this.elt = elt;
        this.count = c;
        this.le = le;
        this.ri = ri;
    }
    
    public PFBBranch(T elt, int c, boolean red, PFB le, PFB ri){
        this.elt = elt;
        this.count = c;
        this.le = le;
        this.ri = ri;
        this.red = red;
    }
    
    public boolean rbInvar1(){
        if(this.red&&(this.le.redHuh()||this.ri.redHuh())){
            return false;
        }
        return this.le.rbInvar1()&&this.ri.rbInvar1();
    }
    
    public int ranPathBCount(){
        if(Math.random()>.5){
            if(!this.redHuh()){
                return 1 + this.le.ranPathBCount();
            }
            return this.le.ranPathBCount();
        }
        if(!this.redHuh()){
                return 1 + this.ri.ranPathBCount();
        }
        return this.ri.ranPathBCount();
    }
    
    
    public boolean redHuh(){
        return this.red;
    }
    
    
    public PFBBranch toBlack(){
        return new PFBBranch(this.elt, this.count, false, this.le, this.ri);
    }
    
    
    public T here(){
        return elt;
    }
    
    public Iterator next(){
        return new Trunk(this.le, this.ri);
    }
    
    public boolean anythingHere(){
        return true;
    }
    
    public int cardinality(){
        return 1 + this.le.cardinality() + this.ri.cardinality();
    }
    
    public int eltCount(){
        return this.count>0? 
                (1 + this.le.eltCount() + this.ri.eltCount()) 
                : (this.le.eltCount() + this.ri.eltCount());
    }
    
    public boolean isEmptyHuh(){
        return false;
    }
    
    public boolean noFilledBags(){
        return (this.count<1&&this.le.noFilledBags()&&this.ri.noFilledBags());
    }
    
    public boolean member(T elt){
            if(elt.compareTo(this.elt)<0){
                return this.le.member(elt);
            }
            if(elt.compareTo(this.elt)>0){
                return this.ri.member(elt);
            }
            if(elt.compareTo(this.elt)==0&&this.count==0){
                return false;
            }
            return true;
    }
    
    public int countOf(T elt){
            if(elt.compareTo(this.elt)<0){
                return this.le.countOf(elt);
            }
            if(elt.compareTo(this.elt)>0){
                return this.ri.countOf(elt);
            }
            return this.count;
    }
    
    
    public PFB left(){
        return this.le;
    }
    
    public PFB right(){
        return this.ri;
    }
    
    public PFB add(T elt, int c){
        return this.ad(elt, c).toBlack();
    }
    
    public PFB add(T elt){
        return this.ad(elt, 1).toBlack();
    }
    
    //THE WAY THIS WORKS BALANCING FLOWS UP THE TREE, SO COOL
    public PFBBranch ad(T elt, int c){
    if(this.elt.compareTo(elt)>0){
             return new PFBBranch(this.elt, this.count, this.red, 
                        this.le.ad(elt, c),
                        this.ri).balance();}
    else if(this.elt.compareTo(elt)<0){
             return new PFBBranch(this.elt, this.count, this.red, 
                        this.le,
                        this.ri.ad(elt, c)).balance();}
    else return new PFBBranch(this.elt,this.count+c, this.red, this.le,this.ri);   
    }
    
    public PFBBranch balance(){
        if(!this.redHuh()){
        try{   
            if(this.le.redHuh()&&this.le.left().redHuh()){
                PFBBranch lef = (PFBBranch)this.le;
                PFBBranch lefgrand = (PFBBranch)lef.le;
                return new PFBBranch(lef.elt,lef.count,true,
                                    lefgrand.toBlack(),
                                    new PFBBranch(this.elt,this.count,false,
                                            lef.ri,
                                            this.ri));
            }
        }
        catch(NothingHere e){}
        try{if(this.ri.redHuh()&&this.ri.right().redHuh()){
                PFBBranch rig = (PFBBranch)this.ri;
                PFBBranch riggrand = (PFBBranch)rig.ri;
                return new PFBBranch(rig.elt,rig.count,true,
                                    new PFBBranch(this.elt,this.count,false,
                                            this.le,
                                            rig.le),
                                    riggrand.toBlack());
            }
        }
        catch(NothingHere e){}
        try{if(this.le.redHuh()&&this.le.right().redHuh()){
                PFBBranch lef = (PFBBranch)this.le;
                PFBBranch riggrand = (PFBBranch)lef.ri;
                return new PFBBranch(riggrand.elt,riggrand.count,true,
                                    new PFBBranch(lef.elt,lef.count,false,
                                            lef.le,
                                            riggrand.le),
                                    new PFBBranch(this.elt,this.count,false,
                                            riggrand.ri,
                                            this.ri));
            }
        }
        catch(NothingHere e){}
        try{if(this.ri.redHuh()&&this.ri.left().redHuh()){
                PFBBranch rig = (PFBBranch)this.ri;
                PFBBranch lefgrand = (PFBBranch)rig.le;
                return new PFBBranch(lefgrand.elt,lefgrand.count,true,
                                    new PFBBranch(this.elt,this.count,false,
                                            this.le,
                                            lefgrand.le),
                                    new PFBBranch(rig.elt,rig.count,false,
                                            lefgrand.ri,
                                            rig.ri));
            }
        }
        catch(NothingHere e){}
        
        return this;
    }
        return this;
    }
    
    
    public PFB remove(T elt, int c){
        if(elt.compareTo(this.elt)==0){
            return new PFBBranch(elt, Math.max(this.count-c,0), this.le, this.ri);
            }
        else if(elt.compareTo(this.elt)<0){
            return new PFBBranch(this.elt,this.count,this.le.remove(elt,c),this.ri);
        }
        return new PFBBranch(this.elt,this.count,this.le,this.ri.remove(elt,c));
        }
    
    public PFB removeAll(T elt){
        
        if(elt.compareTo(this.elt)< 0){
            return new PFBBranch(this.elt,this.count,this.le.removeAll(elt),this.ri);
        }
        else if(elt.compareTo(this.elt)> 0){
            return new PFBBranch(this.elt,this.count,this.le,this.ri.removeAll(elt));
        }
        return new PFBBranch(elt, 0, this.le, this.ri);
        }
    
    
    public PFB union(PFB u){
        return this.ri.union(this.le.union(u)).add(elt,count);
    }
    
    public PFB inter(PFB u){
        if(u.member(this.elt)){
        return new PFBBranch(this.elt,this.count,this.le.inter(u), this.ri.inter(u));}
        else{
    return this.le.inter(u).union(this.ri.inter(u));}
    
    }
    
    public PFB diff(PFB u){
        if(!u.member(this.elt)){
        return new PFBBranch(this.elt,this.count,this.le.diff(u), this.ri.diff(u));}
        else{
    return this.le.diff(u).union(this.ri.diff(u));}
    }
    
    public boolean equal(PFB u){
         if(this.subset(u)&&u.subset(this)){
             return true;
         }
         return false;
    }
    
    
    public boolean subset(PFB u){
         if(u.member(this.elt)){
             return(this.le.subset(u)&&this.ri.subset(u));
         }
         else{return false;}
    }
}




public class PFBLeaf<T extends Comparable<T>> implements PFB<T>, Iterator {
    
    int height;
    
    public PFBLeaf() {}
    
    public boolean isEmptyHuh(){
        return true;
    }
    
    public boolean noFilledBags(){
        return true;
    }
  
    public PFB left()throws NothingHere{
        throw new NothingHere();
    }
    
    public PFB right()throws NothingHere{
        throw new NothingHere();
    }
    
    public boolean redHuh(){
        return false;
    }
    
    public boolean rbInvar1(){
        return true;
    }
    
    public int ranPathBCount(){
        return 1;
    }
   
    
    public PFB add(T elt, int c){
        return new PFBBranch(elt,c,true,new PFBLeaf(),new PFBLeaf());
    }
    
    public PFB add(T elt){
        return new PFBBranch(elt,1,true,new PFBLeaf(),new PFBLeaf());
    }
    
    
    public PFBBranch ad(T elt, int c){
        return new PFBBranch(elt,c,true,new PFBLeaf(),new PFBLeaf());
    }
    
    public int longestPath(){
        return 0;
    }
    
    
    
    public T here() throws NothingHere{
        throw new NothingHere();
    }
    public boolean hasNext(){
        return false;
    }
    public PFB next() throws NothingHere{
        throw new NothingHere();
    }
    
    public int eltCount(){
        return 0;
    };
    
    public boolean anythingHere(){
        return false;
    }
    
    
    public int cardinality(){
        return 0;
    }
    
    public int elementCount(){
        return 0;
    }
    
    
    public boolean member(T elt){
        return false;
    }
    
    public int countOf(T elt){
        return 0;
    }
    
    public PFB remove(T elt, int c){
        return this;
    }
    
    public PFB removeAll(T elt){
        return this;
    }
    
    public PFB union(PFB u){
        return u;
    }
    
    public PFB inter(PFB u){
        return new PFBLeaf();
    }
    
    public PFB diff(PFB u){
        return new PFBLeaf();
    }
    
    public boolean equal(PFB u){
        if(u.isEmptyHuh()){
            return true;
        }
        return false;
    }
   
    public boolean subset(PFB u){
        return true;
    }
    
}




public class Branch implements FinSet, Iterator{
    
    Integer elt;
    FinSet le;
    FinSet ri;
    
    
    
    public Branch(int elt){
        this.elt = elt;
        this.le = new Leaf();
        this.ri = new Leaf();
    }
    
    public Branch(int elt, FinSet le, FinSet ri){
        this.elt = elt;
        this.le = le;
        this.ri = ri;
    }
    
    
    public Integer here(){
        return this.elt;
    }
    
    public boolean anythingHere(){
        return true;
    }
    
    public Iterator next(){
        return new Trunk(this.le, this.ri);
    }
    
    
    
    
    public int cardinality(){
        return 1 + this.ri.cardinality() + this.le.cardinality();
    }
    
    public boolean isEmptyHuh(){
        return false;
    }
    
    public boolean member(int elt){
            if(elt<this.elt){
                return this.le.member(elt);
            }
            if(elt>this.elt){
                return this.ri.member(elt);
            }
            return true;
            
    }
    
    public FinSet add(int elt){
        if(elt < this.elt){
             return new Branch(this.elt,this.le.add(elt),this.ri);}
        if(elt > this.elt){
            return new Branch(this.elt,this.le,this.ri.add(elt));}
        return this;       
    }
    
    public FinSet remove(int elt){
        if(this.elt == elt){
            return this.le.union(this.ri);
            }
        else if(elt < this.elt){
            return new Branch(this.elt,this.le.remove(elt),this.ri);
        }
        else
        return new Branch(this.elt,this.le,this.ri.remove(elt));
        }
    
    
    public FinSet union(FinSet u){
        return this.ri.union(this.le.union(u)).add(elt);
    }
    
    public FinSet inter(FinSet u){
        if(u.member(this.elt)){
        return new Branch(this.elt, this.le.inter(u), this.ri.inter(u));}
        else{
    return this.le.inter(u).union(this.ri.inter(u));}
    
    }
    
    public FinSet diff(FinSet u){
        if(!u.member(this.elt)){
        return new Branch(this.elt, this.le.diff(u), this.ri.diff(u));}
        else{
    return this.le.diff(u).union(this.ri.diff(u));}
    }
    
    public boolean equal(FinSet u){
         if(this.subset(u)&&u.subset(this)){
             return true;
         }
         return false;
    }
    
    
    public boolean subset(FinSet u){
         if(u.member(this.elt)){
             return(this.le.subset(u)&&this.ri.subset(u));
         }
         else{return false;}
    }
}




public class Leaf implements FinSet, Iterator{
   
    
   
    public Leaf() {}
    
    /**
     *@return if the leaf is empty, which will always be true
     */
    public boolean isEmptyHuh(){
        return true;
    }
    
    
     public Integer here() throws NothingHere{
        throw new NothingHere();
    }
    /**
     *@return if any value is in the leaf, which will always be false 
     */
     
    public boolean anythingHere(){
        return false;
    }
    public Iterator next() throws NothingHere{
        throw new NothingHere();
    }
    
    public int cardinality(){
        return 0;
    };
    
    
    
    /**
     *@param elt the element to check membership of
     *@return if the element is in the leaf, which will always be false 
     */
    public boolean member(int elt){
        return false;
    }
    
    public FinSet add(int elt){
        return new Branch(elt);
    }
    
    public FinSet remove(int elt){
        return this;
    }
    
    public FinSet union(FinSet u){
        return u;
    }
    
    public FinSet inter(FinSet u){
        return new Leaf();
    }
    
    public FinSet diff(FinSet u){
        return new Leaf();
    }
    
    public boolean equal(FinSet u){
        if(u.isEmptyHuh()){
            return true;
        }
        return false;
    }
   
    public boolean subset(FinSet u){
        return true;
    }
    
    
}




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


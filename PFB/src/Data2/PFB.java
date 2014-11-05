package Data2;

public interface PFB<T extends Comparable<T>> extends Iterator{
    public boolean redHuh();
    //returns number of keys with counts > 0
    public int keyCount();
    //returns if there are any bags, filled or empty
    public boolean isEmptyHuh();
    //returns if there are any bags with content
    public boolean noFilledBags();
    //determines if there is a key 
    public boolean member(T key);
    //returns the number of elt in that bag or zero if it is not in the PFB
    public int cardinality();
    public int countOf(T elt);
    public PFB left()throws NothingHere;
    public PFB right()throws NothingHere;
    //add count number of things to the PFB
    public PFB add(T key, int count);
    //adds one of key to the PFB
    public PFB add(T key);
    public PFB ad(T key, int count);
    //Reduces Count by num for the appropriate elt, 
    //or until it reaches 0
    public PFB remove(T elt, int c);
    //Reduces count of appropriate elt to 0
    public PFB removeAll(T elt);
    public boolean rbInvar1();
    public int ranPathBCount();
    public PFB union(PFB u);
    public PFB inter(PFB u);
    public PFB diff(PFB u);
    public boolean equal(PFB u);
    public boolean subset(PFB u);
}
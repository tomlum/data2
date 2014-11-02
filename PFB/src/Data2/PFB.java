package Data2;

public interface PFB<T extends Comparable<T>> extends Iterator{
    //returns number of keys with counts > 0
    public int keyCount();
    public boolean isEmptyHuh();
    public boolean noFilledBags();
    public boolean member(T elt);
    //returns the number of elt in that bag or zero if it is not in the PFB
    public int countOf(T elt);
    public PFB add(T elt, int Key);
    //Reduces Count by num for the appropriate elt, 
    //reducing it until it reaches 0
    public PFB remove(T elt, int c);
    //Reduces Count of appropriate elt to 0
    public PFB removeAll(T elt);
    public PFB union(PFB u);
    public PFB inter(PFB u);
    public PFB diff(PFB u);
    public boolean equal(PFB u);
    public boolean subset(PFB u);
}
package Data2;

public interface PFB<T extends Comparable<T>> extends Iterator{
    //returns number of keys with counts > 0
    public int keyCount();
    //returns count if member or
    public boolean isEmptyHuh();
    public boolean member(T elt);
    public PFB add(T elt, int Key);
    public PFB remove(T elt, int Key);
    public PFB union(PFB u);
    public PFB inter(PFB u);
    public PFB diff(PFB u);
    public boolean equal(PFB u);
    public boolean subset(PFB u);
}
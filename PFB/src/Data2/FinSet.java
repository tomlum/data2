
package Data2;


public interface FinSet extends Iterator{
    public int cardinality();
    public boolean isEmptyHuh();
    public boolean member(int elt);
    public FinSet add(int elt);
    public FinSet remove(int elt);
    public FinSet union(FinSet u);
    public FinSet inter(FinSet u);
    public FinSet diff(FinSet u);
    public boolean equal(FinSet u);
    public boolean subset(FinSet u);
}

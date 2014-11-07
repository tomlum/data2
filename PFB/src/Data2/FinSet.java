
package Data2;
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

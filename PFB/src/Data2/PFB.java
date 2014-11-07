package Data2;
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
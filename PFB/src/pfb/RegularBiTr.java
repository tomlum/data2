
package pfb;


public interface RegularBiTr extends Iterator{
    public int cardinality();
    public boolean isEmptyHuh();
    public boolean member(int elt);
    public RegularBiTr add(int elt);
    public RegularBiTr remove(int elt);
    public RegularBiTr union(RegularBiTr u);
    public RegularBiTr inter(RegularBiTr u);
    public RegularBiTr diff(RegularBiTr u);
    public boolean equal(RegularBiTr u);
    public boolean subset(RegularBiTr u);
}

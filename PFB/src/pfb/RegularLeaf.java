
package pfb;

public class RegularLeaf implements RegularBiTr, Iterator{
   
    
   
    public RegularLeaf() {}
    
     public Object here() throws NothingHere{
        throw new NothingHere();
    }
    public boolean hasNext(){
        return false;
    }
    public Object next() throws NothingHere{
        throw new NothingHere();
    }
    
    public int cardinality(){
        return 0;
    };
    
    public boolean isEmptyHuh(){
        return true;
    }
    
    public boolean member(int elt){
        return false;
    }
    
    public RegularBiTr add(int elt){
        return new RegularBranch(elt);
    }
    
    public RegularBiTr remove(int elt){
        return this;
    }
    
    public RegularBiTr union(RegularBiTr u){
        return u;
    }
    
    public RegularBiTr inter(RegularBiTr u){
        return new RegularLeaf();
    }
    
    public RegularBiTr diff(RegularBiTr u){
        return new RegularLeaf();
    }
    
    public boolean equal(RegularBiTr u){
        if(u.isEmptyHuh()){
            return true;
        }
        return false;
    }
   
    public boolean subset(RegularBiTr u){
        return true;
    }
    
    
}
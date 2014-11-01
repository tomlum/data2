package Data2;

public class PFBLeaf<T extends Comparable<T>> implements PFB<T>, Iterator {
    
    public PFBLeaf() {}
    
     public Object here() throws NothingHere{
        throw new NothingHere();
    }
    public boolean hasNext(){
        return false;
    }
    public PFB next() throws NothingHere{
        throw new NothingHere();
    }
    
    public int keyCount(){
        return 0;
    };
    
    public boolean isEmptyHuh(){
        return true;
    }
    
    public boolean member(T elt){
        return false;
    }
    
    public PFB add(T elt, int c){
        return new PFBBranch(elt, c);
    }
    
    public PFB remove(T elt, int c){
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

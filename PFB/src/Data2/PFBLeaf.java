package Data2;

public class PFBLeaf<T extends Comparable<T>> implements PFB<T>, Iterator {
    
    int height;
    
    public PFBLeaf() {}
    
    public boolean rbInvar1(){
        return true;
    }
    
    public int ranPathBCount(){
        return 1;
    }
    
    public boolean redHuh(){
        return false;
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
    
    public PFB left()throws NothingHere{
        throw new NothingHere();
    }
    
    public PFB right()throws NothingHere{
        throw new NothingHere();
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
    
    public int keyCount(){
        return 0;
    };
    
    public boolean anythingHere(){
        return false;
    }
    
    public boolean isEmptyHuh(){
        return true;
    }
    
    public int cardinality(){
        return 0;
    }
    
    public boolean noFilledBags(){
        return true;
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

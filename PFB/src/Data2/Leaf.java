package Data2;

public class Leaf implements FinSet, Iterator{
   
    
   
    public Leaf() {}
    
    /**
     *@return if the leaf is empty, which will always be true
     */
    public boolean isEmptyHuh(){
        return true;
    }
    
    
     public Integer here() throws NothingHere{
        throw new NothingHere();
    }
    /**
     *@return if any value is in the leaf, which will always be false 
     */
     
    public boolean anythingHere(){
        return false;
    }
    public Iterator next() throws NothingHere{
        throw new NothingHere();
    }
    
    public int cardinality(){
        return 0;
    };
    
    
    
    /**
     *@param elt the element to check membership of
     *@return if the element is in the leaf, which will always be false 
     */
    public boolean member(int elt){
        return false;
    }
    
    public FinSet add(int elt){
        return new Branch(elt);
    }
    
    public FinSet remove(int elt){
        return this;
    }
    
    public FinSet union(FinSet u){
        return u;
    }
    
    public FinSet inter(FinSet u){
        return new Leaf();
    }
    
    public FinSet diff(FinSet u){
        return new Leaf();
    }
    
    public boolean equal(FinSet u){
        if(u.isEmptyHuh()){
            return true;
        }
        return false;
    }
   
    public boolean subset(FinSet u){
        return true;
    }
    
    
}
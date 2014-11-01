
package Data2;

public class Leaf implements FinSet, Iterator{
   
    
   
    public Leaf() {}
    
     public Object here() throws NothingHere{
        throw new NothingHere();
    }
    public boolean hasNext(){
        return false;
    }
    public Iterator next() throws NothingHere{
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
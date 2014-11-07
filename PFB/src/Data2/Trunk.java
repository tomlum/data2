package Data2;

public class Trunk implements Iterator{
    Iterator le;
    Iterator ri;
    
    public Trunk(Iterator l, Iterator r){
        le = l;
        ri = r;
    }
    
    public boolean anythingHere(){
        return this.le.anythingHere()||this.ri.anythingHere();
    }
    
    public Comparable here() throws NothingHere{
        if(this.le.anythingHere()){
            return this.le.here();
        }
        return this.ri.here();
    }
    
    public Iterator  next() throws NothingHere{
        if(this.le.anythingHere()){
            return new Trunk(this.le.next(), this.ri);
        }
        return new Trunk(this.le, this.ri.next());
    }
}

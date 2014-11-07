package Data2;

public class Branch implements FinSet, Iterator{
    
    Integer elt;
    FinSet le;
    FinSet ri;
    
    
    
    public Branch(int elt){
        this.elt = elt;
        this.le = new Leaf();
        this.ri = new Leaf();
    }
    
    public Branch(int elt, FinSet le, FinSet ri){
        this.elt = elt;
        this.le = le;
        this.ri = ri;
    }
    
    
    public Integer here(){
        return this.elt;
    }
    
    public boolean anythingHere(){
        return true;
    }
    
    public Iterator next(){
        return new Trunk(this.le, this.ri);
    }
    
    
    
    
    public int cardinality(){
        return 1 + this.ri.cardinality() + this.le.cardinality();
    }
    
    public boolean isEmptyHuh(){
        return false;
    }
    
    public boolean member(int elt){
            if(elt<this.elt){
                return this.le.member(elt);
            }
            if(elt>this.elt){
                return this.ri.member(elt);
            }
            return true;
            
    }
    
    public FinSet add(int elt){
        if(elt < this.elt){
             return new Branch(this.elt,this.le.add(elt),this.ri);}
        if(elt > this.elt){
            return new Branch(this.elt,this.le,this.ri.add(elt));}
        return this;       
    }
    
    public FinSet remove(int elt){
        if(this.elt == elt){
            return this.le.union(this.ri);
            }
        else if(elt < this.elt){
            return new Branch(this.elt,this.le.remove(elt),this.ri);
        }
        else
        return new Branch(this.elt,this.le,this.ri.remove(elt));
        }
    
    
    public FinSet union(FinSet u){
        return this.ri.union(this.le.union(u)).add(elt);
    }
    
    public FinSet inter(FinSet u){
        if(u.member(this.elt)){
        return new Branch(this.elt, this.le.inter(u), this.ri.inter(u));}
        else{
    return this.le.inter(u).union(this.ri.inter(u));}
    
    }
    
    public FinSet diff(FinSet u){
        if(!u.member(this.elt)){
        return new Branch(this.elt, this.le.diff(u), this.ri.diff(u));}
        else{
    return this.le.diff(u).union(this.ri.diff(u));}
    }
    
    public boolean equal(FinSet u){
         if(this.subset(u)&&u.subset(this)){
             return true;
         }
         return false;
    }
    
    
    public boolean subset(FinSet u){
         if(u.member(this.elt)){
             return(this.le.subset(u)&&this.ri.subset(u));
         }
         else{return false;}
    }
}

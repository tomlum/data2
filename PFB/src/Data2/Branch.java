package Data2;


public class Branch implements FinSet, Iterator{
    
    int node;
    FinSet le;
    FinSet ri;
    
    
    
    public Branch(int node){
        this.node = node;
        this.le = new Leaf();
        this.ri = new Leaf();
    }
    
    public Branch(int node, FinSet le, FinSet ri){
        this.node = node;
        this.le = le;
        this.ri = ri;
    }
    
    
    public Object here(){
        return this.node;
    }
    public boolean hasNext(){
        if(this.ri.isEmptyHuh()&&this.le.isEmptyHuh()){
            return false;
        }
        return true;
        //return this.le.union(this.ri).HasNext()
    }
    
    public Iterator next(){
        return this.le.union(this.ri);
    }
    
    
    
    public int cardinality(){
        return 1 + this.ri.cardinality() + this.le.cardinality();
    }
    
    public boolean isEmptyHuh(){
        return false;
    }
    
    public boolean member(int elt){
            if(elt<this.node){
                return this.le.member(elt);
            }
            if(elt>this.node){
                return this.ri.member(elt);
            }
            return true;
            
    }
    
    public FinSet add(int elt){
        if(elt < this.node){
             return new Branch(this.node,this.le.add(elt),this.ri);}
        if(elt > this.node){
            return new Branch(this.node,this.le,this.ri.add(elt));}
        return this;       
    }
    
    public FinSet remove(int elt){
        if(this.node == elt){
            return this.le.union(this.ri);
            }
        else if(elt < this.node){
            return new Branch(this.node,this.le.remove(elt),this.ri);
        }
        else
        return new Branch(this.node,this.le,this.ri.remove(elt));
        }
    
    
    public FinSet union(FinSet u){
        return this.ri.union(this.le.union(u)).add(node);
    }
    
    public FinSet inter(FinSet u){
        if(u.member(this.node)){
        return new Branch(this.node, this.le.inter(u), this.ri.inter(u));}
        else{
    return this.le.inter(u).union(this.ri.inter(u));}
    
    }
    
    public FinSet diff(FinSet u){
        if(!u.member(this.node)){
        return new Branch(this.node, this.le.diff(u), this.ri.diff(u));}
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
         if(u.member(this.node)){
             return(this.le.subset(u)&&this.ri.subset(u));
         }
         else{return false;}
    }
}


package pfb;


public class RegularBranch implements RegularBiTr, Iterator{
    
    int node;
    RegularBiTr le;
    RegularBiTr ri;
    
    
    
    public RegularBranch(int node){
        this.node = node;
        this.le = new RegularLeaf();
        this.ri = new RegularLeaf();
    }
    
    public RegularBranch(int node, RegularBiTr le, RegularBiTr ri){
        this.node = node;
        this.le = le;
        this.ri = ri;
    }
    
    
    public Object here(){
        return new Integer(this.node);
    }
    public boolean hasNext(){
        if(!(this.ri.isEmptyHuh()&&this.le.isEmptyHuh())){
            return true;
        }
        return false;
        //return this.le.union(this.ri).HasNext()
    }
    
    public Object next(){
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
    
    public RegularBiTr add(int elt){
        if(elt < this.node){
             return new RegularBranch(this.node,this.le.add(elt),this.ri);}
        if(elt > this.node){
            return new RegularBranch(this.node,this.le,this.ri.add(elt));}
        return this;       
    }
    
    //still requires union
    public RegularBiTr remove(int elt){
        if(this.node == elt){
            return this.le.union(this.ri);
            }
        else if(elt < this.node){
            return new RegularBranch(this.node,this.le.remove(elt),this.ri);
        }
        else
        return new RegularBranch(this.node,this.le,this.ri.remove(elt));
        }
    
    
    public RegularBiTr union(RegularBiTr u){
        return this.ri.union(this.le.union(u)).add(node);
    }
    
    public RegularBiTr inter(RegularBiTr u){
        if(u.member(this.node)){
        return new RegularBranch(this.node, this.le.inter(u), this.ri.inter(u));}
        else{
    return this.le.inter(u).union(this.ri.inter(u));}
    
    }
    
    public RegularBiTr diff(RegularBiTr u){
        if(!u.member(this.node)){
        return new RegularBranch(this.node, this.le.diff(u), this.ri.diff(u));}
        else{
    return this.le.diff(u).union(this.ri.diff(u));}
    }
    
    public boolean equal(RegularBiTr u){
         if(this.subset(u)&&u.subset(this)){
             return true;
         }
         return false;
    }
    
    
    public boolean subset(RegularBiTr u){
         if(u.member(this.node)){
             return(this.le.subset(u)&&this.ri.subset(u));
         }
         else{return false;}
    }
}

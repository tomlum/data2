package Data2;


public class PFBBranch<T extends Comparable<T>> implements PFB<T>, Iterator{
    
    T key;
    int count; 
    PFB le;
    PFB ri;
    
    
    
    public PFBBranch(T key, int c){
        this.key = key;
        this.count = c;
        this.le = new PFBLeaf();
        this.ri = new PFBLeaf();
    }
    
    public PFBBranch(T key, int c, PFB le, PFB ri){
        this.key = key;
        this.count = c;
        this.le = le;
        this.ri = ri;
    }
    
    
    public Object here(){
        return key;
    }
    public boolean hasNext(){
        if(this.ri.isEmptyHuh()&&this.le.isEmptyHuh()){
            return false;
        }
        return true;
        //return this.le.union(this.ri).HasNext()
    }
    
    public PFB next(){
        return this.le.union(this.ri);
    }
    
    
    
    public int keyCount(){
        if(this.count > 0){
        return 1 + this.ri.keyCount() + this.le.keyCount();
        }
        else return this.ri.keyCount() + this.le.keyCount();
    }
    
    public boolean isEmptyHuh(){
        return this.count>0;
    }
    
    public boolean member(T elt){
            if(elt.compareTo(this.key)<0){
                return this.le.member(elt);
            }
            if(elt.compareTo(this.key)>0){
                return this.ri.member(elt);
            }
            if(elt.compareTo(this.key)==0&&this.count==0){
                return false;
            }
            return true;
    }
    
    public PFB add(T elt, int c){
        if(this.key.compareTo(elt)>0){
             return new PFBBranch(this.key,this.count,this.le.add(elt,c),this.ri);}
        if(elt.compareTo(this.key)<0){
            return new PFBBranch(this.key,this.count,this.le,this.ri.add(elt,c));}
        return new PFBBranch(this.key,this.count+c,this.le,this.ri);       
    }
    
    public PFB remove(T elt, int c){
        if(this.key.compareTo(elt)==0){
            return this.le.union(this.ri);
            }
        else if(elt.compareTo(this.key)<0){
            return new PFBBranch(this.key,this.count,this.le.remove(elt,c),this.ri);
        }
        return new PFBBranch(this.key,this.count,this.le,this.ri.remove(elt,c));
        }
    
    
    public PFB union(PFB u){
        return this.ri.union(this.le.union(u)).add(key,count);
    }
    
    public PFB inter(PFB u){
        if(u.member(this.key)){
        return new PFBBranch(this.key,this.count,this.le.inter(u), this.ri.inter(u));}
        else{
    return this.le.inter(u).union(this.ri.inter(u));}
    
    }
    
    public PFB diff(PFB u){
        if(!u.member(this.key)){
        return new PFBBranch(this.key,this.count,this.le.diff(u), this.ri.diff(u));}
        else{
    return this.le.diff(u).union(this.ri.diff(u));}
    }
    
    public boolean equal(PFB u){
         if(this.subset(u)&&u.subset(this)){
             return true;
         }
         return false;
    }
    
    
    public boolean subset(PFB u){
         if(u.member(this.key)){
             return(this.le.subset(u)&&this.ri.subset(u));
         }
         else{return false;}
    }
}



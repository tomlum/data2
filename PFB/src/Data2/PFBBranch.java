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
    
    
    public T here(){
        return key;
    }
    
    public Iterator next(){
        return new Trunk(this.le, this.ri);
    }
    
    public int keyCount(){
        if(this.count > 0){
        return 1 + this.ri.keyCount() + this.le.keyCount();
        }
        else return this.ri.keyCount() + this.le.keyCount();
    }
    
    public boolean anythingHere(){
        return true;
    }
    
    public boolean isEmptyHuh(){
        return false;
    }
    
    public boolean noFilledBags(){
        return (this.count==0&&this.le.noFilledBags()&&this.ri.noFilledBags());
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
    
    public int countOf(T elt){
            if(elt.compareTo(this.key)<0){
                return this.le.countOf(elt);
            }
            if(elt.compareTo(this.key)>0){
                return this.ri.countOf(elt);
            }
            return this.count;
    }
    
    public PFB add(T elt, int c){
        
        if(this.key.compareTo(elt)>0){
             return new PFBBranch(this.key,this.count,this.le.add(elt,c),this.ri);}
        if(this.key.compareTo(elt)<0){
            return new PFBBranch(this.key,this.count,this.le,this.ri.add(elt,c));}
        return new PFBBranch(this.key,this.count+c,this.le,this.ri);       
    }
    
    public PFB remove(T elt, int c){
        if(elt.compareTo(this.key)==0){
            return new PFBBranch(key, Math.max(this.count-c,0), this.le, this.ri);
            }
        else if(elt.compareTo(this.key)<0){
            return new PFBBranch(this.key,this.count,this.le.remove(elt,c),this.ri);
        }
        return new PFBBranch(this.key,this.count,this.le,this.ri.remove(elt,c));
        }
    
    public PFB removeAll(T elt){
        if(elt.compareTo(this.key)==0){
            return new PFBBranch(key, 0, this.le, this.ri);
            }
        else if(elt.compareTo(this.key)< 0){
            return new PFBBranch(this.key,this.count,this.le.removeAll(elt),this.ri);
        }
        else{
            return new PFBBranch(this.key,this.count,this.le,this.ri.removeAll(elt));
        }
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



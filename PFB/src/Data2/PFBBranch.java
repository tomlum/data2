package Data2;


public class PFBBranch<T extends Comparable<T>> implements PFB<T>, Iterator{
    
    T key;
    int count; 
    PFB le;
    PFB ri;
    int height;
    boolean red;
    
    
    public PFBBranch(T key, int c){
        this.key = key;
        this.count = c;
        this.le = new PFBLeaf();
        this.ri = new PFBLeaf();
        this.red = true;
    }
    
    public PFBBranch(T key, int c, PFB le, PFB ri){
        this.key = key;
        this.count = c;
        this.le = le;
        this.ri = ri;
    }
    
    public PFBBranch(T key, int c, boolean red, PFB le, PFB ri){
        this.key = key;
        this.count = c;
        this.le = le;
        this.ri = ri;
        this.red = red;
    }
    
    public boolean rbInvar1(){
        if(this.red&&(this.le.redHuh()||this.ri.redHuh())){
            return false;
        }
        return this.le.rbInvar1()&&this.ri.rbInvar1();
    }
    
    public int ranPathBCount(){
        if(Math.random()>.5){
            if(!this.redHuh()){
                return 1 + this.le.ranPathBCount();
            }
            return this.le.ranPathBCount();
        }
        if(!this.redHuh()){
                return 1 + this.ri.ranPathBCount();
        }
        return this.ri.ranPathBCount();
    }
    
    
    public boolean redHuh(){
        return this.red;
    }
    
    
    public PFBBranch toBlack(){
        return new PFBBranch(this.key, this.count, false, this.le, this.ri);
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
    
    public int cardinality(){
        return 1 + this.le.cardinality() + this.ri.cardinality();
    }
    
    public boolean isEmptyHuh(){
        return false;
    }
    
    public boolean noFilledBags(){
        return (this.count<1&&this.le.noFilledBags()&&this.ri.noFilledBags());
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
    
    
    public PFB left(){
        return this.le;
    }
    
    public PFB right(){
        return this.ri;
    }
    
    public PFB add(T elt, int c){
        return this.ad(elt, c).toBlack();
    }
    
    public PFB add(T elt){
        return this.ad(elt, 1).toBlack();
    }
    
    //THE WAY THIS WORKS BALANCING FLOWS UP THE TREE, SO COOL
    public PFBBranch ad(T elt, int c){
    if(this.key.compareTo(elt)>0){
             return new PFBBranch(this.key, this.count, this.red, 
                        this.le.ad(elt, c),
                        this.ri).balance();}
    else if(this.key.compareTo(elt)<0){
             return new PFBBranch(this.key, this.count, this.red, 
                        this.le,
                        this.ri.ad(elt, c)).balance();}
    else return new PFBBranch(this.key,this.count+c, this.red, this.le,this.ri);   
    }
    
    public PFBBranch balance(){
        if(!this.redHuh()){
        try{   
            if(this.le.redHuh()&&this.le.left().redHuh()){
                PFBBranch lef = (PFBBranch)this.le;
                PFBBranch lefgrand = (PFBBranch)lef.le;
                return new PFBBranch(lef.key,lef.count,true,
                                    lefgrand.toBlack(),
                                    new PFBBranch(this.key,this.count,false,
                                            lef.ri,
                                            this.ri));
            }
        }
        catch(NothingHere e){}
        try{if(this.ri.redHuh()&&this.ri.right().redHuh()){
                PFBBranch rig = (PFBBranch)this.ri;
                PFBBranch riggrand = (PFBBranch)rig.ri;
                return new PFBBranch(rig.key,rig.count,true,
                                    new PFBBranch(this.key,this.count,false,
                                            this.le,
                                            rig.le),
                                    riggrand.toBlack());
            }
        }
        catch(NothingHere e){}
        try{if(this.le.redHuh()&&this.le.right().redHuh()){
                PFBBranch lef = (PFBBranch)this.le;
                PFBBranch riggrand = (PFBBranch)lef.ri;
                return new PFBBranch(riggrand.key,riggrand.count,true,
                                    new PFBBranch(lef.key,lef.count,false,
                                            lef.le,
                                            riggrand.le),
                                    new PFBBranch(this.key,this.count,false,
                                            riggrand.ri,
                                            this.ri));
            }
        }
        catch(NothingHere e){}
        try{if(this.ri.redHuh()&&this.ri.left().redHuh()){
                PFBBranch rig = (PFBBranch)this.ri;
                PFBBranch lefgrand = (PFBBranch)rig.le;
                return new PFBBranch(lefgrand.key,lefgrand.count,true,
                                    new PFBBranch(this.key,this.count,false,
                                            this.le,
                                            lefgrand.le),
                                    new PFBBranch(rig.key,rig.count,false,
                                            lefgrand.ri,
                                            rig.ri));
            }
        }
        catch(NothingHere e){}
        
        return this;
    }
        return this;
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
        
        if(elt.compareTo(this.key)< 0){
            return new PFBBranch(this.key,this.count,this.le.removeAll(elt),this.ri);
        }
        else if(elt.compareTo(this.key)> 0){
            return new PFBBranch(this.key,this.count,this.le,this.ri.removeAll(elt));
        }
        return new PFBBranch(key, 0, this.le, this.ri);
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



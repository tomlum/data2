
package pfb;

public interface Iterator {
    public Object here() throws NothingHere;
    public boolean hasNext();
    public Object next() throws NothingHere;
}

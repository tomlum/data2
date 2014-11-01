
package Data2;

public interface Iterator {
    public Object here() throws NothingHere;
    public boolean hasNext();
    public Iterator next() throws NothingHere;
}

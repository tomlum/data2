
package Data2;

public interface Iterator<T extends Comparable<T>> {
    public T here() throws NothingHere;
    public boolean anythingHere();
    public Iterator next() throws NothingHere;
}

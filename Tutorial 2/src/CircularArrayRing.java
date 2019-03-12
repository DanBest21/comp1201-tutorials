import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E>
{
    private int size;
    private ArrayList<E> ring = new ArrayList(size);

    public CircularArrayRing() { }

    public CircularArrayRing(int size)
    {
        this.size = size;
    }

    public boolean add(E e)
    {
        ring.add(e);
    }

    public E get(int index) throws IndexOutOfBoundsException
    {
        ring.get(index);
    }

    public Iterator<E> iterator()
    {

    }

    public int size()
    {

    }
}

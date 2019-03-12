import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E>
{
    private int size;
    private int mark = 0;
    private ArrayList<E> ring = new ArrayList(size);

    public CircularArrayRing() { }

    public CircularArrayRing(int size)
    {
        this.size = size;
    }

    public boolean add(E e)
    {
        if (mark >= size)
        {
            mark = 0;
        }

        if (mark < ring.size())
        {
            ring.remove(mark);
        }

        ring.add(mark, e);
        mark++;
        return true;
    }

    public E get(int index) throws IndexOutOfBoundsException
    {
        int realIndex = size - index - 1;

        if (realIndex >= size || realIndex < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            return ring.get(size - index);
        }
    }

    public Iterator<E> iterator()
    {
        return ring.iterator();
    }

    public int size()
    {
        return ring.size();
    }
}

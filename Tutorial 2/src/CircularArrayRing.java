import java.util.*;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E>
{
    private static final int DEFAULT_SIZE = 10;

    private int capacity;
    private int mark = 0;
    private List<E> ring;

    // Default constructor that sets the capacity of the initial ArrayList to the DEFAULT_SIZE constant.
    public CircularArrayRing()
    {
        capacity = DEFAULT_SIZE;
        ring = new ArrayList<>(capacity);
    }

    // Constructor that allows for an initial user-defined capacity.
    public CircularArrayRing(int capacity)
    {
        this.capacity = capacity;
        ring = new ArrayList<>(capacity);
    }

    // Method that adds an element to the ring, replacing an existing one if the ring is already full.
    public boolean add(E e)
    {
        // If the mark is greater than or equal to the capacity, then set it back to zero.
        if (mark >= capacity)
        {
            mark = 0;
        }

        // If the ring is still not at its capacity, then simply add the element to the ring at the next possible location.
        if (size() < capacity)
        {
            mark++;
            ring.add(e);

            // Return false if a value was not overridden.
            return false;
        }

        // Otherwise, replace the element corresponding to the mark index with the passed 'e' argument.
        ring.set(mark, e);
        mark++;

        // Return true if a value was overridden.
        return true;
    }

    // Function that gets the element at the specified index of the ring.
    public E get(int index) throws IndexOutOfBoundsException
    {
        // If the provided index is out of range (negative or above the current size of the ring), then throw an IndexOutOfBoundsException.
        if ((index >= size()) || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        // Else, translate the index into the ArrayList index by subtracting the passed index value and 1 from the mark, and adding 10 should this calculated number be negative.
        // Then use this newly calculated index value with the ArrayList.get() method to retrieve the correct element.
        else
        {
            index = mark - index - 1;
            return ring.get(index < 0 ? index + size() : index);
        }
    }

    // Function that returns the current size of the ring.
    public int size()
    {
        return ring.size();
    }

    // Function that creates and returns an iterator for the ring structure.
    public Iterator<E> iterator()
    {
        // Anonymous inner class for the iterator.
        return new Iterator<E>()
        {
            private int index = 0;

            @Override
            // Method that checks to see if another value exists in the ring based on the current index position.
            public boolean hasNext()
            {
                // If the size minus the index is above 0 then there is at least one more element to iterate to, so return true. Otherwise, return false.
                return (size() - index) > 0;
            }

            @Override
            // Method that returns the next value in the ring.
            public E next() throws NoSuchElementException
            {
                // If there is another value, get the value at the index and then increment the index variable by 1.
                if (hasNext())
                {
                    return get(index++);
                }
                // Otherwise, throw a NoSuchElementException.
                else
                {
                    throw new NoSuchElementException();
                }
            }

            @Override
            // This method simply returns an UnsupportedOperationException since values cannot be removed from a ring by its very definition.
            public void remove() throws UnsupportedOperationException
            {
                throw new UnsupportedOperationException();
            }
        };
    }
}

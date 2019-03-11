import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue
{
    private static final int DEFAULT_SIZE = 10;

    private int head = 0;
    private int tail = 0;
    private int size = DEFAULT_SIZE;

    Integer[] queue = new Integer[size];

    public void enqueue(int in)
    {
        if (getCapacityLeft() == 0)
        {
            doubleQueueSize();
        }
        else if (tail == size)
        {
            tail = 0;
        }

        queue[tail] = in;
        tail++;
    }

    public int dequeue() throws NoSuchElementException
    {
        if (head == size)
        {
            head = 0;
        }

        if (queue[head] != null)
        {
            int value = queue[head];
            queue[head] = null;
            head++;
            return value;
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    public int noItems()
    {
        int items = 0;

        for (Integer i : queue)
        {
            if (i != null)
            {
                items++;
            }
        }

        return items;
    }

    public boolean isEmpty()
    {
        for (Integer i : queue)
        {
            if (i != null)
            {
                return false;
            }
        }

        return true;
    }

    public int getCapacityLeft()
    {
        return size - noItems();
    }

    public void doubleQueueSize()
    {
        Integer[] temp = queue;
        size = size * 2;

        queue = new Integer[size];
        System.arraycopy(temp, 0, queue, 0, temp.length);

        head = 0;
        tail = temp.length;
    }
}

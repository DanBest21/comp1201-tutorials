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
        if (tail > DEFAULT_SIZE)
        {
            Integer[] temp = queue;
            size = size * 2;
            queue = new Integer[size];
            System.arraycopy(temp, 0, queue, 0, temp.length);
        }

        queue[tail] = in;
        tail++;
    }

    public int dequeue() throws NoSuchElementException
    {
        queue[head] = null;
        head++;
    }

    public int noItems()
    {

    }

    public boolean isEmpty()
    {

    }

    public int getCapacityLeft()
    {

    }
}

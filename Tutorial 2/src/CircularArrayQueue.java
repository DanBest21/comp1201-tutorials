import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue
{
    private static final int DEFAULT_SIZE = 10;

    private int head = 0;
    private int tail = 0;
    private int items = 0;
    private int size = DEFAULT_SIZE;

    private int[] queue = new int[size];

    // Function that adds an integer element to the tail of the queue.
    public void enqueue(int in)
    {
        // If the capacity of the array that is left is less than or equal to zero, then call the doubleQueueSize() supplementary function.
        if (getCapacityLeft() <= 0)
        {
            doubleQueueSize();
        }
        // Else, if the tail variable is more than or equal to the size, then set the tail back to zero - i.e. loop back to the start of the array.
        else if (tail >= size)
        {
            tail = 0;
        }

        // Set the tail element to the 'in' value, and then increment the tail and items variables.
        queue[tail] = in;
        tail++;
        items++;
    }

    // Function that removes the first integer element (at the head) from the queue, and returns the removed value.
    public int dequeue() throws NoSuchElementException
    {
        // If the head variable is more than or equal to the size, then set the head variable back to zero - i.e. loop back to the start of the array.
        if (head >= size)
        {
            head = 0;
        }

        // If the head and tail variables are not the same, or they are the same but the array isn't empty, then remove the head element and return its value.
        // Increment the head variable and decrement the items variable to correspond to this change.
        if ((head != tail ) || (head == tail && !isEmpty()))
        {
            int value = queue[head];
            head++;
            items--;
            return value;
        }
        // Otherwise, throw a NoSuchElementException.
        else
        {
            throw new NoSuchElementException();
        }
    }

    // Function that counts the number of populated elements in the queue.
    public int noItems()
    {
        return items;
    }

    // Function that checks to see if the queue is empty.
    public boolean isEmpty()
    {
        return (items == 0);
    }

    // Function that gets the capacity of the queue that is left before it needs to be resized.
    public int getCapacityLeft()
    {
        return size - noItems();
    }

    // Supplemental function that doubles the queue size.
    public void doubleQueueSize()
    {
        // Create a new array called temp, and copy the current queue array to it.
        int[] temp = queue;

        size = size * 2;

        // Recreate the queue array to the new size and copy the contents of temp to it.
        queue = new int[size];
        System.arraycopy(temp, 0, queue, 0, temp.length);

        // Reset the head variable and set the tail variable to the next free location, the length of the old array.
        head = 0;
        tail = temp.length;
    }
}

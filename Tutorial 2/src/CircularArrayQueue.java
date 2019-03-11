import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue
{
    private static final int DEFAULT_SIZE = 10;

    private int head = 0;
    private int tail = 0;
    private int size = DEFAULT_SIZE;

    // Note: An Integer array is used as opposed to an int array since primitives cannot be set to null.
    Integer[] queue = new Integer[size];

    // Function that adds an integer element to the tail of the queue.
    public void enqueue(int in)
    {
        // If the capacity of the array that is left is less than or equal to zero, the call the doubleQueueSize() supplementary function.
        if (getCapacityLeft() <= 0)
        {
            doubleQueueSize();
        }
        // Else, if the tail value is more than or equal to the size, the set the tail back to zero - i.e. loop back to the start of the array.
        else if (tail >= size)
        {
            tail = 0;
        }

        // Set the element in the array at the tail index to the value of the 'in' parameter, and increase the tail value by 1.
        queue[tail] = in;
        tail++;
    }

    // Function that removes the first integer element (at the head) from the queue, and returns the removed value.
    public int dequeue() throws NoSuchElementException
    {
        // If the head value is more than or equal to the size, then set the head value back to zero - i.e. loop back to the start of the array.
        if (head >= size)
        {
            head = 0;
        }

        // If the value at the head of the queue is not null, set the value to null, increase the head value by 1, and return the value that was removed.
        if (queue[head] != null)
        {
            int value = queue[head];
            queue[head] = null;
            head++;
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
        int items = 0;

        // For each element in the queue, if the value is not null, increment the items value by 1.
        for (Integer i : queue)
        {
            if (i != null)
            {
                items++;
            }
        }

        return items;
    }

    // Function that checks to see if the queue is empty.
    public boolean isEmpty()
    {
        // For each element in the queue, if a populated element is found (i.e. a value is not null), return false.
        for (Integer i : queue)
        {
            if (i != null)
            {
                return false;
            }
        }

        // Otherwise return true.
        return true;
    }

    // Function that gets the capacity of the queue that is left before it needs to be resized.
    public int getCapacityLeft()
    {
        // Calculate the capacity by subtracting the number of items populated from the size of the queue.
        return size - noItems();
    }

    // Supplemental function that doubles the queue size.
    public void doubleQueueSize()
    {
        // Create a new array called temp, and copy the current queue array to it.
        Integer[] temp = queue;

        // Double the size variable.
        size = size * 2;

        // Recreate the queue array to the new size and copy the contents of temp to it.
        queue = new Integer[size];
        System.arraycopy(temp, 0, queue, 0, temp.length);

        // Reset the head value and set the tail value to the next free location, the length of the old array.
        head = 0;
        tail = temp.length;
    }
}

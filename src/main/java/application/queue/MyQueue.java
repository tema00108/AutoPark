package application.queue;

import java.util.Arrays;

public class MyQueue<T> {
    private static final int DEFAULT_SIZE = 5;
    private Object[] array;
    private int size;
    private int last;
    private int first;

    public MyQueue() {
        array = new Object[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
        last = -1;
        first = 0;
    }

    public MyQueue(T[] array) {
        this.array = Arrays.copyOf(array, array.length * 2);
        size = array.length * 2;
        last = array.length - 1;
        first = 0;
    }

    public void enqueue(T obj) {
        if (last == size - 1) {
            array = Arrays.copyOf(array, size * 2);
        }

        array[++last] = obj;
    }

    public T dequeue() {
        T element = peek();
        first++;
        if (first > 10) {
            firstToZero();
        }

        return element;
    }

    public T peek() {
        if (first > last) {
            throw new IllegalStateException("Queue is empty");
        }

        return (T) array[first];
    }

    public int size() {
        return last - first + 1;
    }

    private void firstToZero() {
        array = arrayCopy(array, first, last + 1);
        last -= first;
        first = 0;
    }

    private Object[] arrayCopy(Object[] array, int fromIn, int toEx) {
        if (fromIn < 0 || toEx <= fromIn || array.length < toEx) {
            throw new IllegalArgumentException("Array size: " + array.length +
                    ", from index: " + fromIn + ", to index: " + toEx);
        }

        Object[] newArray = new Object[toEx - fromIn];
        System.arraycopy(array, fromIn, newArray, 0, toEx - fromIn);

        return newArray;
    }
}

package by.incubator.queue;

import java.util.Arrays;

public class MyStack<T> {
    private static final int DEFAULT_SIZE = 5;
    private final int CLEAR_ARRAY_SIGNAL = 5;
    private Object[] array;
    private int size;
    private int current;

    public MyStack() {
        array = new Object[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
        current = -1;
    }

    public MyStack(T[] array) {
        this.array = Arrays.copyOf(array, array.length * 2);
        size = array.length * 2;
        current = array.length - 1;
    }

    public void push(T obj) {
        if (current == size - 1) {
            array = Arrays.copyOf(array, size * 2);
        }

        array[++current] = obj;
    }

    public T pop() {
        T element = peek();
        current--;

        if (current == CLEAR_ARRAY_SIGNAL) {
            clearArray();
        }
        return element;
    }

    public T peek() {
        if (current < 0) {
            throw new IllegalStateException("Stack is empty");
        }

        return (T) array[current];
    }

    public int size() {
        return current + 1;
    }

    private void clearArray() {
        array = Arrays.copyOf(array, (current - 1) * 2);
    }
}

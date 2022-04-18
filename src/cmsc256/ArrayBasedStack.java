package cmsc256;

import java.util.Arrays;

public class ArrayBasedStack <T> implements StackInterface<T>{
    private T[] data;
    private int topOfStack;
    private static final int INITIAL_CAPACITY = 5;

    //parameterized constructor
    public ArrayBasedStack(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Array initial size error.");
        }
        @SuppressWarnings("unchecked")
                T[] tempStack = (T[])new Object[capacity];
                data = tempStack;
                topOfStack = -1;
    }
    //default constructor
    public ArrayBasedStack(){
        data = (T[])new Object[INITIAL_CAPACITY];
        topOfStack = -1;
    }
    /*
    private method expandArray double the size of the array when called
     */
    private void expandArray(){
        data = Arrays.copyOf(data,data.length*2);
    }
    //private method isArrayFull return true if topOfStack exceeds the largest index value
    private boolean isArrayFull(){
        if(topOfStack >= data.length - 1){
            return true;
        }
        return false;
    }
    @Override
    public void push(T newEntry) {
        if(isArrayFull()){
            expandArray();
        }
        topOfStack++;
        data[topOfStack] = newEntry;
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T temp = data[topOfStack];
        data[topOfStack] = null;
        topOfStack--;
        return temp;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return data[topOfStack];
    }

    @Override
    public boolean isEmpty() {
        if(topOfStack < 0){
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
                T[] tempStack = (T[])new Object[INITIAL_CAPACITY];
                data = tempStack;
                topOfStack = -1;
    }
}

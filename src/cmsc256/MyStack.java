package cmsc256;
import bridges.base.SLelement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class MyStack<E> implements StackInterface<E> {

    private SLelement<E> top;
    private int size;


    @Override
    public void push(E newEntry) {
        if(newEntry == null){
            throw new IllegalArgumentException("Entry can't be null");
        }
        top = new SLelement<E>(newEntry, top);
        size++;

    }

    @Override
    public E pop() {
        if(top == null){
            throw new EmptyStackException();
        }
        else {
            E result = top.getValue();
            top = top.getNext(); //get the next value of top, which is null
            size--; //readjust
            return result;
        }
    }

    @Override
    public E peek() {
        if(top == null){
            throw new EmptyStackException();
        }
        return top.getValue();
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        top = null;
    }

    public static boolean isBalanced(File webpage) throws FileNotFoundException {
        if(webpage == null) {
            return false;
        }
        Scanner input = new Scanner(webpage);
        MyStack<String> myStack = new MyStack<>(); //Stack
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> tagsData = new ArrayList<String>();

        while(input.hasNextLine()) { //put all the lines of webpage in a String array
            data.add(input.nextLine().trim());
        }

        for(int i = 0; i < data.size(); i++){ //for loop to remove strings containing < >,
            String[] temp = data.get(i).split("(?=<)|(?<=>)"); //https://stackoverflow.com/questions/48999125/splitting-a-string-based-on-angle-brackets
            for(int j = 0; j < temp.length; j++){ //for loops to put in another array list to remove " " using .contains
                if(temp[j].contains("<")){
                    tagsData.add(temp[j]);
                }
            }
        }
        String top = "";
        for (int i = 0; i<tagsData.size(); i++){
            if(!tagsData.get(i).contains("/")){
                myStack.push(tagsData.get(i));
            }
            else if(tagsData.get(i).startsWith("</")){
                        if(!myStack.isEmpty()) {
                            top = myStack.pop();
                        }
                        String deophaipop = tagsData.get(i).replace("/", "");
                        if (!top.equals(deophaipop)) {
                            return false;
                        }
            }

        }
        if(!myStack.isEmpty()){
            return false;
        }
        System.out.println(data);
        System.out.println(tagsData);

        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println(isBalanced(new File("1goodeasypage.html")));
        } catch (Exception e) {
            System.out.println("error.");
        }
    }

}

package improvedcalculator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author shanm
 */
public class MyStack<E> {
    private E[] stack;
    private int top;
    /**
     * Constructor that initializes the stack to an array of objects with size 10
     */
    public MyStack() {
        stack = (E[]) new Object[10];
        top = 0;
    }
    /**
     * Void method to add objects to the stack. The object in the constructor becomes the last element of the stack. 
     * @param obj that represents an object that is added to the stack. It could be a string, integer, double, etc. 
     */
    public void push(E obj) {
        if (top == stack.length) {
            increaseSize();
        }
        stack[top] = obj;
        top++;
    }
    /**
     * Method that pops off the last element in the stack and returns its value
     * @return a generic object that represents the top of the stack
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        top--;
        E obj = stack[top];
        stack[top] = null;
        if (size() < stack.length - 10) {
            decreaseSize();
        }
        return obj;
    }
    /**
     * Method that returns the value of the last element of the stack without popping it off
     * @return a generic object that represents the top of the stack
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[top - 1];
    }
    /**
     * Method that returns how many objects are in the stack
     * @return the variable top, which represents the number of objects in the stack
     */
    public int size() {
        return top;
    }
    /**
     * Method which checks if the stack is empty
     * @return a boolean which tells us if a stack is empty or not. True is empty, and false means it isn't
     */
    public boolean isEmpty() {
        if (top == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Method which puts the contents of the stack into a string
     * @return a string that represents the contents of the stack
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < top; i++) {
            str += stack[i] + " ";
        }
        return str.trim();
    }
    /**
     * void method which increases the size of the array when the stack reaches maximum capacity. 
     */
    private void increaseSize() {
        E[] temp = (E[]) new Object[stack.length + 10];
        for (int i = 0; i < stack.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }
    /**
     * void method which decreases the size of the array when the 10th element is popped off the array
     */
    private void decreaseSize() {
        E[] temp = (E[]) new Object[stack.length - 10];
        for (int i = 0; i < top; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }
}



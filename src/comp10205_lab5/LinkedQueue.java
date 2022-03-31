/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp10205_lab5;

/**
 * Linked Queue class to be used for Lab#5 Used code from COMP-10152 - Data
 * Structures and Algorithms (pg.417-419) Modified by Mark Yendt to be Generic
 *
 * @param <E>
 */
public class LinkedQueue<E> {

    /**
     * Node class to be used by the LinkedQueue class
     *
     * @param <E>
     */
    private class Node<E> {

        E value;
        Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
    private int count;
    private Node<E> front;
    private Node<E> rear;

    /**
     * Constructor for a LinkedQueue
     */
    public LinkedQueue() {
        front = rear = null;
        count = 0;
    }

    /**
     * Add an item to the Queue
     *
     * @param value item to be added to the Queue
     */
    public void enqueue(E value) {
        if (rear != null) {
            rear.next = new Node(value, null);
            rear = rear.next;
        } else {
            rear = new Node(value, null);
            front = rear;
        }
        count++;
    }

    /**
     * Remove an item from the Queue - throws exception if queue is empty
     *
     * @return the item at the from of the Queue
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue - Queue is empty");
        }
        E value = front.value;
        front = front.next;
        count--;
        if (front == null) {
            rear = null;
        }
        return value;
    }

    /**
     * Check is queue is empty
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Shows front of Queue
     *
     * @return the value of the first item in the Queue
     */
    public E peek() {
        return front.value;
    }

    /**
     * Obtain the number of elements in the Queue
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * This method is modified by Meet Patel - 000794612
     *
     * Creates a string of all the elements in the linked list
     *
     * @return a string, containing the entire list of elements
     */
    @Override
    public String toString() {

        String str = "[";
        Node temp = front;
        while (temp != null) {
            str = str + temp.value;
            if (temp.next != null) {
                str = str + ", ";
            }
            temp = temp.next;
        }

        str = str + "]";
        return str;
    }

}

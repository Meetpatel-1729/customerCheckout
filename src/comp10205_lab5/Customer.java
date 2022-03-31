/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp10205_lab5;

/**
 * Customer class to be used for Lab#5 This class will set all the properties of
 * customers and those properties will be used to assign them in respective
 * queue for optimizing the line-up
 *
 * @author Meet Patel
 */
public class Customer {

    int numberOfItems = 0; // Stores the number of items

    boolean isExpressCheckout = false; // Set the flag to true if the customer is allowed in express line else false 

    int startTime = 0; // Start time of the customer

    /**
     * Constructor for a Customer class
     *
     * This will set the flag to true if the customer is allowed in Express
     * line.
     *
     * @param numberOfItems number of items per customer.
     * @param allowedItems allowed items in fast checkout.
     */
    public Customer(int numberOfItems, int allowedItems) {
        this.numberOfItems = numberOfItems;

        if (numberOfItems <= allowedItems) {
            this.isExpressCheckout = true;
        }
    }

    /**
     * Calculates the time required to serve the customer in queue based on the
     * number of items customer have.
     *
     * @return the time required to serve the customer
     */
    public int serveTime() {
        return (45 + 5 * numberOfItems);
    }

    /**
     * Sets the start time of serving the customer once customer joins the queue
     *
     * @param start the start time of serving the customer.
     */
    public void setStartTime(int start) {
        this.startTime = start;
    }

    /**
     * Get the start time of serving the customer
     *
     * @return the start time of serving the customer.
     */
    public int getStartTime() {
        return this.startTime;
    }

    /**
     * Creates a string to display the information about the customer
     *
     * @return a string to be display the information about the customer.
     */
    @Override
    public String toString() {
        return "[" + numberOfItems + "(" + this.serveTime() + " s)]";
    }
}

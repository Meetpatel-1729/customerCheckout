/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp10205_lab5;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

/**
 * Comp10205_Lab5 class uses the Customer and LinkedQueue class
 * This class assigns the customers in a a optimized way in which it will take less time for checkout
 * 
 * @author Meet Patel
 */
public class Comp10205_Lab5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        final String fileName = "resources/CustomerData.txt"; // Stores the file path

        String queueType = "Express"; // Will set the queue type to normal otherwise Express

        ArrayList<LinkedQueue<Customer>> queues = new ArrayList<>(); // Arraylist will stores the linked queue of type customer

        ArrayList<Customer> customers = new ArrayList<>(); // Arraylist of type customer to store all the customers

        // Arraylist of type integer to store the queue wait time (i.e. 0 index value means 1st queues wait time)
        ArrayList<Integer> queueWaitTimes = new ArrayList<>();

        Customer c; // Will store the customer object

        int finishTime; // Finish time of the customer

        // Read in the dictionary 
        try {
            Scanner fin = new Scanner(new File(fileName), "UTF-8"); // Scanner object

            int f = fin.nextInt(); // Number of Express Lines

            int n = fin.nextInt(); // Number of normal lines

            int x = fin.nextInt(); // Allowed items in express line

            int numberOfCustomers = fin.nextInt(); // Number of Cutomers

            while (fin.hasNext()) {
                customers.add(new Customer(fin.nextInt(), x)); // Add all the customers into arraylist
            }

            // Loop through number of queues (Normal + Express)
            for (int i = 0; i < f + n; i++) {
                queueWaitTimes.add(0); // Add 0 value into arraylist which is the starting case when queue starts

                queues.add(new LinkedQueue<>()); // Add an empty linked queue in Arraylist
            }

            System.out.println("PART A - Checkout lines and time estimates for each line \n");

            // Loop through until the customer list become empty
            while (!customers.isEmpty()) {

                c = customers.get(0); // Gets the first customer

                int lessWaitTime; // Stores the less wait time of the queue

                int lessWaitTimeIndex; // Stores the less wait time queue's index

                if (c.isExpressCheckout) {
                    // Loop through all the queues when customer is allowed in express checkout

                    lessWaitTime = Collections.min(queueWaitTimes); // Find the minimum wait time of the queue

                    // Find the minimum wait time queue's index
                    lessWaitTimeIndex = queueWaitTimes.indexOf(Collections.min(queueWaitTimes));

                } else {
                    // Loop through only normal queues when customer is not allowed in  express checkout

                    lessWaitTime = Collections.min(queueWaitTimes.subList(f, (f + n))); // Find the minimum wait time of the queue

                    // Find the minimum wait time queue's index
                    lessWaitTimeIndex = queueWaitTimes.indexOf(Collections.min(queueWaitTimes.subList(f, (f + n))));

                }
                c.setStartTime(lessWaitTime); // Sets the start time of the customer

                queueWaitTimes.set(lessWaitTimeIndex, lessWaitTime + c.serveTime()); // sets the queue wait time index 

                queues.get(lessWaitTimeIndex).enqueue(c); // add the customer in appropriate queue

                customers.remove(0); // Remove the customer from the customer list
            }

            // Loop through all the queues to print out each queue
            for (int i = 0; i < f + n; i++) {

                // Sets the queue type to normal if it is the normal queue
                if (i == f) {
                    queueType = "Normal ";
                }
                // Prints each queue with queue type, queue number, total queue time and all the customers in that queue
                System.out.println("Checkout(" + queueType + ") # " + (i + 1) + "(Est Time = "
                        + queueWaitTimes.get(i) + " s) = " + queues.get(i));
            }

            // Prints the maximum queues wait time which is the time to clear all the customers from the queues
            System.out.println("Time to clear store of all customers = " + Collections.max(queueWaitTimes) + " s\n");

            System.out.println("\nPART B - Number of customers in line after each minute (60s)\n");

            // This will prints the column header time and line numbers of each queue
            String partB = "t(s)";
            for (int i = 0; i < f + n; i++) {
                partB += "\t Line " + (i + 1);
            }

            System.out.println(partB); // Prints the line number

            partB = ""; // Sets the string to null

            //Loop through from 1 second until it clears all the queue 
            for (int time = 1; time <= Collections.max(queueWaitTimes); time++) {

                // Loop through all the queues
                for (int i = 0; i < f + n; i++) {

                    // Checks if the queue is not empty
                    if (!queues.get(i).isEmpty()) {
                        c = queues.get(i).peek(); // Peek the first customer of the perticuler queue

                        // set the finish time based on start time of serving the customer and time it takes to serve the customer
                        finishTime = c.startTime + c.serveTime();

                        /**
                         * Check if the customer is served at particular time or
                         * not. if customer is served then it removes the
                         * customer from the queue
                         */
                        if (finishTime == time) {
                            queues.get(i).dequeue(); // Remove the customer form particular queue
                        }
                    }
                }
                // Loop thorugh each minuter interval or there are no more customers in the queue
                if (time % 60 == 0 || time == Collections.max(queueWaitTimes)) {

                    // Loop through each queue and prints queue size whihc is the total number fo cutomer in that line 
                    for (int i = 0; i < f + n; i++) {
                        partB += "  " + queues.get(i).size() + "\t"; // Concate all the queues size into this string
                    }

                    // Print each number of customers in each queue per every minute interavl
                    System.out.println(time + "\t" + partB);

                    partB = ""; // Sets the string to null
                }
            }

            fin.close(); // Close the file

        } catch (FileNotFoundException e) {
            // This block of code will catch the file not found exception and print the message
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}